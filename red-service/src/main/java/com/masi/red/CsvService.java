package com.masi.red;

import com.masi.red.common.*;
import com.masi.red.dto.NewTestDTO;
import com.masi.red.dto.TestDTO;
import com.masi.red.entity.*;
import com.masi.red.exception.EmptyCsvFileException;
import com.masi.red.exception.InvalidCsvHeaderException;
import com.masi.red.helper.EntityFinder;
import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CsvService implements ICsvService {

    private static final String[] TEST_CSV_HEADER =
            {"numer", "typ", "jezyk", "tresc", "liczba_mozliwych_odpowiedzi", "[tresc_kazdej_odpowiedzi]"};

    private final EntityFinder entityFinder;
    private final TestRepository testRepository;
    private final MapperFacade mapper;

    @Override
    public void exportTestCsv(Integer testId, HttpServletResponse response) throws IOException {
        Test test = entityFinder.findTestById(testId);

        response.setContentType(CsvConstants.MEDIA_TYPE);
        response.setHeader(CsvConstants.HEADER_KEY, CsvConstants.HEADER_TEST_VALUE);

        try (final CSVWriter writer =
                     new CSVWriter(response.getWriter(),
                             CsvConstants.CSV_SEPARATOR,
                             ICSVWriter.NO_QUOTE_CHARACTER,
                             ICSVWriter.DEFAULT_ESCAPE_CHARACTER,
                             ICSVWriter.DEFAULT_LINE_END)) {

            for (Question question : test.getQuestions()) {
                String[] csvRow = getCsvQuestionRow(question);
                writer.writeNext(csvRow);
            }
        }
    }

    @Override
    public TestDTO importTestCsv(NewTestDTO testDTO, MultipartFile file, User user) throws EmptyCsvFileException, InvalidCsvHeaderException, IOException {
        if (file.isEmpty()) {
            throw new EmptyCsvFileException("Przesłany plik jest pusty");
        }
        Language testLang = testDTO.getLanguage();
        String line;
        List<Question> questionList = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] questionArray = line.split(String.valueOf(CsvConstants.CSV_SEPARATOR));

                String type = questionArray[1];
                Language language = Language.valueOf(questionArray[2]);
                String questionContent = questionArray[3];
                String possibleAnswers = questionArray[5];

                possibleAnswers = possibleAnswers.substring(1, possibleAnswers.length() - 1);
                String[] possibleAnswersArray = possibleAnswers.split(CsvConstants.DEFAULT_POSSIBLE_ANSWERS_DELIMITER);
                String suggestedAnswer = getSuggestedAnswer(possibleAnswersArray);

                Question question = null;
                if (Objects.equals(type, QuestionType.SCALE.getSymbol())) {
                    double minVal = Double.parseDouble(possibleAnswersArray[0]);
                    double maxVal = Double.parseDouble(possibleAnswersArray[possibleAnswersArray.length - 1]);
                    double interval = Double.parseDouble(possibleAnswersArray[1]) - minVal;
                    question = new ScaleQuestion(minVal, maxVal, interval);
                } else if (Objects.equals(type, QuestionType.SINGLE_CHOICE.getSymbol())) {
                    question = new SingleChoiceQuestion(new HashSet<>(Arrays.asList(possibleAnswersArray)));
                } else if (Objects.equals(type, QuestionType.NUMERIC.getSymbol())) {
                    question = new NumericQuestion();
                } else if (Objects.equals(type, QuestionType.OPEN.getSymbol())) {
                    question = new OpenQuestion();
                }
                if (question != null) {
                    question.setLanguage(language);
                    question.setSuggestedAnswer(suggestedAnswer);
                    question.setContent(questionContent);
                    question.setOriginalQuestion(question);
                    questionList.add(question);
                }
                testLang = language;
            }
            JobTitle jobTitle = entityFinder.findJobTitleById(testDTO.getJobTitleId());
            User testOwner;
            if (testDTO.getEditorId() != null) {
                testOwner = entityFinder.findUserById(testDTO.getEditorId());
            } else {
                testOwner = user;
            }
            Test test = Test.builder()
                    .user(testOwner)
                    .jobTitle(jobTitle)
                    .language(testLang)
                    .questions(questionList)
                    .build();
            jobTitle.attachTest(test);
            Test save = testRepository.save(test);
            return mapper.map(save, TestDTO.class);
        }
    }

    private String getSuggestedAnswer(String[] scaleAnswers) {
        String suggestedAnswer = Strings.EMPTY;
        for (int i = 0; i < scaleAnswers.length; i++) {
            if (scaleAnswers[i].endsWith(CsvConstants.DEFAULT_SUGGESTED_ANSWER_MARKER)) {
                scaleAnswers[i] = scaleAnswers[i]
                        .replace(CsvConstants.DEFAULT_SUGGESTED_ANSWER_MARKER, Strings.EMPTY);
                suggestedAnswer = scaleAnswers[i];
            }
        }
        return suggestedAnswer;
    }

    private String[] getCsvQuestionRow(Question question) {
        String number = String.valueOf(question.getId());
        String type = QuestionTypeMapper.getQuestionSymbol(question);
        String language = question.getLanguage().toString();
        String content = question.getContent();
        String possibleAnswersNumber = getNumberOfPossibleAnswers(question, type);
        String answerContent = getCsvPossibleAnswers(question, type);
        return new String[]{number, type, language, content, possibleAnswersNumber, answerContent};
    }

    private String getCsvPossibleAnswers(Question question, String type) {
        String suggestedAnswer;
        if (Objects.equals(type, QuestionType.SINGLE_CHOICE.getSymbol())) {
            suggestedAnswer = getSingleChoiceQuestionCsvAnswers(question);
        } else if (Objects.equals(type, QuestionType.SCALE.getSymbol())) {
            suggestedAnswer = getScaleQuestionCsvAnswers(question);
        } else {
            suggestedAnswer = question.getSuggestedAnswer() == null ? Strings.EMPTY : question.getSuggestedAnswer();
            suggestedAnswer += CsvConstants.DEFAULT_SUGGESTED_ANSWER_MARKER;
        }
        return CsvConstants.DEFAULT_ARRAY_OPEN
                + suggestedAnswer
                + CsvConstants.DEFAULT_ARRAY_CLOSE;
    }

    private String getScaleQuestionCsvAnswers(Question question) {
        double minValue = ((ScaleQuestion) question).getMinValue();
        double maxValue = ((ScaleQuestion) question).getMaxValue();
        double interval = ((ScaleQuestion) question).getInterval();
        List<Double> possibleAnswers = new ArrayList<>();
        for (double i = minValue; i <= maxValue; i += interval) {
            possibleAnswers.add(i);
        }

        return possibleAnswers.stream()
                .map(answer -> {
                    String stringAnswer = String.valueOf(answer);
                    if (stringAnswer.equals(question.getSuggestedAnswer())) {
                        stringAnswer += CsvConstants.DEFAULT_SUGGESTED_ANSWER_MARKER;
                    }
                    return stringAnswer;
                })
                .collect(Collectors.joining(CsvConstants.DEFAULT_POSSIBLE_ANSWERS_DELIMITER));
    }

    private String getSingleChoiceQuestionCsvAnswers(Question question) {
        return ((SingleChoiceQuestion) question).getPossibleAnswers().stream()
                .map(answer -> {
                    if (Objects.equals(answer, question.getSuggestedAnswer())) {
                        answer += CsvConstants.DEFAULT_SUGGESTED_ANSWER_MARKER;
                    }
                    return answer;
                })
                .collect(Collectors.joining(CsvConstants.DEFAULT_POSSIBLE_ANSWERS_DELIMITER));
    }

    private String getNumberOfPossibleAnswers(Question question, String type) {
        if (Objects.equals(type, QuestionType.SINGLE_CHOICE.getSymbol())) {
            return String.valueOf(((SingleChoiceQuestion) question).getPossibleAnswers().size());
        } else if (Objects.equals(type, QuestionType.SCALE.getSymbol())) {
            return getScaleQuestionPossibleAnswersNumber((ScaleQuestion) question);
        }
        return CsvConstants.DEFAULT_POSSIBLE_ANSWERS_NUMBER;
    }

    private String getScaleQuestionPossibleAnswersNumber(ScaleQuestion question) {
        double minValue = question.getMinValue();
        double maxValue = question.getMaxValue();
        double interval = question.getInterval();
        double possibleAnswersNumber = ((maxValue - minValue) / interval) + 1;
        return String.valueOf((int) possibleAnswersNumber);
    }

}

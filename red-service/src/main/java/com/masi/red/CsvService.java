package com.masi.red;

import com.masi.red.common.CsvConstants;
import com.masi.red.common.QuestionType;
import com.masi.red.common.QuestionTypeMapper;
import com.masi.red.entity.Question;
import com.masi.red.entity.ScaleQuestion;
import com.masi.red.entity.SingleChoiceQuestion;
import com.masi.red.entity.Test;
import com.masi.red.helper.EntityFinder;
import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CsvService implements ICsvService {

    private static final String[] TEST_CSV_HEADER =
            {"numer", "typ", "jezyk", "tresc", "liczba_mozliwych_odpowiedzi", "[tresc_kazdej_odpowiedzi]"};

    private final EntityFinder entityFinder;

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

            writer.writeNext(TEST_CSV_HEADER);

            for (Question question : test.getQuestions()) {
                String[] csvRow = getCsvQuestionRow(question);
                writer.writeNext(csvRow);
            }
        }
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

package com.masi.red;

import com.masi.red.common.CsvConstants;
import com.masi.red.common.QuestionType;
import com.masi.red.common.QuestionTypeMapper;
import com.masi.red.entity.Question;
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
import java.util.Iterator;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CsvService implements ICsvService {

    private static final String[] TEST_CSV_HEADER =
            {"numer", "typ", "jezyk", "tresc", "liczba_mozliwych_odpowiedzi", "[tresc_kazdej_odpowiedzi]"};
    private static final String DEFAULT_POSSIBLE_ANSWERS_NUMBER = "1";

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
        if (type.equals(QuestionType.SINGLE_CHOICE.getSymbol())) {
            return getSingleChoiceQuestionCsvAnswers(question);
        }
        String suggestedAnswer = question.getSuggestedAnswer() == null ? Strings.EMPTY : question.getSuggestedAnswer();
        return CsvConstants.DEFAULT_ARRAY_OPEN
                + suggestedAnswer
                + CsvConstants.DEFAULT_SUGGESTED_ANSWER_MARKER
                + CsvConstants.DEFAULT_ARRAY_CLOSE;
    }

    private String getSingleChoiceQuestionCsvAnswers(Question question) {
        StringBuilder answerContent = new StringBuilder(CsvConstants.DEFAULT_ARRAY_OPEN);
        for (Iterator<String> iterator = ((SingleChoiceQuestion) question).getPossibleAnswers().iterator(); iterator.hasNext(); ) {
            String possibleAnswer = iterator.next();
            answerContent.append(possibleAnswer);
            if (possibleAnswer.equals(question.getSuggestedAnswer())) {
                answerContent.append(CsvConstants.DEFAULT_SUGGESTED_ANSWER_MARKER);
            }
            if (iterator.hasNext()) {
                answerContent.append(ICSVWriter.DEFAULT_SEPARATOR);
            }
        }
        answerContent.append(CsvConstants.DEFAULT_ARRAY_CLOSE);
        return answerContent.toString();
    }

    private String getNumberOfPossibleAnswers(Question question, String type) {
        if (Objects.equals(type, QuestionType.SINGLE_CHOICE.getSymbol())) {
            return String.valueOf(((SingleChoiceQuestion) question).getPossibleAnswers().size());
        }
        return DEFAULT_POSSIBLE_ANSWERS_NUMBER;
    }

}

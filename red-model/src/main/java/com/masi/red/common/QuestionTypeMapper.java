package com.masi.red.common;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.masi.red.dto.*;
import com.masi.red.entity.*;

public final class QuestionTypeMapper {

    private static BiMap<Class<? extends Question>, Class<? extends QuestionDTO>> questionTypeMap;
    private static BiMap<Class<? extends Question>, QuestionType> questionSymbolMap;

    static {
        questionTypeMap = new ImmutableBiMap.Builder<Class<? extends Question>, Class<? extends QuestionDTO>>()
                .put(ScaleQuestion.class, ScaleQuestionDTO.class)
                .put(SingleChoiceQuestion.class, SingleChoiceQuestionDTO.class)
                .put(OpenQuestion.class, OpenQuestionDTO.class)
                .put(NumericQuestion.class, NumericQuestionDTO.class)
                .build();

        questionSymbolMap = new ImmutableBiMap.Builder<Class<? extends Question>, QuestionType>()
                .put(ScaleQuestion.class, QuestionType.SCALE)
                .put(SingleChoiceQuestion.class, QuestionType.SINGLE_CHOICE)
                .put(OpenQuestion.class, QuestionType.OPEN)
                .put(NumericQuestion.class, QuestionType.NUMERIC)
                .build();
    }

    private QuestionTypeMapper() {
    }

    public static Class<? extends QuestionDTO> getDTOClass(Question entity) {
        return questionTypeMap.get(entity.getClass());
    }

    public static Class<? extends Question> getEntityClass(QuestionDTO dto) {
        return questionTypeMap.inverse().get(dto.getClass());
    }

    public static String getQuestionSymbol(Question entity) {
        return questionSymbolMap.get(entity.getClass()).getSymbol();
    }

}

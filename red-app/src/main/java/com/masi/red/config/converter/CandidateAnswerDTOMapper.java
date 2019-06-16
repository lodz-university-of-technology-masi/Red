package com.masi.red.config.converter;

import com.masi.red.dto.CandidateAnswerDTO;
import com.masi.red.entity.CandidateAnswer;
import com.masi.red.entity.User;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

@Component
public class CandidateAnswerDTOMapper extends BidirectionalConverter<CandidateAnswer, CandidateAnswerDTO>{

        @Override
        public CandidateAnswerDTO convertTo(CandidateAnswer source, Type<CandidateAnswerDTO> destinationType, MappingContext mappingContext) {
            User candidate = source.getUser();
            return CandidateAnswerDTO.builder()
                    .id(source.getId())
                    .creationTime(source.getCreationTime())
                    .testId(source.getTest().getId())
                    .username(candidate.getUsername())
                    .build();
        }

        @Override
        public CandidateAnswer convertFrom(CandidateAnswerDTO source, Type<CandidateAnswer> destinationType, MappingContext mappingContext) {
            return CandidateAnswer.builder()
                    .id(source.getId())
                    .creationTime(source.getCreationTime())
                    .build();
        }
    }

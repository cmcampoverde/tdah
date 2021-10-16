package com.titulacion.tdah.service.mapper;


import com.titulacion.tdah.domain.*;
import com.titulacion.tdah.service.dto.TestAnswerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TestAnswer} and its DTO {@link TestAnswerDTO}.
 */
@Mapper(componentModel = "spring", uses = {TestEdahMapper.class, QuestionMapper.class})
public interface TestAnswerMapper extends EntityMapper<TestAnswerDTO, TestAnswer> {

    @Mapping(source = "testEdah.id", target = "testEdahId")
    @Mapping(source = "question.id", target = "questionId")
    TestAnswerDTO toDto(TestAnswer testAnswer);

    @Mapping(source = "testEdahId", target = "testEdah")
    @Mapping(source = "questionId", target = "question")
    TestAnswer toEntity(TestAnswerDTO testAnswerDTO);

    default TestAnswer fromId(Long id) {
        if (id == null) {
            return null;
        }
        TestAnswer testAnswer = new TestAnswer();
        testAnswer.setId(id);
        return testAnswer;
    }
}

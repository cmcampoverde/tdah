package com.titulacion.tdah.service.mapper;


import com.titulacion.tdah.domain.*;
import com.titulacion.tdah.service.dto.TestEdahDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TestEdah} and its DTO {@link TestEdahDTO}.
 */
@Mapper(componentModel = "spring", uses = {PatientMapper.class})
public interface TestEdahMapper extends EntityMapper<TestEdahDTO, TestEdah> {

    @Mapping(source = "patient.id", target = "patientId")
    TestEdahDTO toDto(TestEdah testEdah);

    @Mapping(source = "patientId", target = "patient")
    TestEdah toEntity(TestEdahDTO testEdahDTO);

    default TestEdah fromId(Integer id) {
        if (id == null) {
            return null;
        }
        TestEdah testEdah = new TestEdah();
        testEdah.setId(id);
        return testEdah;
    }
}

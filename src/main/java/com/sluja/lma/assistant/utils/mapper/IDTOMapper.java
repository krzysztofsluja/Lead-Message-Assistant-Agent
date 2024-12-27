package com.sluja.lma.assistant.utils.mapper;

public interface IDTOMapper<DTO, ENTITY> {

    DTO toDTO(ENTITY entity);
}

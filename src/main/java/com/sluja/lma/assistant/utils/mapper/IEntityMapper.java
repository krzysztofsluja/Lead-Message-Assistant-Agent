package com.sluja.lma.assistant.utils.mapper;

public interface IEntityMapper<DTO, ENTITY> {

    ENTITY toEntity(DTO dto);
}

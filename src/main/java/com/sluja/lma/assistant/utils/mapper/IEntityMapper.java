package com.sluja.lma.assistant.utils.mapper;

import com.sluja.lma.assistant.exception.ExceptionWithErrorCodeAndMessage;

public interface IEntityMapper<DTO, ENTITY> {

    ENTITY toEntity(DTO dto) throws ExceptionWithErrorCodeAndMessage;
}

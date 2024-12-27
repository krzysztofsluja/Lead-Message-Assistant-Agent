package com.sluja.lma.assistant.operator.processes.signup.mapper;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sluja.lma.assistant.operator.processes.signup.dto.NewOperatorDataDTO;
import com.sluja.lma.assistant.operator.processes.signup.exception.IncorrectOperatorDataException;
import com.sluja.lma.assistant.operator.processes.signup.model.OperatorData;
import com.sluja.lma.assistant.utils.mapper.IEntityMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public final class NewOperatorDataDTOtoOperatorDataMapper implements IEntityMapper<NewOperatorDataDTO, OperatorData> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public OperatorData toEntity(final NewOperatorDataDTO newOperatorDataDTO) throws IncorrectOperatorDataException {
        if (Objects.isNull(newOperatorDataDTO)) {
            log.info(
                    "Exception occurred while mapping NewOperatorDataDTO to OperatorData. NewOperatorDataDTO is null!");
            throw new IncorrectOperatorDataException();
        }

        OperatorData entity = new OperatorData();
        entity.setFirstName(newOperatorDataDTO.firstName());
        entity.setLastName(newOperatorDataDTO.lastName());
        entity.setUsername(newOperatorDataDTO.username());
        entity.setPassword(passwordEncoder.encode(newOperatorDataDTO.password()));
        entity.setEmail(newOperatorDataDTO.email());
        entity.setActive(false);
        entity.setValidate(false);
        entity.setAccountLocked(false);

        return entity;
    }
}
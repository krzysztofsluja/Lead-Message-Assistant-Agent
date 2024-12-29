package com.sluja.lma.assistant.operator.processes.signup.service;

import org.springframework.stereotype.Service;

import com.sluja.lma.assistant.exception.ExceptionWithErrorCodeAndMessage;
import com.sluja.lma.assistant.exception.RuntimeExceptionWithErrorCodeAndMessage;
import com.sluja.lma.assistant.operator.processes.signup.dto.NewOperatorDataDTO;
import com.sluja.lma.assistant.operator.processes.signup.dto.request.NewOperatorDataRequestDTO;
import com.sluja.lma.assistant.operator.processes.signup.exception.OperatorAlreadyExistsException;
import com.sluja.lma.assistant.operator.processes.signup.exception.OperatorSignUpInterruptedException;
import com.sluja.lma.assistant.operator.processes.signup.model.OperatorData;
import com.sluja.lma.assistant.operator.processes.signup.repository.OperatorDataRepository;
import com.sluja.lma.assistant.operator.processes.signup.service.interfaces.IOperatorSignUp;
import com.sluja.lma.assistant.utils.mapper.IEntityMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OperatorSignUpService implements IOperatorSignUp {

    private final IEntityMapper<NewOperatorDataDTO, OperatorData> newOperatorDataDTOtoOperatorDataMapper;
    private final OperatorDataRepository operatorDataRepository;

    @Override
    public void initializeSignUp(final NewOperatorDataRequestDTO newOperatorDataRequestDTO)
            throws ExceptionWithErrorCodeAndMessage {
        try {
            final NewOperatorDataDTO newOperatorDataDTO = NewOperatorDataDTO.of(
                    newOperatorDataRequestDTO.firstName(),
                    newOperatorDataRequestDTO.lastName(),
                    newOperatorDataRequestDTO.username(),
                    newOperatorDataRequestDTO.password(),
                    newOperatorDataRequestDTO.email());

            // TODO send verification topic
            saveUnverifiedOperatorData(newOperatorDataDTO);
        } catch (final RuntimeExceptionWithErrorCodeAndMessage ex) {
            log.error("Invalid operator data for request: {}", newOperatorDataRequestDTO, ex);
            throw new OperatorSignUpInterruptedException(ex.getErrorMessagesWithAdditionalInformation());
        } catch (final Exception ex) {
            log.error("Error saving operator data", ex);
            throw new OperatorSignUpInterruptedException();
        }
    }

    private void saveUnverifiedOperatorData(final NewOperatorDataDTO newOperatorDataDTO)
            throws ExceptionWithErrorCodeAndMessage {
        if (!operatorExists(newOperatorDataDTO.username(), newOperatorDataDTO.email())) {
            final OperatorData operatorData = newOperatorDataDTOtoOperatorDataMapper.toEntity(newOperatorDataDTO);
            final OperatorData savedOperatorData = operatorDataRepository.save(operatorData);
            log.info("Operator data saved successfully with id: {}", savedOperatorData.getId());
        }
        throw new OperatorAlreadyExistsException();
    }

    private boolean operatorExists(final String username, final String email) {
        return operatorDataRepository.findByUsernameOrEmail(username, email).isPresent();
    }

}

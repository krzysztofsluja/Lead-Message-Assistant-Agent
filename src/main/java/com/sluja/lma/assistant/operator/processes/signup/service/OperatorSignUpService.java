package com.sluja.lma.assistant.operator.processes.signup.service;

import org.springframework.stereotype.Service;

import com.sluja.lma.assistant.exception.ExceptionWithErrorCodeAndMessage;
import com.sluja.lma.assistant.exception.RuntimeExceptionWithErrorCodeAndMessage;
import com.sluja.lma.assistant.operator.processes.signup.dto.NewOperatorDataDTO;
import com.sluja.lma.assistant.operator.processes.signup.dto.request.NewOperatorDataRequestDTO;
import com.sluja.lma.assistant.operator.processes.signup.exception.IncorrectNewOperatorDataException;
import com.sluja.lma.assistant.operator.processes.signup.exception.OperatorAlreadyExistsException;
import com.sluja.lma.assistant.operator.processes.signup.exception.OperatorSignUpInterruptedException;
import com.sluja.lma.assistant.operator.processes.signup.model.OperatorData;
import com.sluja.lma.assistant.operator.processes.signup.repository.OperatorDataRepository;
import com.sluja.lma.assistant.operator.processes.signup.service.interfaces.IOperatorSignUp;
import com.sluja.lma.assistant.operator.utils.validation.OperatorValidationUtils;
import com.sluja.lma.assistant.utils.mapper.IEntityMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OperatorSignUpService implements IOperatorSignUp {

    private final IEntityMapper<NewOperatorDataDTO, OperatorData> newOperatorDataDTOtoOperatorDataMapper;
    private final OperatorDataRepository operatorDataRepository;

    @Override
    @SneakyThrows
    public void initializeSignUp(final NewOperatorDataRequestDTO newOperatorDataRequestDTO) {

        log.info("Initializing sign up for operator: {}", newOperatorDataRequestDTO);
        if (OperatorValidationUtils.isUsernameInvalid(newOperatorDataRequestDTO.username())
                || OperatorValidationUtils.isEmailInvalid(newOperatorDataRequestDTO.email()))
            throw new IncorrectNewOperatorDataException();

        if (operatorExists(newOperatorDataRequestDTO.username(), newOperatorDataRequestDTO.email()))
            throw new OperatorAlreadyExistsException();

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
        } catch (final ExceptionWithErrorCodeAndMessage ex) {
            log.error("An error occurred!", ex);
            throw ex;
        } catch (final Exception ex) {
            log.error("Error saving operator data", ex);
            throw new OperatorSignUpInterruptedException();
        }
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    private void saveUnverifiedOperatorData(final NewOperatorDataDTO newOperatorDataDTO)
            throws ExceptionWithErrorCodeAndMessage {
        final OperatorData operatorData = newOperatorDataDTOtoOperatorDataMapper.toEntity(newOperatorDataDTO);
        final OperatorData savedOperatorData = operatorDataRepository.save(operatorData);
        log.info("Operator data saved successfully with id: {}", savedOperatorData.getId());
    }

    private boolean operatorExists(final String username, final String email) {
        return operatorDataRepository.findByUsernameOrEmail(username, email).isPresent();
    }

}

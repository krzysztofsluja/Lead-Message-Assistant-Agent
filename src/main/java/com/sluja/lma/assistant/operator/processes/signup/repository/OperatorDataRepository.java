package com.sluja.lma.assistant.operator.processes.signup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sluja.lma.assistant.operator.processes.signup.model.OperatorData;

@Repository
public interface OperatorDataRepository extends JpaRepository<OperatorData, Long> {

    Optional<OperatorData> findByUsernameOrEmail(final String username, final String email);
}

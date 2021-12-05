package com.example.eksamen3semester.repository;

import com.example.eksamen3semester.model.BridgeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BridgeRepository extends JpaRepository<BridgeStatus, Long> {
}

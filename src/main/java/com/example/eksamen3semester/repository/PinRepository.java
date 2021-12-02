package com.example.eksamen3semester.repository;

import com.example.eksamen3semester.model.Pin;
import com.example.eksamen3semester.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface PinRepository extends JpaRepository<Pin, Long> {

}

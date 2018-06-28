package com.oybek.webapp.repository;

import com.oybek.webapp.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Long> {

    List<Type> findAll();
}

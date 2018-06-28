package com.oybek.webapp.repository;

import com.oybek.webapp.entities.Readout;
import com.oybek.webapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadoutRepository extends JpaRepository<Readout, Long> {

    public List<Readout> findByUserlogin(User user);
}

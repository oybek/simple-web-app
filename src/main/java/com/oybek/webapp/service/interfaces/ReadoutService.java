package com.oybek.webapp.service.interfaces;

import com.oybek.webapp.entities.Readout;
import com.oybek.webapp.entities.User;

import java.util.List;

public interface ReadoutService {

    public List<Readout> findByUser(User user);

    public Readout save(Readout readout);
}

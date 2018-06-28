package com.oybek.webapp.service.implementations;

import com.oybek.webapp.entities.Readout;
import com.oybek.webapp.entities.User;
import com.oybek.webapp.repository.ReadoutRepository;
import com.oybek.webapp.service.interfaces.ReadoutService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadoutServiceImpl implements ReadoutService {

    private ReadoutRepository readoutRepository;

    ReadoutServiceImpl(ReadoutRepository readoutRepository) {
        this.readoutRepository =  readoutRepository;
    }

    public List<Readout> findByUser(User user) {
        return readoutRepository.findByUserlogin(user);
    }

    public Readout save(Readout readout) {
        return readoutRepository.save(readout);
    }
}

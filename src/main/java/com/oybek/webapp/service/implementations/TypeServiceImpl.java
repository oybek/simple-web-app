package com.oybek.webapp.service.implementations;

import com.oybek.webapp.entities.Type;
import com.oybek.webapp.repository.TypeRepository;
import com.oybek.webapp.service.interfaces.TypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    private TypeRepository typesRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typesRepository = typeRepository;
    }

    public List<Type> findAll() {
        return this.typesRepository.findAll();
    }
}

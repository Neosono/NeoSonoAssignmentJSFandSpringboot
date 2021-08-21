package com.neosono.assignment.jsfandspring.service;

import com.neosono.assignment.jsfandspring.model.Developer;
import com.neosono.assignment.jsfandspring.repository.DeveloperFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeveloperFormService {

    @Autowired
    DeveloperFormRepository repo;

    public void saveDeveloper(Developer developer) {
        repo.save(developer);
    }


}

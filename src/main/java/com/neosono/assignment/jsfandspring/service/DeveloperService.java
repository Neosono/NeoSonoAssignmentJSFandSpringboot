package com.neosono.assignment.jsfandspring.service;

import com.neosono.assignment.jsfandspring.model.Developer;
import com.neosono.assignment.jsfandspring.repository.DeveloperFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeveloperService {

  @Autowired
  DeveloperFormRepository repo;

  public List<Developer> getDeveloperList(){
    return repo.findAll();
  }
  public Optional<Developer> retrieveDeveloper(Long id) {
    return repo.findById(id);
  }
  public void updateDeveloperSkills(Developer developer) {
      repo.save(developer);
    }
  }

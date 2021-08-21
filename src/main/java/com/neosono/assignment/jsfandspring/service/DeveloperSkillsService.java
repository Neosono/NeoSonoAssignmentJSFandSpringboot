package com.neosono.assignment.jsfandspring.service;

import com.neosono.assignment.jsfandspring.model.DeveloperSkills;
import com.neosono.assignment.jsfandspring.repository.DeveloperSkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeveloperSkillsService {
    @Autowired
    DeveloperSkillsRepository repo;

    public void addSkill(DeveloperSkills skill) { repo.save(skill); }
    public Optional<DeveloperSkills> getSkill(Long id) {
        return repo.findById(id);
    }

}

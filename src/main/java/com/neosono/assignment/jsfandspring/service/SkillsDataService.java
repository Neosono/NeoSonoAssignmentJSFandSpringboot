
package com.neosono.assignment.jsfandspring.service;

import com.neosono.assignment.jsfandspring.model.SkillsDataModel;
import com.neosono.assignment.jsfandspring.repository.SkillsDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillsDataService {

    @Autowired
    SkillsDataRepository repo;

    public List<SkillsDataModel> getSkillsData() {
        return repo.findAll();
    }
    public Optional<SkillsDataModel> getSkill(Long id) {
        return repo.findById(id);
    }

}

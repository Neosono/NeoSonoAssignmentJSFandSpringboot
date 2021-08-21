package com.neosono.assignment.jsfandspring.repository;

import com.neosono.assignment.jsfandspring.model.DeveloperSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperSkillsRepository extends JpaRepository<DeveloperSkills, Long> {

}

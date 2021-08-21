
package com.neosono.assignment.jsfandspring.repository;

import com.neosono.assignment.jsfandspring.model.SkillsDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SkillsDataRepository extends JpaRepository<SkillsDataModel, Long> {
}


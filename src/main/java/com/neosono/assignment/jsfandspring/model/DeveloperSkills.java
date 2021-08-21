package com.neosono.assignment.jsfandspring.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="developer_skills")
public class DeveloperSkills implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "skill_name")
    private String skillName;

    @JoinColumn(name = "skill_description")
    private String skillDescription;

    public DeveloperSkills(){

    }

    public DeveloperSkills(String skillName, String skillDescription) {
        this.id = id;
        this.skillName = skillName;
        this.skillDescription = skillDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }


    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeveloperSkills)) return false;
        DeveloperSkills that = (DeveloperSkills) o;
        return getId().equals(that.getId()) && Objects.equals(getSkillName(), that.getSkillName()) && Objects.equals(getSkillDescription(), that.getSkillDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSkillName(), getSkillDescription());
    }

    @Override
    public String toString() {
        return "DeveloperSkills{" +
                "id=" + id +
                ", skillName='" + skillName + '\'' +
                ", skillDescription=" + skillDescription +
                '}';
    }
}

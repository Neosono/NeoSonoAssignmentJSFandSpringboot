package com.neosono.assignment.jsfandspring.controller;


import com.neosono.assignment.jsfandspring.model.Developer;
import com.neosono.assignment.jsfandspring.model.DeveloperSkills;
import com.neosono.assignment.jsfandspring.service.DeveloperService;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component("springDeveloperListBean")
@ELBeanName(value = "springDeveloperListBean")
@Scope(value = "session")
@Join(path="/", to = "/developers.xhtml")
public class DeveloperListBean implements Serializable {
  @Autowired
  private DeveloperService service;

  private static Logger logger = LoggerFactory.getLogger(DeveloperListBean.class);
  private  List<Developer> developerList = new ArrayList<>();
  private  List<DeveloperSkills> developerSkills = new ArrayList<>();

  private String skillName = "";
  private String concatenatedSkillNames = "";

  public List<DeveloperSkills> getDeveloperSkills() {
    return developerSkills;
  }

  public void setDeveloperSkills(List<DeveloperSkills> developerSkills) {
    this.developerSkills = developerSkills;
  }

  @Transactional
  @PostConstruct
  public  void init(){
    developerList = service.getDeveloperList();
    for(Developer dev : developerList){

      if(dev.getDeveloperSkills() != null || !dev.getDeveloperSkills().isEmpty()) {
        for (DeveloperSkills devSkills :dev.getDeveloperSkills()) {
          //developerSkills = dev.getDeveloperSkills();
        }
      }
    }
  }
  @Transactional
  public List<Developer> getDeveloperList() {
    developerList = service.getDeveloperList();
    for(Developer dev : developerList){
      developerSkills = dev.getDeveloperSkills();
    }
    return developerList;
  }


  public void setDeveloperList(List<Developer> developerList) {
    this.developerList = developerList;
  }

  public String getSkillName() {
    return skillName;
  }

  public void setSkillName(String skillName) {
    this.skillName = skillName;
  }

  public String getConcatenatedSkillNames() {
    return concatenatedSkillNames;
  }

  public void setConcatenatedSkillNames(String concatenatedSkillNames) {
    this.concatenatedSkillNames = concatenatedSkillNames;
  }
}

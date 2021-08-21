package com.neosono.assignment.jsfandspring.controller;


import com.neosono.assignment.jsfandspring.model.Developer;
import com.neosono.assignment.jsfandspring.model.DeveloperSkills;
import com.neosono.assignment.jsfandspring.service.DeveloperFormService;
import com.neosono.assignment.jsfandspring.service.DeveloperService;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component("springDeveloperFormBean")
@ELBeanName(value = "springDeveloperFormBean")
@Scope(value = "session")
@Join(path="/", to = "/developers.xhtml")
public class DeveloperFormBean implements Serializable {


  @Autowired
  private DeveloperService service;

  @Autowired
  DeveloperFormService developerFormService;
  private DeveloperListBean developerListBean;
   private static Logger logger = LoggerFactory.getLogger(DeveloperFormBean.class);

  private Developer developer ;
  private String developerFirstName;
  private String developerLastName;
  private String developerEmail;
  private List<DeveloperSkills> skills = new ArrayList<>();

  public String getDeveloperFirstName() {
    return developerFirstName;
  }

  public void setDeveloperFirstName(String developerFirstName) {
    this.developerFirstName = developerFirstName;
  }

  public String getDeveloperLastName() {
    return developerLastName;
  }

  public void setDeveloperLastName(String developerLastName) {
    this.developerLastName = developerLastName;
  }

  public String getDeveloperEmail() {
    return developerEmail;
  }

  public void setDeveloperEmail(String developerEmail) {
    this.developerEmail = developerEmail;
  }

  public void setDeveloper(Developer developer) {
    this.developer = developer;
  }

  @Transactional
  public String saveDeveloper() {
    Developer newDeveloper = new Developer();
    if(this.getDeveloperFirstName() != null &&
            this.getDeveloperLastName() !=null &&
            this.getDeveloperEmail() != null){

      newDeveloper.setfName(developerFirstName);
      newDeveloper.setlName(developerLastName);
      newDeveloper.setEmail(developerEmail);
      List<DeveloperSkills> skills = new ArrayList<>();
      //newDeveloper.setDeveloperSkills(skills);
      developerFormService.saveDeveloper(newDeveloper);
    }else{
      return "developer data is empty!";
    }
    return "Data saved successfully!!!";
  }
}

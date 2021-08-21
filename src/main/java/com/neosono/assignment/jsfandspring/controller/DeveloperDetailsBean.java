package com.neosono.assignment.jsfandspring.controller;


import com.neosono.assignment.jsfandspring.model.Developer;
import com.neosono.assignment.jsfandspring.model.DeveloperSkills;
import com.neosono.assignment.jsfandspring.model.SkillsDataModel;
import com.neosono.assignment.jsfandspring.service.DeveloperService;
import com.neosono.assignment.jsfandspring.service.SkillsDataService;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Component("springDeveloperDetailsBean")
@ELBeanName("springDeveloperDetailsBean")
@Join(path="/", to = "/developer.xhtml")
@ManagedBean(eager = true)
@ViewScoped
public class DeveloperDetailsBean implements Serializable {
    @Autowired
    private DeveloperService service;

    @Autowired
    private SkillsDataService skillsService;

    private static Logger logger = LoggerFactory.getLogger(DeveloperDetailsBean.class);

    private Optional<Developer> optionalDeveloper;
    private List<SkillsDataModel> devSkillStandingDataList = new ArrayList<>();
    private String developerIdParam = "";
    private List<DeveloperSkills> devSkillList;
    private List<Developer> developerList = new ArrayList<>();
    private String skillName = "";
    private String skillDescription = "";
    private String developerFirstName;
    private String developerLastName;
    private Long id = 0L;
    private Long devId = 0L;
    private String concatenatedSkillAndDescription;

    private DeveloperSkills developerSkills;

    private Developer dev;

    public Long getId() {
        return id;
    }

    public List<DeveloperSkills> getDevSkillList() {
        return devSkillList;
    }

    public void setDevSkillList(List<DeveloperSkills> devSkillList) {
        this.devSkillList = devSkillList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeveloperSkills getDeveloperSkills() {
        return developerSkills;
    }

    public void setDeveloperSkills(DeveloperSkills developerSkills) {
        this.developerSkills = developerSkills;
    }

    public String getDeveloperIdParam() {
        return developerIdParam;
    }

    public void setDeveloperIdParam(String developerIdParam) {
        this.developerIdParam = developerIdParam;
    }

    public void setDeveloper(Developer developer) {
        this.optionalDeveloper = Optional.ofNullable(developer);
    }

    public List<Developer> getDeveloperList() {
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

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

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

    public String getConcatenatedSkillAndDescription() {
        return concatenatedSkillAndDescription;
    }

    public void setConcatenatedSkillAndDescription(String concatenatedSkillAndDescription) {
        this.concatenatedSkillAndDescription = concatenatedSkillAndDescription;
    }

    public List<SkillsDataModel> getDevSkillStandingDataList() {
        return devSkillStandingDataList;
    }

    public void setDevSkillStandingDataList(List<SkillsDataModel> devSkillStandingDataList) {
        this.devSkillStandingDataList = devSkillStandingDataList;
    }

    public Long getDeveloperId() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc != null) {
            Map<String, String> params = fc.getExternalContext()
                    .getRequestParameterMap();
            developerIdParam = params.get("id");
            if (developerIdParam != null || !developerIdParam.isEmpty()) {
                devId = Long.parseLong(developerIdParam);
            }
        }
        return devId;
    }

    @Transactional
    @PostConstruct
    public void init() {
        developerList = service.getDeveloperList();
        devSkillStandingDataList = skillsService.getSkillsData();
        if (getDeveloperId() != null && getDeveloperId() != 0L) {
            if (developerList != null && !developerList.isEmpty()) {
                for (Developer developer : developerList) {
                    if (developer.getId() == devId) {
                        optionalDeveloper = Optional.of(developer);
                        if (optionalDeveloper.isPresent()) {
                            dev = optionalDeveloper.get();
                            developerFirstName = dev.getfName();
                            developerLastName = dev.getlName();
                            devSkillList = dev.getDeveloperSkills();
                        }break;
                    }
                }
            }
        }
    }
    public String addSkill() {
        DeveloperSkills ds = new DeveloperSkills();
        List<DeveloperSkills> dsList = new ArrayList<>();
        List<DeveloperSkills> newDsList = new ArrayList<>();
        Developer developer = new Developer();
        Optional<Developer> optionalDeveloper = service.retrieveDeveloper(devId);
        if (optionalDeveloper.isPresent()) {
            developer = optionalDeveloper.get();
        }
        if (developer.getDeveloperSkills() != null) {
            dsList = developer.getDeveloperSkills();
            for (SkillsDataModel skillData : devSkillStandingDataList) {
                if (skillData.getSkillName().trim().equalsIgnoreCase(skillName.trim())) {
                    ds.setSkillDescription(skillData.getSkillDescription());
                    ds.setSkillName(skillName);
                    dsList.add(ds);
                    developer.setDeveloperSkills(dsList);
                    service.updateDeveloperSkills(developer);
                }
            }
        } else {
            for (SkillsDataModel skillData : devSkillStandingDataList) {
                if (skillData.getSkillName().trim().equalsIgnoreCase(skillName.trim())) {
                    ds.setSkillDescription(skillData.getSkillDescription());
                    ds.setSkillName(skillName);
                    newDsList.add(ds);
                    developer.setDeveloperSkills(newDsList);
                    service.updateDeveloperSkills(developer);

                }
            }
        }
        return "ok!";
    }
}


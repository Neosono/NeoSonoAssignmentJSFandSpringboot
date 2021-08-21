package com.neosono.assignment.jsfandspring.controller;

import com.fasterxml.jackson.databind.util.Converter;
import com.neosono.assignment.jsfandspring.model.SkillsDataModel;
import com.neosono.assignment.jsfandspring.service.SkillsDataService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

@ManagedBean
@RequestScoped
public abstract class SkillsConverter implements Converter {

    @Autowired
    private SkillsDataService skillsDataService;

    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        try {
            return skillsDataService.getSkill(Long.valueOf(submittedValue));
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid Skill ID", submittedValue)), e);
        }
    }


    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof SkillsDataModel) {
            return String.valueOf(((SkillsDataModel) modelValue).getId());
        } else {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid skill", modelValue)));
        }
    }

}
package com.neosono.assignment.jsfandspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.ManagedBean;

@ManagedBean
@Scope("request")
public class SpringBeanClass extends SpringBeanAutowiringSupport {
    @Autowired
    private DeveloperListBean myList;

    @Autowired
    private DeveloperFormBean myForm;

    @Autowired
    private DeveloperDetailsBean mySkills;
}

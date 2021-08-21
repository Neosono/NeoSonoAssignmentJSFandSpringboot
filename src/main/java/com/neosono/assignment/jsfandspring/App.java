package com.neosono.assignment.jsfandspring;

import com.neosono.assignment.jsfandspring.model.SkillsDataModel;
import com.neosono.assignment.jsfandspring.repository.SkillsDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@ComponentScan
public class App implements ApplicationListener<ContextRefreshedEvent> {
  private static final Logger LOG = LoggerFactory.getLogger(App.class);
  //put/chain MVC data on network via IoC
  @Autowired
  SkillsDataRepository repo;

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @Bean
  ServletRegistrationBean jsfServletRegistration (ServletContext servletContext) {
    //spring boot only works if this is set
    servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
    //registration
    ServletRegistrationBean srb = new ServletRegistrationBean();
    srb.setServlet(new FacesServlet());
    srb.setUrlMappings(Arrays.asList("*.xhtml"));
    srb.setLoadOnStartup(1);
    return srb;
  }

  //load skills standing data via file
  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    try {
      LOG.info("########### Load Skills Data ################");
      importSkillsData();
      LOG.info(" ########## Exit Import File ######################");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  //implement file loading...
  public void importSkillsData() throws IOException {
    LOG.info("###### start:: import File: ...file .txt #################");
    File file = new ClassPathResource("skills_data.txt").getFile();
    try {
      BufferedReader b = new BufferedReader(new FileReader(file));
      String readLine = "";
      b.readLine();
      while ((readLine = b.readLine()) != null) {
        SkillsDataModel data = new SkillsDataModel();
        data.setSkillName(readLine.substring(0, 27).trim());
        data.setSkillDescription(readLine.substring(27).trim());
        repo.save(data);
      }
      LOG.info("###### end:: importFile #################");
      List<SkillsDataModel> skillsList = (List<SkillsDataModel>)repo.findAll();
      LOG.info("########### size of the list is from H2 DB ############"+skillsList.size());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

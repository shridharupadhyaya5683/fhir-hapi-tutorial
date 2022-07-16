package com.shridhar.fhir.fhirhapitutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FhirHapiTutorialApplication {

  public static void main(String[] args) {
    SpringApplication.run(FhirHapiTutorialApplication.class, args);
  }

  @Bean
  public ServletRegistrationBean ServletRegistrationBean(){

    ServletRegistrationBean registration=new ServletRegistrationBean(new SimpleRestfulServer(),"/*");

    registration.setName("FhirServlet");

    return registration;
  }
}

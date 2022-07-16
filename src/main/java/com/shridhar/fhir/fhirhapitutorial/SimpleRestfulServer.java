package com.shridhar.fhir.fhirhapitutorial;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.RestfulServer;
import com.shridhar.fhir.fhirhapitutorial.resource.PatientResourceProvider;
import javax.servlet.ServletException;

public class SimpleRestfulServer extends RestfulServer {

  @Override
  protected void initialize()throws ServletException {
    //create a context for the appropriate version
    setFhirContext(FhirContext.forR5());

    registerProvider(new PatientResourceProvider());
  }
}

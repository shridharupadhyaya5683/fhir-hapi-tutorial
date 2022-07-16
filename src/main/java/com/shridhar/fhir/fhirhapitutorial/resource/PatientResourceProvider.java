package com.shridhar.fhir.fhirhapitutorial.resource;

import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r5.model.IdType;
import org.hl7.fhir.r5.model.Patient;

public class PatientResourceProvider implements IResourceProvider {

  public PatientResourceProvider() {
    loadDummyPatients();
  }

  private Map<String, Patient> patientMap = new HashMap<>();

  @Override
  public Class<? extends IBaseResource> getResourceType() {
    return Patient.class;
  }


  @Read()
  public Patient read(@IdParam IdType theId) {

    Patient retVal = patientMap.get(theId.getIdPart());

    if (retVal == null) {
      throw new ResourceNotFoundException(theId);
    }

    return retVal;
  }

  @Search
  public List<Patient> search(@RequiredParam(name = Patient.SP_FAMILY) StringParam theParam) {

    List<Patient> patients = this.searchByFamilyName(theParam.getValue());

    return patients;
  }

  private List<Patient> searchByFamilyName(String familyName){

    List<Patient> retPatients = patientMap.values()
        .stream()
        .filter(next -> familyName.toLowerCase().equals(next.getNameFirstRep().getFamily().toLowerCase()))
        .collect(Collectors.toList());

    return retPatients;
  }

  private void loadDummyPatients() {

    Patient patient = new Patient();

    patient.setId("1");

    patient.addIdentifier().setSystem("http://optum.com/MRNs").setValue("007");

    patient.addName().setFamily("Chakravarty").addGiven("Mithun").addGiven("A");

    patient.addAddress().addLine("Address Line 1");

    patient.addAddress().setCity("Mumbai");

    patient.addAddress().setCountry("India");

    patient.addTelecom().setValue("111-111-1111");

    this.patientMap.put("1", patient);

    for (int i = 2; i < 5; i++) {

      patient = new Patient();

      patient.setId(Integer.toString(i));

      patient.addIdentifier().setSystem("http://optum.com/MRNs").setValue("007" + i);

      patient.addName().setFamily("Bond" + i).addGiven("James").addGiven("J");

      patient.addAddress().addLine("House Line " + i);

      patient.addAddress().setCity("Your City");

      patient.addAddress().setCountry("USA");

      this.patientMap.put(Integer.toString(i), patient);

    }

  }
}

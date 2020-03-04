package com.example.population_census.api.request;

public class DetailsRequest {
    private String nationalId,
            maritalStatus,
            employmentStatus,
            digitalAddress,
            address,
            gender,
            dob;

    public DetailsRequest() {
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public void setDigitalAddress(String digitalAddress) {
        this.digitalAddress = digitalAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.address = houseAddress;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}

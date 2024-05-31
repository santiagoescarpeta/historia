package com.ms.historyinquiry.DTO;

import java.util.Date;
import java.util.List;

public class UserDto {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public String getDiseasePrevented() {
        return diseasePrevented;
    }

    public void setDiseasePrevented(String diseasePrevented) {
        this.diseasePrevented = diseasePrevented;
    }

    public int getDosesAdministered() {
        return dosesAdministered;
    }

    public void setDosesAdministered(int dosesAdministered) {
        this.dosesAdministered = dosesAdministered;
    }

    public Date getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(Date vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public List<VaccineDto> getVaccines() {
        return vaccines;
    }

    public void setVaccines(List<VaccineDto> vaccines) {
        this.vaccines = vaccines;
    }

    private String name;
    private Integer age;
    private String vaccineType;
    private String diseasePrevented;
    private int dosesAdministered;
    private Date vaccinationDate;
    private List<VaccineDto> vaccines;

    // Getters and Setters
}


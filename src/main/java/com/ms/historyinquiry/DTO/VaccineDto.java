package com.ms.historyinquiry.DTO;

public class VaccineDto {
    private Long id;
    private String name;
    private String manufacturer;
    private int dosesAdministered;

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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getDosesAdministered() {
        return dosesAdministered;
    }

    public void setDosesAdministered(int dosesAdministered) {
        this.dosesAdministered = dosesAdministered;
    }

    // Getters and Setters
}

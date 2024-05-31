package com.ms.historyinquiry.Model;

import com.ms.historyinquiry.Model.Vaccine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "user_vaccine")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_vaccine_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vaccine_id", referencedColumnName = "vaccine_id")
    private Vaccine vaccine;

    @Column(name = "vaccine_type")
    private String vaccineType;

    @Column(name = "disease_prevent")
    private String diseasePrevent;

    @Column(name = "doses_administered")
    private int dosesAdministered;

    @Column(name = "vaccination_date")
    private Date vaccinationDate;
}

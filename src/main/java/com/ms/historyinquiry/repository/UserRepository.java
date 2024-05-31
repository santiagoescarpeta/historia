package com.ms.historyinquiry.repository;

import com.ms.historyinquiry.Model.User;
import com.ms.historyinquiry.Model.UserVaccine;
import com.ms.historyinquiry.Model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT uv.vaccine FROM UserVaccine uv WHERE uv.usuario.id = :userId AND uv.vaccine.id = :vaccineId")
    List<Vaccine> findVaccinationHistoryByUserIdAndVaccineId(@Param("userId") Long userId, @Param("vaccineId") Long vaccineId);

    @Query("SELECT uv.vaccine FROM UserVaccine uv WHERE uv.usuario.id = :userId")
    List<Vaccine> findVaccinationHistoryByUserId(@Param("userId") Long userId);


    @Query("SELECT uv FROM UserVaccine uv WHERE uv.usuario.id = :userId AND uv.vaccine.id = :vaccineId")
    UserVaccine findVaccineDetails(@Param("userId") Long userId, @Param("vaccineId") Long vaccineId);



    @Query("SELECT uv.usuario FROM UserVaccine uv WHERE uv.vaccineType = :vaccineType")
    List<User> findByVaccineType(@Param("vaccineType") String vaccineType);

    @Query("SELECT uv.usuario FROM UserVaccine uv WHERE uv.vaccinationDate = :vaccinationDate")
    List<User> findByVaccinationDate(@Param("vaccinationDate") Date vaccinationDate);

    @Query("SELECT uv.usuario FROM UserVaccine uv WHERE uv.vaccineType = :vaccineType AND uv.vaccinationDate = :vaccinationDate")
    List<User> findByVaccineTypeAndVaccinationDate(@Param("vaccineType") String vaccineType, @Param("vaccinationDate") Date vaccinationDate);

}


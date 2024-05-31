package com.ms.historyinquiry.repository;

import com.ms.historyinquiry.Model.User;
import com.ms.historyinquiry.Model.UserVaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserVaccineRepository  extends  JpaRepository<UserVaccine, Long> {
    List<UserVaccine> findByUsuarioId(Long userId);
}


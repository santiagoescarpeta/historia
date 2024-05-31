package com.ms.historyinquiry.Service;

import com.ms.historyinquiry.Exporter.PdfExporter;
import com.ms.historyinquiry.Exporter.CsvExporter;
import com.ms.historyinquiry.Model.User;
import com.ms.historyinquiry.Model.UserVaccine;
import com.ms.historyinquiry.Model.Vaccine;
import com.ms.historyinquiry.repository.UserRepository;
import com.ms.historyinquiry.repository.UserVaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ms.historyinquiry.DTO.UserDto;
import com.ms.historyinquiry.DTO.VaccineDto;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final CsvExporter csvExporter;
    private final PdfExporter pdfExporter;
    private UserVaccineRepository userVaccineRepository;


    @Autowired
    public UserService(UserRepository userRepository, CsvExporter csvExporter, PdfExporter pdfExporter,UserVaccineRepository userVaccineRepository) {
        this.userRepository = userRepository;
        this.csvExporter = csvExporter;
        this.pdfExporter = pdfExporter;
        this.userVaccineRepository = userVaccineRepository;
    }

    public byte[] exportVaccinationHistory(Long userId, String format) {
        List<Vaccine> vaccinationHistory = userRepository.findVaccinationHistoryByUserId(userId);
        if (vaccinationHistory != null) {
            if ("pdf".equalsIgnoreCase(format)) {
                return pdfExporter.exportToPdf(vaccinationHistory);
            } else if ("csv".equalsIgnoreCase(format)) {
                return csvExporter.exportToCsv(vaccinationHistory);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public List<UserDto> filterUsers(String vaccineType, Date vaccinationDate) {
        List<User> users;

        if (vaccineType != null && vaccinationDate != null) {
            users = userRepository.findByVaccineTypeAndVaccinationDate(vaccineType, vaccinationDate);
        } else if (vaccineType != null) {
            users = userRepository.findByVaccineType(vaccineType);
        } else if (vaccinationDate != null) {
            users = userRepository.findByVaccinationDate(vaccinationDate);
        } else {
            users = userRepository.findAll();
        }

        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getUsername());
        userDto.setAge(user.getEdad());

        List<UserVaccine> userVaccines = userVaccineRepository.findByUsuarioId(user.getId());
        if (!userVaccines.isEmpty()) {
            UserVaccine firstVaccine = userVaccines.get(0);
            userDto.setVaccineType(firstVaccine.getVaccineType());
            userDto.setDiseasePrevented(firstVaccine.getDiseasePrevent());
            userDto.setDosesAdministered(firstVaccine.getDosesAdministered());
            userDto.setVaccinationDate(firstVaccine.getVaccinationDate());

            List<VaccineDto> vaccineDtos = new ArrayList<>();
            for (UserVaccine uv : userVaccines) {
                VaccineDto vaccineDto = new VaccineDto();
                vaccineDto.setId(uv.getVaccine().getId());
                vaccineDto.setName(uv.getVaccine().getName());
                vaccineDto.setManufacturer(uv.getVaccine().getManufacturer());
                vaccineDto.setDosesAdministered(uv.getDosesAdministered());
                vaccineDtos.add(vaccineDto);
            }
            userDto.setVaccines(vaccineDtos);
        }

        return userDto;
    }

    public UserVaccine getVaccineDetails(Long userId, Long vaccineId) {
        return userRepository.findVaccineDetails(userId, vaccineId);
    }

    public UserDto getUserVaccinationHistory(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return null;
        }

        User user = userOptional.get();
        return convertToDto(user);
    }

    public List<Vaccine> getVaccinationHistory(Long userId) {
        return userRepository.findVaccinationHistoryByUserId(userId);
    }
}

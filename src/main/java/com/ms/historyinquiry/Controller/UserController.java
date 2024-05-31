package com.ms.historyinquiry.Controller;

import com.ms.historyinquiry.DTO.UserDto;
import com.ms.historyinquiry.Model.User;
import com.ms.historyinquiry.Model.UserVaccine;
import com.ms.historyinquiry.Model.Vaccine;
import com.ms.historyinquiry.Service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/filter")
    public ResponseEntity<List<UserDto>> filterUsers(
            @RequestParam(required = false) String vaccineType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date vaccinationDate) {
        List<UserDto> filteredUsers = userService.filterUsers(vaccineType, vaccinationDate);
        return ResponseEntity.ok(filteredUsers);
    }

    @GetMapping("/{userId}/vaccination-history")
    public ResponseEntity<UserDto> getUserVaccinationHistory(@PathVariable Long userId) {
        UserDto userDto = userService.getUserVaccinationHistory(userId);
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/vaccination-history/export")
    public ResponseEntity<byte[]> exportVaccinationHistory(@PathVariable Long userId, @RequestParam String format) {
        byte[] fileContent = userService.exportVaccinationHistory(userId, format);
        if (fileContent != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("filename", "vaccination_history." + format);
            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}/vaccination-history/{vaccineId}")
    public ResponseEntity<UserDetailsResponse> getVaccineDetails(@PathVariable Long userId, @PathVariable Long vaccineId) {
        UserVaccine userVaccine = userService.getVaccineDetails(userId, vaccineId);
        if (userVaccine != null) {
            UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
            userDetailsResponse.setId(userVaccine.getUsuario().getId());
            userDetailsResponse.setUsername(userVaccine.getUsuario().getUsername());
            userDetailsResponse.setEdad(userVaccine.getUsuario().getEdad());
            return ResponseEntity.ok(userDetailsResponse);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Data
    public class UserDetailsResponse {
        private Long id;
        private String username;
        private Integer edad;
    }
    }
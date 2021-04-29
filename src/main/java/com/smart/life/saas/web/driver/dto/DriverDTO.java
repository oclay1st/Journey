package com.smart.life.saas.web.driver.dto;

import com.smart.life.saas.domain.common.Gender;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class DriverDTO {

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    @NotEmpty
    private List<String> phones;

    @NotNull
    private boolean active;

    @NotNull
    private String licenseId;

    private Integer dailyBookingLimit;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;

    @NotNull
    private Gender gender;

    @NotEmpty
    private List<String> languages;

    @NotNull
    private MultipartFile imageFile;
}

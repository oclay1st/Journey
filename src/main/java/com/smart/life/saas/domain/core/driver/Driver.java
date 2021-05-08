package com.smart.life.saas.domain.core.driver;

import com.smart.life.saas.domain.common.Gender;
import com.smart.life.saas.domain.core.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @NotEmpty
    @ElementCollection
    private List<String> phones;

    @NotNull
    private boolean active;

    @NotNull
    private String licenseId;

    @NotNull
    private Integer dailyBookingLimit;

    @NotNull
    private LocalDate birthday;

    @NotNull
    private Gender gender;

    @NotEmpty
    @ElementCollection
    private List<String> languages;

    @NotNull
    private String image;

    @OneToOne
    private User user;
}

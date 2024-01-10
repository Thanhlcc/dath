package com.projects.dath.dto;

import ch.qos.logback.core.boolex.EvaluationException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.projects.dath.constant.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Date;

public record RegisterReq(
        @NotNull @NotBlank
        String username,
        @NotNull @NotBlank
        String password,
        @Email @NotNull
        String email,
        @Length(min = 2, max = 20, message = "Your first name length must be in [5, 20]")
        String fname,
        @Length(min = 2, max = 20, message = "Your last name length must be in [5, 20]")
        String lname,
        @Past(message = "Your birthday must be in past")
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dob,
        String phoneNumber,
        Gender gender
) {
}

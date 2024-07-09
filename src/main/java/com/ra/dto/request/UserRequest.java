package com.ra.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRequest {
	@NotEmpty(message = "name must be not empty")
	private String name;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateOfBirth;
	private Boolean gender;
	@NotEmpty(message = "email must be not empty")
	private String email;
	@NotEmpty(message = "address must be not empty")
	private String address;
}

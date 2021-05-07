package com.diotrainning.personapi.dto;

import com.diotrainning.personapi.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {
    private Long id;

    @Enumerated
    private PhoneType type;

    @NotEmpty
    @Size(min = 13, max = 16)
    private String number;
}

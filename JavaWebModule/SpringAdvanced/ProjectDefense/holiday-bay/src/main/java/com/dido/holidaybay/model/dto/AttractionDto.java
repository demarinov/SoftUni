package com.dido.holidaybay.model.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Lob;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttractionDto {

    private String name;

    private String address;

    private String content;

    private String additionalInfo;
}

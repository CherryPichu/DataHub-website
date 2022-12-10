package com.example.datahubwebsite.Models.DTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class) // JSON print를 위해서 사용
public class DataDto {
    private int data_id;
    private int location_id;
    private String data;
    private String CreateAt;
}

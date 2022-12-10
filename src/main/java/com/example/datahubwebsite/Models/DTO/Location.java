package com.example.datahubwebsite.Models.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class) // JSON print를 위해서 사용
public class Location {
    private int location_id;
    private double lat;
    private double lng;
    private String fieldname;
    private int user_no;
    private String detail;

    public Location(double lat, double lng, String fieldname, int user_no, String detail) {
        this.lat = lat;
        this.lng = lng;
        this.fieldname = fieldname;
        this.user_no = user_no;
        this.detail = detail;
    }
}

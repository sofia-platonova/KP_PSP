package com.example.back.dto;

import com.example.back.models.Film;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDto {

    String date;
    int id;
    int rating;
    Film film;
}

package com.example.fileupload.controller;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
public class RequestDto {

    @NotBlank
    private String name;

    @Max(30)
    private Integer age;
}

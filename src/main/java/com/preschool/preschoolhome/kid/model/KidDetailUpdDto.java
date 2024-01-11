package com.preschool.preschoolhome.kid.model;

import lombok.Data;

@Data
public class KidDetailUpdDto {
    private int ikid;
    private int irank;
    private int height;
    private int weight;
    private int polite;
    private int activity;
    private int creativity;
    private String growthMemo;
    private String createdAt;
}

package com.preschool.preschoolhome.kid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GrowhCheck {
    private int ikid;
    private String growthDate;
    private String bodyDate;
    private int bodyQuarterly;
    private int growthQuarterly;
}

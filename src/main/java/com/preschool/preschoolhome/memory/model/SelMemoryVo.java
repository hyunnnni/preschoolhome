package com.preschool.preschoolhome.memory.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SelMemoryVo {
    private List<Integer> ikid;
    private String memoryTitle;
    private String memoryContents;
    private List<String> memoryPic = new ArrayList<>(); //빈방 만들어줌

}

package com.preschool.preschoolhome.notice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DelNoticeDto {

    private int inotice;
    private int ikid;
    private int loginUser;
    private int level;
}

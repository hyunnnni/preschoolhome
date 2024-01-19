package com.preschool.preschoolhome.preschool;

import com.preschool.preschoolhome.preschool.model.KidProfileVo;
import com.preschool.preschoolhome.preschool.model.TeacherProfileVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PreschoolMapper {

    List<KidProfileVo> getKidProfile();
    List<TeacherProfileVo> getTeacherProfile();
}

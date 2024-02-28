package com.preschool.preschoolhome.openApi.medel;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.ToString;

@ToString
public class NightHospitalVo {
    private String sigunNm;
    private String indutypeNm;
    private String medcareFacltNm;
    private String medcareFacltTelNo;
    private String refineLotNoAddr;
    private String refineRoadnmAddr;
    private String hmpgAddr;
    private String refineZipCd;
    private double refineWgs84Logt; //경도
    private double refineWgs84Lat; //위도

    @JsonGetter("sigunNm")  //받고싶은 이름
    public String getSigunNm() {
        return sigunNm;
    }
    @JsonGetter("indutypeNm")
    public String getIndutypeNm() {
        return indutypeNm;
    }
    @JsonGetter("medcareFacltNm")
    public String getMedcareFacltNm() {
        return medcareFacltNm;
    }
    @JsonGetter("medcareFacltTelNo")
    public String getMedcareFacltTelNo() {
        return medcareFacltTelNo;
    }
    @JsonGetter("refineLotNoAddr")
    public String getRefineLotNoAddr() {
        return refineLotNoAddr;
    }
    @JsonGetter("refineRoadnmAddr")
    public String getRefineRoadnmAddr() {
        return refineRoadnmAddr;
    }
    @JsonGetter("refineZipCd")
    public String getRefineZipCd() {
        return refineZipCd;
    }
    @JsonGetter("hmpgAddr")
    public String getHmpgAddr() {return hmpgAddr;}
    @JsonGetter("refineWgs84Logt")
    public double getRefineWgs84Logt() {
        return refineWgs84Logt;
    }
    @JsonGetter("refineWgs84Lat")
    public double getRefineWgs84Lat() {
        return refineWgs84Lat;
    }

    @JsonSetter("SIGUN_NM") //데이터 상의 이름
    public void setSigunNm(String sigunNm) {
        this.sigunNm = sigunNm;
    }
    @JsonSetter("INDUTYPE_NM")
    public void setIndutypeNm(String indutypeNm) {
        this.indutypeNm = indutypeNm;
    }
    @JsonSetter("MEDCARE_FACLT_NM")
    public void setMedcareFacltNm(String medcareFacltNm) {
        this.medcareFacltNm = medcareFacltNm;
    }
    @JsonSetter("MEDCARE_FACLT_TELNO")
    public void setMedcareFacltTelNo(String medcareFacltTelNo) {
        this.medcareFacltTelNo = medcareFacltTelNo;
    }
    @JsonSetter("REFINE_LOTNO_ADDR")
    public void setRefineLotNoAddr(String refineLotNoAddr) {
        this.refineLotNoAddr = refineLotNoAddr;
    }
    @JsonSetter("REFINE_ROADNM_ADDR")
    public void setRefineRoadnmAddr(String refineRoadnmAddr) {
        this.refineRoadnmAddr = refineRoadnmAddr;
    }
    @JsonSetter("REFINE_ZIP_CD")
    public void setRefineZipCd(String refineZipCd) {
        this.refineZipCd = refineZipCd;
    }
    @JsonSetter("HMPG_ADDR")
    public void setHmpgAddr(String hmpgAddr) {
        this.hmpgAddr = hmpgAddr;
    }
    @JsonSetter("REFINE_WGS84_LOGT")
    public void setRefineWgs84Logt(double refineWgs84Logt) {
        this.refineWgs84Logt = refineWgs84Logt;
    }
    @JsonSetter("REFINE_WGS84_LAT")
    public void setRefineWgs84Lat(double refineWgs84Lat) {
        this.refineWgs84Lat = refineWgs84Lat;
    }




}

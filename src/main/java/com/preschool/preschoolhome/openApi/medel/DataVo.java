package com.preschool.preschoolhome.openApi.medel;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@ToString
public class DataVo {
    private String sigunNm;
    private String sigunCd;
    private String facltNm;
    private String telNo;
    private String appontDate;
    private String dataStdDate;
    private String refineLotNoAddr;
    private String refineRoadnmAddr;
    private String refineZipCd;
    private double refineWgs84Logt; //경도
    private double refineWgs84Lat; //위도

    @JsonGetter("sigunNm")  //받고싶은 이름
    public String getSigunNm() {
        return sigunNm;
    }
    @JsonGetter("sigunCd")
    public String getSigunCd() {
        return sigunCd;
    }
    @JsonGetter("facltNm")
    public String getFacltNm() {
        return facltNm;
    }
    @JsonGetter("telNo")
    public String getTelNo() {
        return telNo;
    }
    @JsonGetter("appontDate")
    public String getAppontDate() {
        return appontDate;
    }
    @JsonGetter("dataStdDate")
    public String getDataStdDate() {
        return dataStdDate;
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
    @JsonSetter("SIGUN_CD")
    public void setSigunCd(String sigunCd) {
        this.sigunCd = sigunCd;
    }
    @JsonSetter("FACLT_NM")
    public void setFacltNm(String facltNm) {
        this.facltNm = facltNm;
    }
    @JsonSetter("TELNO")
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }
    @JsonSetter("APPONT_DE")
    public void setAppontDate(String appontDate) {
        this.appontDate = appontDate;
    }
    @JsonSetter("DATA_STD_DE")
    public void setDataStdDate(String dataStdDate) {
        this.dataStdDate = dataStdDate;
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
    @JsonSetter("REFINE_WGS84_LOGT")
    public void setRefineWgs84Logt(double refineWgs84Logt) {
        this.refineWgs84Logt = refineWgs84Logt;
    }
    @JsonSetter("REFINE_WGS84_LAT")
    public void setRefineWgs84Lat(double refineWgs84Lat) {
        this.refineWgs84Lat = refineWgs84Lat;
    }
}

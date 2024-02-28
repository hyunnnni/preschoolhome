package com.preschool.preschoolhome.openApi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.preschool.preschoolhome.common.utils.OpenApiProperties;
import com.preschool.preschoolhome.openApi.medel.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;

import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;

@Slf4j
@RequiredArgsConstructor
@Service
public class ApiService {
    private final OpenApiProperties openApiProperties;

    public TotalHospitalVo getHospital(HospitalDto dto){

        DefaultUriBuilderFactory factory =
                new DefaultUriBuilderFactory(openApiProperties.getHospital().getBaseUrl());

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)  //baseurl셋팅
                .baseUrl(openApiProperties.getHospital().getBaseUrl())
                .build();

        String json = webClient.get().uri(uriBuilder ->
                uriBuilder  //baseurl뒤부터 주소 셋팅
                .path(openApiProperties.getHospital().getDataUrl())
                .queryParam("Type","json")
                .queryParam("Key",openApiProperties.getHospital().getServiceKey())
                .queryParam("pIndex",dto.getPage())
                .queryParam("pSize",dto.getSize())
                .queryParam("SIGUN_NM",dto.getSigunNm())
                .build()

        ).retrieve()
          .bodyToMono(String.class)
          .block();

        String json2 = webClient.get().uri(uriBuilder ->
                        uriBuilder  //baseurl뒤부터 주소 셋팅
                                .path(openApiProperties.getHospital().getDataUrl())
                                .queryParam("Type","json")
                                .queryParam("Key",openApiProperties.getHospital().getServiceKey())
                                .queryParam("pIndex",1)
                                .queryParam("pSize",684)
                                .queryParam("SIGUN_NM",dto.getSigunNm())
                                .build()

                ).retrieve()
                .bodyToMono(String.class)
                .block();


        ObjectMapper om  = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
                        ,false);

        try {
            JsonNode jsonNode = om.readTree(json); //문자열을 트리형식으로 정렬
            JsonNode jsonNode2 = om.readTree(json2); //문자열을 트리형식으로 정렬
            List<HospitalVo> dataList = om.convertValue(jsonNode
                            .at("/TbChildnatnPrvntncltnmdnstM/1/row")
                    , new TypeReference<List<HospitalVo>>() {});

            List<HospitalVo> dataList2 = om.convertValue(jsonNode2
                            .at("/TbChildnatnPrvntncltnmdnstM/1/row")
                    , new TypeReference<List<HospitalVo>>() {});

            /* List<HospitalVo> dataList = om.convertValue(jsonNode
                            .path("/TbChildnatnPrvntncltnmdnstM")
                            .path(1)
                            .path("row")
                    , new TypeReference<List<HospitalVo>>() {});*/

            log.info("dataList:{}",dataList);

            TotalHospitalVo totalData = TotalHospitalVo.builder()
                    .dataList(dataList)
                    .totalData(dataList2.size())
                    .build();

            return totalData;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public TotalNightHospitalVo getnightHospital(HospitalDto dto){

        DefaultUriBuilderFactory factory =
                new DefaultUriBuilderFactory(openApiProperties.getNightHospital().getBaseUrl());

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)  //baseurl셋팅
                .baseUrl(openApiProperties.getNightHospital().getBaseUrl())
                .build();

        String json = webClient.get().uri(uriBuilder ->
                        uriBuilder  //baseurl뒤부터 주소 셋팅
                                .path(openApiProperties.getNightHospital().getDataUrl())
                                .queryParam("Type","json")
                                .queryParam("Key",openApiProperties.getNightHospital().getServiceKey())
                                .queryParam("pIndex",dto.getPage())
                                .queryParam("pSize",dto.getSize())
                                .queryParam("SIGUN_NM",dto.getSigunNm())
                                .build()

                ).retrieve()
                .bodyToMono(String.class)
                .block();

        String json2 = webClient.get().uri(uriBuilder ->
                        uriBuilder  //baseurl뒤부터 주소 셋팅
                                .path(openApiProperties.getNightHospital().getDataUrl())
                                .queryParam("Type","json")
                                .queryParam("Key",openApiProperties.getNightHospital().getServiceKey())
                                .queryParam("pIndex",1)
                                .queryParam("pSize",684)
                                .queryParam("SIGUN_NM",dto.getSigunNm())
                                .build()

                ).retrieve()
                .bodyToMono(String.class)
                .block();


        ObjectMapper om  = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
                        ,false);

        try {
            JsonNode jsonNode = om.readTree(json); //문자열을 트리형식으로 정렬
            JsonNode jsonNode2 = om.readTree(json2); //문자열을 트리형식으로 정렬
            List<NightHospitalVo> dataList = om.convertValue(jsonNode
                            .at("/ChildNightTreatHosptl/1/row")
                    , new TypeReference<List<NightHospitalVo>>() {});

            List<NightHospitalVo> dataList2 = om.convertValue(jsonNode2
                            .at("/ChildNightTreatHosptl/1/row")
                    , new TypeReference<List<NightHospitalVo>>() {});

            /* List<NightHospitalVo> dataList = om.convertValue(jsonNode
                            .path("/ChildNightTreatHosptl")
                            .path(1)
                            .path("row")
                    , new TypeReference<List<NightHospitalVo>>() {});*/

            log.info("dataList:{}",dataList);

            TotalNightHospitalVo totalData = TotalNightHospitalVo.builder()
                    .dataList(dataList)
                    .totalData(dataList2.size())
                    .build();

            return totalData;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

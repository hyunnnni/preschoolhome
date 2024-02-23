package com.preschool.preschoolhome.openApi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.preschool.preschoolhome.common.utils.OpenApiProperties;
import com.preschool.preschoolhome.openApi.medel.DataDto;
import com.preschool.preschoolhome.openApi.medel.DataVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;

import java.util.List;

import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;

@Slf4j
@RequiredArgsConstructor
@Service
public class ApiService {
    private final OpenApiProperties openApiProperties;

    public List<DataVo> getData(DataDto dto){

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



        ObjectMapper om  =new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
                        ,false);

        try {
            JsonNode jsonNode = om.readTree(json); //문자열을 트리형식으로 정렬
            List<DataVo> dataList = om.convertValue(jsonNode
                            .at("/TbChildnatnPrvntncltnmdnstM/1/row")
                    , new TypeReference<List<DataVo>>() {});
           /* List<DataVo> dataList = om.convertValue(jsonNode
                            .path("/TbChildnatnPrvntncltnmdnstM")
                            .path(1)
                            .path("row")
                    , new TypeReference<List<DataVo>>() {});*/

            log.info("dataList:{}",dataList);
            return dataList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

package com.ml.domain.dto;

import lombok.Data;

import java.util.Map;

@Data
public class WebLogAspectDto {

    private String url;

    private String httpMethod;

    private String ip;

    private String classMethod;

    private String args;

    private Map<String,String> paraName;

}

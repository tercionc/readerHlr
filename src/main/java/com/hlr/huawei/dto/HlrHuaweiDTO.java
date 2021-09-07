package com.hlr.huawei.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_EMPTY)
@Builder
public class HlrHuaweiDTO {

    private String msisdn;
    private String imsi;
    private Integer hlr_index;
    private String vlr_address;
    private String cat;
    private String std_charge_global;
    private List<String> optgprs;
    private Map<String, String> others_service;
}

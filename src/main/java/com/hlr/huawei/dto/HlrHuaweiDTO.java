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
    private Integer hlrIndex;
    private String vlrAddress;
    private String cat;
    private String stdChargeGlobal;
    private List<String> optgprs;
    private List<String> cf;
    private Map<String, String> othersService;
}

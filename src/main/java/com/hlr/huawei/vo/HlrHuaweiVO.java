package com.hlr.huawei.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_EMPTY)
@Builder
public class HlrHuaweiVO {

    private String msisdn;
    private String imsi;
    private Integer hlrIndex;
    private String vlrAddress;
    private String cat;
    private String stdChargeGlobal;
    private List<String> optgprs;
    private List<String> cf;
    private Map<String, String> othersServices;
}

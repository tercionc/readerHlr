package com.hlr.huawei.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_EMPTY)
@Builder
public class HlrHuaweiDetailsDTO {
    private String msisdn;
    private String imsi;
    private String imei;
    private String subType;
    private Integer hlrId;
    private String vlr_address;
    private List<String> apnList;
    private List<String> qosList;
    private Integer countApn;
    private String cat;
    private Integer oick;
    private Integer ofa;
    private String sodcf;
    private String osb;
    private String bicro;
    private Integer obcc;
    private Integer osb3;
    private Integer osb4;
    private Integer obr;
    private Integer stype;
    private Integer schar;
    private Integer sosdcf;
    private Integer soclir;
    private Integer obi;
    private Integer obo;
    private Integer bs26;
    private Integer bs3g;
    private Integer ts11;
    private Integer ts21;
    private Integer ts22;
    private String pdpId1;
    private String pdpId2;
    private String pdpId3;
    private String pdpId4;
    private String pdpId5;
    private String pdpId6;
    private String pdpId7;
    private String pdpId8;
    private String pdpId9;
    private String pdpId10;
    private Integer caw;
    private Integer cfb;
    private Integer cfnrc;
    private Integer cfnry;
    private Integer cfu;
    private Integer mpty;
    private Integer hold;
    private String rsa;
    private Integer csp;
    private Integer prbt;
    private Integer osb2;
}

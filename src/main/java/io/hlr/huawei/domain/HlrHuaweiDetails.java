package io.hlr.huawei.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "HLR_HUAWEI_DETAILS")
public class HlrHuaweiDetails {
    @Id
    @Column(name="MSISDN")
    private String msisdn;

    @Column(name = "IMSI")
    private String imsi;

    @Column(name = "SUBTYPE")
    private String subtype;

    @Column(name = "IMEI")
    private String imei;

    @Column(name = "HLR_ID")
    private Integer hlrId;

    @Column(name = "VLR_ADDRESS")
    private String vlrAddress;

    @Column(name = "APN_LIST")
    private String apnList;

    @Column(name = "QOS_LIST")
    private String qosList;

    @Column(name = "COUNT_APN")
    private Integer countApn;

    @Column(name = "CAT")
    private String cat;

    @Column(name = "OICK")
    private Integer oick;

    @Column(name = "OFA")
    private Integer ofa;

    @Column(name = "SODCF")
    private Integer sodcf;

    @Column(name = "OSB")
    private Integer osb;

    @Column(name = "BICRO")
    private String bicro;

    @Column(name = "OBCC")
    private Integer obcc;

    @Column(name = "OSB3")
    private Integer osb3;

    @Column(name = "OSB4")
    private Integer osb4;

    @Column(name = "OBR")
    private Integer obr;

    @Column(name = "STYPE")
    private Integer stype;

    @Column(name = "SCHAR")
    private Integer schar;

    @Column(name = "SOSDCF")
    private Integer sosdcf;

    @Column(name = "SOCLIR")
    private Integer soclir;

    @Column(name = "OBI")
    private Integer obi;

    @Column(name = "OBO")
    private Integer obo;

    @Column(name = "BS26")
    private Integer bs26;

    @Column(name = "BS3G")
    private Integer bs3g;

    @Column(name = "TS11")
    private Integer ts11;

    @Column(name = "TS21")
    private Integer ts21;

    @Column(name = "TS22")
    private Integer ts22;

    @Column(name = "PDPID1")
    private String pdpId1;

    @Column(name = "PDPID2")
    private String pdpId2;

    @Column(name = "PDPID3")
    private String pdpId3;

    @Column(name = "PDPID4")
    private String pdpId4;

    @Column(name = "PDPID5")
    private String pdpId5;

    @Column(name = "PDPID6")
    private String pdpId6;

    @Column(name = "PDPID7")
    private String pdpId7;

    @Column(name = "PDPID8")
    private String pdpId8;

    @Column(name = "PDPID9")
    private String pdpId9;

    @Column(name = "PDPID10")
    private String pdpId10;

    @Column(name = "CAW")
    private Integer caw;

    @Column(name = "CFB")
    private Integer cfb;

    @Column(name = "CFNRC")
    private Integer cfnrc;

    @Column(name = "CFNRY")
    private Integer cfnry;

    @Column(name = "CFU")
    private Integer cfu;

    @Column(name = "MPTY")
    private Integer mpty;

    @Column(name = "HOLD")
    private Integer hold;

    @Column(name = "RSA")
    private String rsa;

    @Column(name = "CSP")
    private Integer csp;

    @Column(name = "PRBT")
    private Integer prbt;

    @Column(name = "OSB2")
    private Integer osb2;

    @Column(name = "NAM")
    private Integer nam;
}

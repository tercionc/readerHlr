package io.hlr.huawei.service;

import io.hlr.huawei.business.BusinessRules;
import io.hlr.huawei.constants.Constants;
import io.hlr.huawei.dto.HlrHuaweiDTO;
import io.hlr.huawei.dto.HlrHuaweiDetailsDTO;
import io.hlr.huawei.enumerator.GsType;
import io.hlr.huawei.enumerator.OdbplmnType;
import io.hlr.huawei.enumerator.SsCodeType;
import io.hlr.huawei.enumerator.TelephonyBasicServiceType;
import io.hlr.huawei.vo.HlrHuaweiVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__({@Lazy}))
@Log4j2
@Service
public class ProcessorHlrService {

    @Autowired
    private WriterHlrService writerHlrService;

    @Autowired
    private BusinessRules businessRules;

    public void process(List<HlrHuaweiVO> vos) {

        List<HlrHuaweiDTO> hlrHuaweiDTOs = vos.stream()
                                              .map(this::mapperVoToDTO)
                                              .collect(Collectors.toList());
        List<HlrHuaweiDetailsDTO> hlrHuaweiDetailsDTOS = new ArrayList<>();


        hlrHuaweiDTOs.forEach( dto -> hlrHuaweiDetailsDTOS.add(HlrHuaweiDetailsDTO.builder()
                .msisdn(dto.getMsisdn())
                .hlrId(dto.getHlrIndex())
                .imsi(dto.getImsi())
                .vlrAddress(Optional.ofNullable(dto.getVlrAddress()).isPresent() ? dto.getVlrAddress() : null)
                .cat(Optional.ofNullable(dto.getCat()).isPresent() ? dto.getCat() : Constants.DEFAULT_VALUE.toString())
                .schar(Optional.ofNullable(dto.getStdChargeGlobal()).isPresent() ? businessRules.mapperSchar(dto.getStdChargeGlobal()) :
                        Constants.DEFAULT_VALUE)
                .apnList(Optional.ofNullable(dto.getOptgprs()).isPresent() ? businessRules.mapperApnList(dto.getOptgprs()) : null)
                .qosList(Optional.ofNullable(dto.getOptgprs()).isPresent() ? businessRules.mapperQosList(dto.getOptgprs()) : null)
                .countApn(Optional.ofNullable(dto.getOptgprs()).isPresent() ? businessRules.mapperCountApn(dto.getOptgprs()) : 0)
                .subType(String.valueOf(businessRules.mapperSubType(dto.getOthersService())))
                .imei(businessRules.mapperImei(dto.getOthersService()))
                .oick(businessRules.mapperOick(dto.getOthersService()))
                .ofa(businessRules.mapperOfa(dto.getOthersService()))
                .sodcf(Optional.ofNullable(dto.getCf()).isPresent() ? businessRules.mapperSodcf(dto.getCf()) : 0)
                .osb(businessRules.mapperOsb(dto.getOthersService(), OdbplmnType.PLMN1))
                .obcc(businessRules.mapperGs(dto.getOthersService(), GsType.PLMNSS9))
                .osb3(businessRules.mapperOsb(dto.getOthersService(), OdbplmnType.PLMN3))
                .osb4(businessRules.mapperOsb(dto.getOthersService(), OdbplmnType.PLMN4))
                .obr(businessRules.mapperObr(dto.getOthersService()))
                .stype(businessRules.mapperStype(dto.getOthersService()))
                .sosdcf(Optional.ofNullable(dto.getCf()).isPresent() ? businessRules.mapperSoscf(dto.getCf()) : 0)
                .nam(businessRules.mapperNam(dto.getCurrentNam()))
                .soclir(businessRules.mapperSoclir(dto.getOthersService()))
                .obi(0)
                .obo(0)
                .bs26(businessRules.mapperTsOrBs(dto.getOthersService(), TelephonyBasicServiceType.BS26))
                .bs3g(businessRules.mapperTsOrBs(dto.getOthersService(), TelephonyBasicServiceType.BS3G))
                .ts11(businessRules.mapperTsOrBs(dto.getOthersService(), TelephonyBasicServiceType.TS11))
                .ts21(businessRules.mapperTsOrBs(dto.getOthersService(), TelephonyBasicServiceType.TS21))
                .ts22(businessRules.mapperTsOrBs(dto.getOthersService(), TelephonyBasicServiceType.TS22))
                .pdpId1(Optional.ofNullable(dto.getOptgprs()).isPresent() ? businessRules.mapperPdpId(dto.getOptgprs(), 1) : null)
                .pdpId2(Optional.ofNullable(dto.getOptgprs()).isPresent() ?  businessRules.mapperPdpId(dto.getOptgprs(), 2) : null)
                .pdpId3(Optional.ofNullable(dto.getOptgprs()).isPresent() ?  businessRules.mapperPdpId(dto.getOptgprs(), 3) : null)
                .pdpId4(Optional.ofNullable(dto.getOptgprs()).isPresent() ?  businessRules.mapperPdpId(dto.getOptgprs(), 4) : null)
                .pdpId5(Optional.ofNullable(dto.getOptgprs()).isPresent() ?  businessRules.mapperPdpId(dto.getOptgprs(), 5) : null)
                .pdpId6(Optional.ofNullable(dto.getOptgprs()).isPresent() ?  businessRules.mapperPdpId(dto.getOptgprs(), 6) : null)
                .pdpId7(Optional.ofNullable(dto.getOptgprs()).isPresent() ?  businessRules.mapperPdpId(dto.getOptgprs(), 7) : null)
                .pdpId8(Optional.ofNullable(dto.getOptgprs()).isPresent() ?  businessRules.mapperPdpId(dto.getOptgprs(), 8) : null)
                .pdpId9(Optional.ofNullable(dto.getOptgprs()).isPresent() ?  businessRules.mapperPdpId(dto.getOptgprs(), 9) : null)
                .pdpId10(Optional.ofNullable(dto.getOptgprs()).isPresent() ?  businessRules.mapperPdpId(dto.getOptgprs(), 10) : null)
                .caw(businessRules.mapperCaw(dto.getOthersService()))
                .cfb(businessRules.mapperCf(dto.getOthersService(), SsCodeType.CFB))
                .cfnrc(businessRules.mapperCf(dto.getOthersService(), SsCodeType.CFNRC))
                .cfnry(businessRules.mapperCf(dto.getOthersService(), SsCodeType.CFNRY))
                .cfu(businessRules.mapperCf(dto.getOthersService(), SsCodeType.CFU))
                .mpty(businessRules.mapperGs(dto.getOthersService(), GsType.MPTY))
                .hold(businessRules.mapperGs(dto.getOthersService(), GsType.HOLD))
                .rsa(businessRules.mapperRsa(dto.getOthersService()))
                .csp(businessRules.mapperCsp(dto.getOthersService()))
                .prbt(businessRules.mapperGs(dto.getOthersService(), GsType.PLMNSS8))
                .osb2(businessRules.mapperOsb(dto.getOthersService(), OdbplmnType.PLMN2))
                .bicro("0")
                .build()));
        writerHlrService.write(hlrHuaweiDetailsDTOS);
    }


    private HlrHuaweiDTO mapperVoToDTO(HlrHuaweiVO vo) {
        return HlrHuaweiDTO.builder()
                            .cf(vo.getCf())
                            .optgprs(vo.getOptgprs())
                            .cat(vo.getCat())
                            .hlrIndex(vo.getHlrIndex())
                            .imsi(vo.getImsi())
                            .msisdn(vo.getMsisdn())
                            .othersService(vo.getOthersServices())
                            .stdChargeGlobal(vo.getStdChargeGlobal())
                            .vlrAddress(vo.getVlrAddress())
                    .build();


    }
}

package com.hlr.huawei.service;

import com.hlr.huawei.business.BusinessRules;
import com.hlr.huawei.constants.Constants;
import com.hlr.huawei.dto.HlrHuaweiDTO;
import com.hlr.huawei.dto.HlrHuaweiDetailsDTO;
import com.hlr.huawei.vo.HlrHuaweiVO;
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
                                                    .vlr_address(Optional.ofNullable(dto.getVlrAddress()).isPresent() ? dto.getVlrAddress() : null)
                                                    .cat(Optional.ofNullable(dto.getCat()).isPresent() ? dto.getCat() : Constants.DEFAULT_VALUE.toString())
                                                    .schar(Optional.ofNullable(dto.getStdChargeGlobal()).isPresent() ? businessRules.mapperSchar(dto.getStdChargeGlobal()) :
                                                                                                                         Constants.DEFAULT_VALUE)
                                                    .apnList(Optional.ofNullable(dto.getOptgprs()).isPresent() ?  businessRules.mapperApnList(dto.getOptgprs()) : null)
                                                    .qosList(Optional.ofNullable(dto.getOptgprs()).isPresent() ?  businessRules.mapperQosList(dto.getOptgprs()) : null)
                                                    .countApn(Optional.ofNullable(dto.getOptgprs()).isPresent() ?  businessRules.mapperCountApn(dto.getOptgprs()) : 0 )
                                                    .subType(String.valueOf(businessRules.mapperSubType(dto.getOthersService())))
                                                    .imei(businessRules.mapperImei(dto.getOthersService()))
                .build()));
        writerHlrService.write(hlrHuaweiDetailsDTOS);
    }


    private HlrHuaweiDTO mapperVoToDTO(HlrHuaweiVO vo) {
        return HlrHuaweiDTO.builder()
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

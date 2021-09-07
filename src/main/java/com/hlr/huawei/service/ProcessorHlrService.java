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
                                                    .hlrId(dto.getHlr_index())
                                                    .imsi(dto.getImsi())
                                                    .vlr_address(Optional.ofNullable(dto.getVlr_address()).isPresent() ? dto.getVlr_address() : null)
                                                    .cat(Optional.ofNullable(dto.getCat()).isPresent() ? dto.getCat() : Constants.DEFAULT_VALUE.toString())
                                                    .schar(Optional.ofNullable(dto.getStd_charge_global()).isPresent() ? businessRules.schar(dto.getStd_charge_global()) :
                                                                                                                         Constants.DEFAULT_VALUE)
                                                    .apnList(Optional.ofNullable(dto.getOptgprs()).isPresent() ?  businessRules.apnList(dto.getOptgprs()) : null)
                                                    .qosList(Optional.ofNullable(dto.getOptgprs()).isPresent() ?  businessRules.qosList(dto.getOptgprs()) : null)
                                                    .countApn(Optional.ofNullable(dto.getOptgprs()).isPresent() ?  businessRules.countApn(dto.getOptgprs()) : 0 )
                .build()));

        writerHlrService.write(hlrHuaweiDetailsDTOS);


    }


    private HlrHuaweiDTO mapperVoToDTO(HlrHuaweiVO vo) {
        return HlrHuaweiDTO.builder()
                            .optgprs(vo.getOptgprs())
                            .cat(vo.getCat())
                            .hlr_index(vo.getHlr_index())
                            .imsi(vo.getImsi())
                            .msisdn(vo.getMsisdn())
                            .others_service(vo.getOthers_service())
                            .std_charge_global(vo.getStd_charge_global())
                            .vlr_address(vo.getVlr_address())
                    .build();


    }
}

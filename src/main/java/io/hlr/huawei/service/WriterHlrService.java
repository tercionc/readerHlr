package io.hlr.huawei.service;

import io.hlr.huawei.constants.Constants;
import io.hlr.huawei.domain.HlrHuaweiDetails;
import io.hlr.huawei.dto.HlrHuaweiDetailsDTO;
import io.hlr.huawei.repository.HlrHuaweiDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__({@Lazy}))
@Log4j2
@Service
public class WriterHlrService {

    @Autowired
    private HlrHuaweiDetailsRepository repository;

    public void write(List<HlrHuaweiDetailsDTO> list) {
        list.forEach(element -> repository.save(this.mapperDtoToDomain(element)));
    }

    public HlrHuaweiDetails mapperDtoToDomain(final HlrHuaweiDetailsDTO dto) {

        return HlrHuaweiDetails.builder()
                                .msisdn(dto.getMsisdn())
                                .imsi(dto.getImsi())
                                .hlrId(dto.getHlrId())
                                .vlrAddress(dto.getVlrAddress())
                                .cat(dto.getCat())
                                .schar(dto.getSchar())
                                .apnList(Optional.ofNullable(dto.getApnList()).isPresent() ? String.join("-", dto.getApnList()) : null )
                                .qosList(Optional.ofNullable(dto.getQosList()).isPresent() ? String.join("-", dto.getQosList()) : null)
                                .countApn(dto.getCountApn())
                                .subtype(dto.getSubType())
                                .imei(dto.getImei())
                                .oick(dto.getOick())
                                .ofa(dto.getOfa())
                                .sodcf(dto.getSodcf())
                                .obcc(dto.getObcc())
                                .osb3(dto.getOsb3())
                                .osb4(dto.getOsb4())
                                .obr(dto.getObr())
                                .stype(dto.getStype())

                                .bicro(Optional.ofNullable(dto.getBicro()).orElse(Constants.DEFAULT_STRING_VALUE))
                                .bs3g(Optional.ofNullable(dto.getBs3g()).orElse(Constants.DEFAULT_VALUE))
                                .bs26(Optional.ofNullable(dto.getBs26()).orElse(Constants.DEFAULT_VALUE))
                                .cat(Optional.ofNullable(dto.getCat()).orElse(Constants.DEFAULT_STRING_VALUE))
                                .caw(Optional.ofNullable(dto.getCaw()).orElse(Constants.DEFAULT_VALUE))
                                .cfb(Optional.ofNullable(dto.getCfb()).orElse(Constants.DEFAULT_VALUE))
                                .cfnrc(Optional.ofNullable(dto.getCfnrc()).orElse(Constants.DEFAULT_VALUE))
                .build();
    }


}

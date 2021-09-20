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
                                .sosdcf(dto.getSosdcf())
                                .nam(dto.getNam())
                                .soclir(dto.getSoclir())
                                .obi(dto.getObi())
                                .obo(dto.getObo())
                                .bs26(dto.getBs26())
                                .bs3g(dto.getBs3g())
                                .ts11(dto.getTs11())
                                .ts21(dto.getTs21())
                                .ts22(dto.getTs22())
                                .pdpId1(Optional.ofNullable(dto.getPdpId1()).orElse(null))
                                .pdpId2(Optional.ofNullable(dto.getPdpId2()).orElse(null))
                                .pdpId3(Optional.ofNullable(dto.getPdpId3()).orElse(null))
                                .pdpId4(Optional.ofNullable(dto.getPdpId4()).orElse(null))
                                .pdpId5(Optional.ofNullable(dto.getPdpId5()).orElse(null))
                                .pdpId6(Optional.ofNullable(dto.getPdpId6()).orElse(null))
                                .pdpId7(Optional.ofNullable(dto.getPdpId7()).orElse(null))
                                .pdpId8(Optional.ofNullable(dto.getPdpId8()).orElse(null))
                                .pdpId9(Optional.ofNullable(dto.getPdpId9()).orElse(null))
                                .pdpId10(Optional.ofNullable(dto.getPdpId10()).orElse(null))
                                .caw(dto.getCaw())
                                .cfb(dto.getCfb())
                                .cfnrc(dto.getCfnrc())
                                .cfnry(dto.getCfnry())
                                .cfu(dto.getCfu())
                                .mpty(dto.getMpty())
                                .hold(dto.getHold())
                                .rsa(dto.getRsa())
                                .csp(dto.getCsp())
                                .prbt(dto.getPrbt())
                                .osb2(dto.getOsb2())
                                .bicro(Optional.ofNullable(dto.getBicro()).orElse(Constants.DEFAULT_STRING_VALUE)).build();
    }


}

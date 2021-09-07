package com.hlr.huawei.service;

import com.hlr.huawei.dto.HlrHuaweiDTO;
import com.hlr.huawei.dto.HlrHuaweiDetailsDTO;
import com.hlr.huawei.vo.HlrHuaweiVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__({@Lazy}))
@Log4j2
@Service
public class ProcessorHlrService {

    @Autowired
    private WriterHlrService writerHlrService;

    public List<HlrHuaweiVO> process(List<HlrHuaweiVO> vos) {

        List<HlrHuaweiDTO> hlrHuaweiDTOs = vos.stream()
                                              .map(this::mapperVoToDTO)
                                              .collect(Collectors.toList());
        List<HlrHuaweiDetailsDTO> hlrHuaweiDetailsDTOS = new ArrayList<>();

        hlrHuaweiDTOs.forEach( obj -> {
            HlrHuaweiDetailsDTO hlrHuaweiDetailsDTO = new HlrHuaweiDetailsDTO();

            hlrHuaweiDetailsDTO.setMsisdn(obj.getMsisdn());
            hlrHuaweiDetailsDTO.setHlrId(obj.getHlr_index());
            hlrHuaweiDetailsDTO.setImsi(obj.getImsi());
            hlrHuaweiDetailsDTO.setVlr_address(obj.getVlr_address());
            hlrHuaweiDetailsDTO.setCat(obj.getCat());
            if ( !obj.getStd_charge_blobal().isEmpty() ) {
                /*
                  TO DO - Convert Hex to Decimal
                 */
            }


            /*
                public static final String TAG_FIX_MSISDN = "MSISDN";
    public static final String TAG_FIX_IMSI = "IMSI";
    public static final String TAG_FIX_HLR_INDEX = "HLR_INDEX";
    public static final String TAG_FIX_VLR_ADDRESS = "VLR_ADDRESS";
    public static final String TAG_FIX_CAT = "CAT";
    public static final String TAG_FIX_STD_CHARGE_GLOBAL = "STD_CHARGE_GLOBAL";
    public static final String TAG_FIX_OPTGPRS = "OPTGPRS";
             */

        });






        return Arrays.asList();
    }


    private HlrHuaweiDTO mapperVoToDTO(HlrHuaweiVO vo) {
        return HlrHuaweiDTO.builder()
                            .optgprs(vo.getOptgprs())
                            .cat(vo.getCat())
                            .hlr_index(vo.getHlr_index())
                            .imsi(vo.getImsi())
                            .msisdn(vo.getMsisdn())
                            .others_service(vo.getOthers_service())
                            .std_charge_blobal(vo.getStd_charge_blobal())
                            .vlr_address(vo.getVlr_address())
                    .build();


    }
}

package com.hlr.huawei.business;


import com.hlr.huawei.constants.Constants;
import com.hlr.huawei.enumerator.SubType;
import com.hlr.huawei.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Lazy}))
public class BusinessRules {

    public Integer mapperSchar(final String stdChargeGlobal) {
        return Utils.HexToDecimal(stdChargeGlobal);
    }

    public List<String> mapperApnList(final List<String> optgprs) {
        return optgprs.stream()
                .map(list -> Arrays.asList(list.split("-")).get(1))
                .collect(Collectors.toList());
    }

    public List<String> mapperQosList(final List<String> optgprs) {
        return optgprs.stream()
                .map(list -> Arrays.asList(list.split("-")).get(2))
                .collect(Collectors.toList());
    }

    public Integer mapperCountApn(final List<String> optgprs) {
        return Integer.valueOf(String.valueOf(optgprs.stream()
                .map(list -> Arrays.asList(list.split("-")).get(1))
                .count()));
    }

    public int mapperSubType(final Map<String, String> othersServices) {
        if ( Optional.ofNullable(othersServices).isPresent() )
            return SubType.getValue(Arrays.asList(othersServices.getOrDefault(Constants.TAG_VAR_ECATEGORY, "UNKNOWN").split("-")).get(0));
        else
            return SubType.getValue("UNKNOWN");
    }

    public String mapperImei(final Map<String, String> othersService) {
        if ( Optional.ofNullable(othersService).isPresent() )
            return othersService.getOrDefault(Constants.TAG_VAR_EPS_IMEI, "00000000000000") +
                    othersService.getOrDefault(Constants.TAG_VAR_EPS_EPS_IMEISV, "01");
        else
            return "000000000000001";
    }
}

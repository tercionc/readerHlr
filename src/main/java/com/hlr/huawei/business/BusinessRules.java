package com.hlr.huawei.business;


import com.hlr.huawei.constants.Constants;
import com.hlr.huawei.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Log4j2
@RequiredArgsConstructor(onConstructor = @__({@Lazy}))
public class BusinessRules {

    public Integer schar(final String stdChargeGlobal) {
        return Utils.HexToDecimal(stdChargeGlobal);
    }

    public List<String> apnList(final List<String> optgprs) {
        return optgprs.stream()
                .map(list -> Arrays.asList(list.split("-")).get(1))
                .collect(Collectors.toList());
    }

    public List<String> qosList(final List<String> optgprs) {
        return optgprs.stream()
                .map(list -> Arrays.asList(list.split("-")).get(2))
                .collect(Collectors.toList());
    }

    public Integer countApn(final List<String> optgprs) {
        return Integer.valueOf(String.valueOf(optgprs.stream()
                .map(list -> Arrays.asList(list.split("-")).get(1))
                .count()));
    }

}

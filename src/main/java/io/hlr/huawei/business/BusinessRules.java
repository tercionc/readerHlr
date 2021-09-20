package io.hlr.huawei.business;


import io.hlr.huawei.constants.Constants;
import io.hlr.huawei.enumerator.*;
import io.hlr.huawei.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.*;
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
        if (Optional.ofNullable(othersServices).isPresent())
            return SubType.getValue(Arrays.asList(othersServices.getOrDefault(Constants.TAG_VAR_ECATEGORY, "UNKNOWN").split("-")).get(0));
        else
            return SubType.getValue("UNKNOWN");
    }

    public int mapperStype(final Map<String, String> othersServices) {
        if (Optional.ofNullable(othersServices).isPresent())
            return SubType.getValue(Arrays.asList(othersServices.getOrDefault(Constants.TAG_VAR_ECATEGORY, "UNKNOWN").split("-")).get(0));
        else
            return SubType.getValue("UNKNOWN");
    }

    public String mapperImei(final Map<String, String> othersService) {
        if (Optional.ofNullable(othersService).isPresent())
            return othersService.getOrDefault(Constants.TAG_VAR_EPS_IMEI, "00000000000000") +
                    othersService.getOrDefault(Constants.TAG_VAR_EPS_EPS_IMEISV, "01");
        else
            return "000000000000001";
    }

    public Integer mapperOick(final Map<String, String> othersService) {
        try {
            if (Optional.ofNullable(othersService).isPresent() &&
                    othersService.containsKey(Constants.TAG_VAR_EIN)) {
                if (EinType.valueOf(Arrays.asList(othersService.getOrDefault(Constants.TAG_VAR_EIN, "NONE-SK").split("-")).get(0)).equals(EinType.EOICK))
                    return Integer.valueOf(Arrays.asList(othersService.getOrDefault(Constants.TAG_VAR_EIN, "NONE-SK").split("-")).get(1));
                else
                    return 0;
            } else
                return 0;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return 0;
        }
    }

    public Integer mapperOfa(final Map<String, String> othersService) {
       try {
           if (Optional.ofNullable(othersService).isPresent() &&
                   othersService.containsKey(Constants.TAG_VAR_DEFOFAID)) {
               return Integer.valueOf(othersService.getOrDefault(Constants.TAG_VAR_DEFOFAID, "0"));
           } else
               return 0;
       } catch (Exception ex) {
           log.error(ex.getMessage());
           return 0;
       }
    }

    public Integer mapperSodcf(final List<String> cf) {
        try {
            if (Optional.ofNullable(cf).isPresent()) {
                return  Arrays.asList(new ArrayList<>(cf).get(cf.size()-1).split("-")).get(4).equals("YES") ? 1 : 0;
            } else
                return 0;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return 0;
        }
    }

    public Integer mapperSoscf(final List<String> cf) {
        try {
            if (Optional.ofNullable(cf).isPresent()) {
                return  Arrays.asList(new ArrayList<>(cf).get(cf.size()-1).split("-")).get(5).equals("YES") ? 1 : 0;
            } else
                return 0;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return 0;
        }
    }

    public Integer mapperOsb(final Map<String, String> othersService, final OdbplmnType type) {
        try {
            if (Optional.ofNullable(othersService).isPresent() &&
                    othersService.containsKey(Constants.TAG_VAR_ODBPLMN))
                return OdbplmnType.valueOf(othersService.get(Constants.TAG_VAR_ODBPLMN)).equals(type) ? 1 : 0;
            else
                return 0;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return 0;
        }
    }

    public Integer mapperObr(final Map<String, String> othersService) {
        try {
            if (Optional.ofNullable(othersService).isPresent() &&
                    othersService.containsKey(Constants.TAG_VAR_ODBROAM))
                switch ( OdbroamType.valueOf(othersService.getOrDefault(Constants.TAG_VAR_ODBROAM, "NONE")) ) {
                    case ODBOH :
                        return 1;
                    case ODBOHC :
                        return 2;
                    case BROHPLMNCGPRS :
                        return 3;
                    case NONE :
                        return 0;
                    default:
                        return 0;
                }
            else
                return 0;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return 0;
        }
    }

    public Integer mapperNam( final String currentNam) {
        try {
            if (Optional.ofNullable(currentNam).isPresent())
                switch ( CurrentNamType.valueOf(currentNam) ) {
                    case BOTH :
                        return 1;
                    case NONGPRS :
                        return 2;
                    case GPRS :
                        return 3;
                    default:
                        return 0;
                }
            else
                return 0;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return 0;
        }
    }

    public Integer mapperSoclir(final Map<String, String> othersService) {
        try {
            List<GsType> gss;
            if (Optional.ofNullable(othersService).isPresent() &&
                    othersService.containsKey(Constants.TAG_VAR_GS)) {
                gss = Arrays.stream(othersService.getOrDefault(Constants.TAG_VAR_GS, "NONE").split("&"))
                            .map(GsType::valueOf)
                            .collect(Collectors.toList());

                if( gss.contains(GsType.CLIR) &&
                        othersService.containsKey(Constants.TAG_VAR_CLIRES) )
                    return CliresType.valueOf(othersService.getOrDefault(Constants.TAG_VAR_CLIRES, "PERM")).getValue();
                else return 0;
            } else
                return 0;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return 0;
        }
    }

    public Integer mapperTsOrBs(final Map<String, String> othersService, final TelephonyBasicServiceType type) {
        try {
            if ( Optional.ofNullable(othersService).isPresent() ) {
                return Arrays.stream(othersService.getOrDefault(Constants.TAG_VAR_TBS, "NONE").split("&"))
                        .map(TelephonyBasicServiceType::valueOf)
                        .collect(Collectors.toList())
                        .contains(type) ? 1 : 0;
            } else
                return 0;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return 0;
        }
    }

    public Integer mapperCaw(final Map<String, String> othersService) {
        try {
            if ( Optional.ofNullable(othersService).isPresent() &&
                    othersService.containsKey(Constants.TAG_VAR_CW))
                return 1;
            else
                return 0;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return 0;
        }
    }

    public String mapperPdpId(final List<String> optgprs, final Integer position) {
        List<String> apnList = mapperApnList(optgprs);
        List<String> qosList = mapperQosList(optgprs);
        if (apnList.size() >= position && qosList.size() >= position)
            return apnList.get(position-1) + "-" + qosList.get(position-1);
        return null;
    }

    public Integer mapperCf(final Map<String, String> othersService, final SsCodeType type) {
        try {
            if ( Optional.ofNullable(othersService).isPresent())
                return Arrays.asList(othersService.getOrDefault(Constants.TAG_VAR_CF, "NONE").split("&"))
                        .contains(type.toString()) ? 1 : 0;
            else
                return 0;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return 0;
        }
    }

    public Integer mapperGs(final Map<String, String> othersService, final GsType type) {
        try {
            if (Optional.ofNullable(othersService).isPresent() &&
                    othersService.containsKey(Constants.TAG_VAR_GS))
                return Arrays.asList(othersService.getOrDefault(Constants.TAG_VAR_GS, "NONE").split("&"))
                        .contains(type.toString()) ? 1 : 0;
            else
                return 0;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return 0;
        }
    }

    public String mapperRsa(final Map<String, String> othersService) {
        try {
            if ( Optional.ofNullable(othersService).isPresent() &&
                    othersService.containsKey(Constants.TAG_VAR_VLRLIST))
                return othersService.get(Constants.TAG_VAR_VLRLIST);
            else
                return "0";
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return "0";
        }
    }

    public Integer mapperCsp(final Map<String, String> othersService) {
        try {
            if ( Optional.ofNullable(othersService).isPresent() &&
                    othersService.containsKey(Constants.TAG_VAR_CSP))
                return Integer.valueOf(othersService.get(Constants.TAG_VAR_CSP));
            else
                return 0;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return 0;
        }
    }

}

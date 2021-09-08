package com.hlr.huawei.service;

import com.hlr.huawei.config.FileConfig;
import com.hlr.huawei.constants.Constants;
import com.hlr.huawei.vo.HlrHuaweiVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__({@Lazy}))
@Log4j2
@Service
public class ReaderHlrService {

    @Autowired
    private ProcessorHlrService processorHlrService;

    @Autowired
    private WriterHlrService writerHlrService;

    @Autowired
    private FileConfig fileConfig;

    public void process() {

        List<HlrHuaweiVO>  vos = new ArrayList<>();
        int element=0;

        int lengthBuffer = Optional.ofNullable(fileConfig.getChunk()).orElse(1);

        try (FileInputStream inputStream = new FileInputStream(fileConfig.getPath() + "/" + fileConfig.getName()); Scanner sc = new Scanner(inputStream, "UTF-8")) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim().replace(";", "");
                String key;
                String value = "0";

                if (line.contains(Constants.SEPERATOR_EQUAL)) {
                    key = Arrays.stream(line.split(Constants.SEPERATOR_EQUAL)).findFirst().get();
                    value = Arrays.stream(line.split(Constants.SEPERATOR_EQUAL)).collect(Collectors.toList()).get(1);
                } else {
                    key = line;
                }

                switch (key) {
                    case Constants.TAG_FIX_BEGIN:
                        vos.add(HlrHuaweiVO.builder().build());
                        break;
                    case Constants.TAG_FIX_MSISDN:
                        vos.get(element).setMsisdn(value);
                        break;
                    case Constants.TAG_FIX_IMSI:
                        vos.get(element).setImsi(value);
                        break;
                    case Constants.TAG_FIX_HLR_INDEX:
                        vos.get(element).setHlrIndex(Integer.parseInt(value));
                        break;
                    case Constants.TAG_FIX_VLR_ADDRESS:
                        vos.get(element).setVlrAddress(value);
                        break;
                    case Constants.TAG_FIX_CAT:
                        vos.get(element).setCat(value);
                        break;
                    case Constants.TAG_FIX_STD_CHARGE_GLOBAL:
                        vos.get(element).setStdChargeGlobal(value);
                        break;
                    case Constants.TAG_FIX_OPTGPRS:
                        if (Optional.ofNullable(vos.get(element).getOptgprs()).isPresent()) {
                            vos.get(element).getOptgprs().add(value);
                        } else {
                            vos.get(element).setOptgprs(new ArrayList<>());
                            vos.get(element).getOptgprs().add(value);
                        }
                        break;
                    case Constants.TAG_FIX_END:
                        if (vos.size() < lengthBuffer)
                            element++;
                        else {
                            processorHlrService.process(vos);
                            element = 0;
                            vos.clear();
                        }
                        break;
                    default:
                        if (Optional.ofNullable(vos.get(element).getOthersServices()).isPresent()) {
                            vos.get(element).getOthersServices().put(key, value);
                        } else {
                            vos.get(element).setOthersServices(new HashMap<>());
                            vos.get(element).getOthersServices().put(key, value);
                        }
                        break;
                }

            }
            if (vos.size() > 0) {
                processorHlrService.process(vos);
                vos.clear();
            }

            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

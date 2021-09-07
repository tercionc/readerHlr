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

    private int lengthBuffer=2;

    @Autowired
    private ProcessorHlrService processorHlrService;

    @Autowired
    private WriterHlrService writerHlrService;

    @Autowired
    private FileConfig fileConfig;

    public void process() throws IOException {

        FileInputStream inputStream = null;
        Scanner sc = null;
        List<HlrHuaweiVO>  vos = new ArrayList<>();
        int element=0;

        try {
            inputStream = new FileInputStream(fileConfig.getPath() + "/" + fileConfig.getName());
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim().replace(";","");
                String key;
                String value = null;

                if ( line.contains(Constants.SEPERATOR_EQUAL) ) {
                    key = Arrays.stream(line.split(Constants.SEPERATOR_EQUAL)).findFirst().get();
                    value= Arrays.stream(line.split(Constants.SEPERATOR_EQUAL)).collect(Collectors.toList()).get(1);
                } else {
                    key = line;
                }

                if (key.equals(Constants.TAG_FIX_BEGIN)) {
                    vos.add(HlrHuaweiVO.builder().build());
                } else if (key.equals(Constants.TAG_FIX_MSISDN)) {
                    vos.get(element).setMsisdn(value);
                } else if (key.equals(Constants.TAG_FIX_IMSI)) {
                    vos.get(element).setImsi(value);
                } else if (key.equals(Constants.TAG_FIX_HLR_INDEX)) {
                    vos.get(element).setHlr_index(Integer.parseInt(value));
                } else if (key.equals(Constants.TAG_FIX_VLR_ADDRESS)) {
                    vos.get(element).setVlr_address(value);
                } else if (key.equals(Constants.TAG_FIX_CAT)) {
                    vos.get(element).setCat(value);
                } else if (key.equals(Constants.TAG_FIX_STD_CHARGE_GLOBAL)) {
                    vos.get(element).setStd_charge_blobal(value);
                } else if (key.equals(Constants.TAG_FIX_OPTGPRS)) {
                    if (Optional.ofNullable(vos.get(element).getOptgprs()).isPresent()) {
                        vos.get(element).getOptgprs().add(value);
                    } else {
                        vos.get(element).setOptgprs(new ArrayList<>());
                        vos.get(element).getOptgprs().add(value);
                    }
                } else if (key.equals(Constants.TAG_FIX_END)) {
                    if (vos.size() < lengthBuffer)
                        element++;
                    else {
                        processorHlrService.process(vos);
                        element=0;
                        vos.clear();
                    }
                } else {
                    if (Optional.ofNullable(vos.get(element).getOthers_service()).isPresent() ) {
                        vos.get(element).getOthers_service().put(key, value);
                    } else {
                        vos.get(element).setOthers_service(new HashMap<String, String>());
                        vos.get(element).getOthers_service().put(key, value);
                    }
                }

            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }

    }


}

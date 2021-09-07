package com.hlr.huawei.service;

import com.hlr.huawei.repository.HlrHuaweiDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__({@Lazy}))
@Log4j2
@Service
public class WriterHlrService {

    private HlrHuaweiDetailsRepository repository;


}

package com.hlr.huawei;

import com.hlr.huawei.service.ReaderHlrService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class ReaderHlrApplication implements CommandLineRunner {

	@Autowired
	private ReaderHlrService readerHlrService;

	public static void main(String[] args) {

		SpringApplication.run(ReaderHlrApplication.class, args);
	}

	@Override
	public void run(String... args) {
		readerHlrService.process();
	}
}

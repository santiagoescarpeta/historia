package com.ms.historyinquiry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.ms.historyinquiry.model")
public class HistoryinquiryApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistoryinquiryApplication.class, args);
	}

}

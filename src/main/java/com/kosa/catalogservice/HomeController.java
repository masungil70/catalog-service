package com.kosa.catalogservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosa.catalogservice.config.PolarProperties;

@RestController
public class HomeController {
    private final PolarProperties polarProperties;

    public HomeController(PolarProperties polarProperties) {
        this.polarProperties = polarProperties;
    }

    @GetMapping("/")
    public String getGreeting() {
        // return "도서 카달로그에 오신 것을 환영합니다";
        // 설정 데이터 빈에서 가져온 환영 메시지를 사용함
        return polarProperties.getGreeting();

    }

}

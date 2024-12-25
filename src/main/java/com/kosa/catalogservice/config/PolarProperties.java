package com.kosa.catalogservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "polar")
public class PolarProperties {
    private String greeting;

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

}

/*
 * 외부화된 구성 : 하나의 빌드로 여러 설정을 변경 하여 실행 할 수 있음
 * 
 * polar.greeting 속성값 선택단계 및 방법
 * 1. CLI 인수로 설정 : java -jar *.jar --polar.greeting="속성값 설정"
 * 2. JVM 인수로 설정 : java -jar -Dpolar.greeting="속성값 설정"*.jar
 * 3. 환경 변수로 설정 :
 * export POLAR_GREETING=속성값 설정
 * java -jar *.jar
 * 환경 변수명 POLAR_GREETING은 자바에서 속성으로 읽을 때는 polar.greeting으로 변환됨 (이런 것은 완화된
 * 바이딩이라고함)
 * 
 */
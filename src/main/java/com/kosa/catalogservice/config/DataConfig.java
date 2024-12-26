package com.kosa.catalogservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration // 해당 클래스가 스프림 설정의 소스임을 나타낸다
@EnableJdbcAuditing
@EnableJdbcRepositories(basePackages = "com.kosa.catalogservice.domain")
public class DataConfig {

}

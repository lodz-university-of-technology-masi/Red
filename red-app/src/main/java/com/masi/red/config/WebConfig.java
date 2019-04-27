package com.masi.red.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebConfig implements WebMvcConfigurer {

    private final Formatter typeFormatter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(typeFormatter);
    }
}
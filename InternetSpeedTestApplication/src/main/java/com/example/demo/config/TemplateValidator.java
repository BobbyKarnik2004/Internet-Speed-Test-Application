package com.example.demo.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.example.demo.exception.TemplateValidationException;

@Component
public class TemplateValidator implements ApplicationRunner {

    private final TemplateEngine templateEngine;

    public TemplateValidator(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public void run(ApplicationArguments args) {
        validateTemplate("error");
        validateTemplate("speedtest");
    }

    private void validateTemplate(String templateName) {
        try {
            templateEngine.process(templateName, new Context());
        } catch (Exception e) {
            throw new TemplateValidationException(
                "Failed to process template: " + templateName, e
            );
        }
    }
}

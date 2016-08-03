package ru.tecomgroup.mibbrowser.spring.Validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import ru.tecomgroup.mibbrowser.core.model.SnmpConfiguration;

public class SnmpConfigurationValidator {


    public boolean supports(Class<?> clazz) {
        return SnmpConfiguration.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors){
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "port", "port.empty", "Port must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "retries", "retries.empty", "Retries must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "timeOut", "timeOut.empty", "timeOut must not be empty.");
    }
}

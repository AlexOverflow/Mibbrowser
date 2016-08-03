package ru.tecomgroup.mibbrowser.spring.Validators;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import ru.tecomgroup.mibbrowser.core.model.MibBrowserRequest;

public class MibrowserRequestValidator {

    public boolean supports(Class<?> clazz) {
        return MibBrowserRequest.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors){
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hostAddress", "hostAddress.empty", "Address must not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oid", "oid.empty", "Oid must not be empty.");
    }
}

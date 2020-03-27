package com.example.assignment2.validators;

import com.example.assignment2.model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Locale;
import java.util.regex.Pattern;

// Custom validator for manipulating Trainer from the user's endpoint
@Component
public class TrainerValidator implements Validator {

    @Autowired
    MessageSource messageSource;

    @Override
    public boolean supports(Class<?> aClass) {
        return Trainer.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Trainer trainer = (Trainer) o;
        // Regex pattern to that matches all letters, spaces and dashes
        Pattern namesPattern = Pattern.compile("[\\p{L}\\s-]+");
        // Regex pattern to that matches all letters, numbers, spaces, slashes and  dashes
        Pattern subjectPattern = Pattern.compile("[\\p{L}\\d\\s/-]+");
        if (!namesPattern.matcher(trainer.getFirstName()).matches()) {
            errors.rejectValue("firstName", null, messageSource.getMessage("lsd.illegal.char", null, Locale.ENGLISH));
        }
        if (!namesPattern.matcher(trainer.getLastName()).matches()) {
            errors.rejectValue("lastName", null, messageSource.getMessage("lsd.illegal.char", null, Locale.ENGLISH));
        }
        if (!subjectPattern.matcher(trainer.getSubject()).matches() && !trainer.getSubject().isEmpty()) {
            errors.rejectValue("subject", null, messageSource.getMessage("lsdn.illegal.char", null, Locale.ENGLISH));
        }
    }
}

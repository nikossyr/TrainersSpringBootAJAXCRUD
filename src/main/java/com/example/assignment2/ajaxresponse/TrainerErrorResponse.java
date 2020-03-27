package com.example.assignment2.ajaxresponse;

import java.util.List;
import java.util.Map;

// Class to return as a JSON response after server form validation
public class TrainerErrorResponse {

    private boolean validated;
    private Map<Object, List<String>> errorMessages;

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public Map<Object, List<String>> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(Map<Object, List<String>> errorMessages) {
        this.errorMessages = errorMessages;
    }
}

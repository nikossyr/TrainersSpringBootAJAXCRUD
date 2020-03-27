package com.example.assignment2.ajaxresponse;

import com.example.assignment2.model.Trainer;

// Class to return as a JSON response when AJAX asks for a Trainer from the DB
public class TrainerJsonResponse {

    Trainer trainer;
    String message;
    Integer statusCode = -1;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}

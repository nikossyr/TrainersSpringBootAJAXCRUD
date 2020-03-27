package com.example.assignment2.controllers;

import com.example.assignment2.ajaxresponse.TrainerErrorResponse;
import com.example.assignment2.ajaxresponse.TrainerJsonResponse;
import com.example.assignment2.model.Trainer;
import com.example.assignment2.services.ITrainerService;
import com.example.assignment2.validators.TrainerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

// Rest Controller returns only the response body. It doesn't return jsp pages
@RestController
public class AjaxJqueryController {

    @Autowired
    ITrainerService trainerService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    ITrainerService trainerServiceInterface;

    @Autowired
    TrainerValidator trainerValidator;

    //    Method used to merge two lists into one. It is used in case the Collectors.toMap function encounters multiple
//    occurrences of the same key. It adds all values to the key that they correspond.
    private static List<String> mergeEntriesWithDuplicatedKeys(List<String> existingResults, List<String> newResults) {
        List<String> mergedResults = new ArrayList<>();
        mergedResults.addAll(existingResults);
        mergedResults.addAll(newResults);
        return mergedResults;
    }

    // We have to set the class name that it binds. Alternatively it is set to global and it validates ALL model attributes
    @InitBinder("trainer")
    //WebDataBinder is a DataBinder that binds request parameter to JavaBean objects.
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(trainerValidator);
    }

    //     Mapping that returns all Trainers from database to a json response
    @PostMapping(value = "/getTrainer")
    public TrainerJsonResponse getTrainerEdit(@ModelAttribute("trainerId") Integer id) {
        TrainerJsonResponse response = new TrainerJsonResponse();
        Optional<Trainer> trainerOptional = trainerService.getTrainerById(id);
        if (trainerOptional.isPresent()) {
            response.setStatusCode(1);
            response.setTrainer(trainerOptional.get());
        } else {
            response.setStatusCode(-1);
            response.setMessage(messageSource.getMessage("trainer.not.found", null, Locale.ENGLISH));
        }
        return response;
    }

    //     Mapping that validates whether the insert Trainer form has been completed correctly and insert the trainer to the DB
//     or return the error messages in json
    @PostMapping("/insertTrainer")
    public TrainerErrorResponse insertTrainer(@Valid @ModelAttribute("trainer") Trainer trainer,
                                              BindingResult bindingResult) {

        TrainerErrorResponse response = new TrainerErrorResponse();

        if (!bindingResult.hasErrors()) {
            response.setValidated(true);
            trainerServiceInterface.saveTrainer(trainer);
        } else {
            response.setValidated(false);
            Map<Object, List<String>> errors = bindingResult.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(
                                    FieldError::getField,
                                    fieldError -> Collections.singletonList(fieldError.getDefaultMessage()),
                                    AjaxJqueryController::mergeEntriesWithDuplicatedKeys
                            ));
            response.setErrorMessages(errors);
        }
        return response;
    }

}

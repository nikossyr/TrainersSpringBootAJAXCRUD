package com.example.assignment2.controllers;

import com.example.assignment2.model.Trainer;
import com.example.assignment2.services.ITrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DefaultController {

    @Autowired
    ITrainerService trainerServiceInterface;

    //    Mapping that handles the first time the user accesses the Web App
    @GetMapping("/")
    public String getWelcomePage(ModelMap modelMap) {
        modelMap.addAttribute("emptyTrainer", new Trainer());
        modelMap.addAttribute("trainers", trainerServiceInterface.getAllTrainers());
        return "index";
    }

    //    Mapping that returns a view with all the Trainers. It is used as an AJAX response, but it returns a partial html
//    page (a table in particular) so it is placed in the "regular" controller
    @GetMapping(value = "/getAllTrainers")
    public String getAllTrainersTable(ModelMap modelMap) {
        modelMap.addAttribute("trainers", trainerServiceInterface.getAllTrainers());
        return "trainersTable";
    }

    //    Mapping that handles the deleting of a Trainer from the Database
    @PostMapping("/deleteTrainer")
    public String deleteTrainer(@ModelAttribute("trainer") Trainer trainer) {
        trainerServiceInterface.deleteTrainer(trainer);
        return "redirect:/";
    }

}

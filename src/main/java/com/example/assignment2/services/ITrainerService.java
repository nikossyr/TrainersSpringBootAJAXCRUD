package com.example.assignment2.services;

import com.example.assignment2.model.Trainer;

import java.util.List;
import java.util.Optional;

public interface ITrainerService {

    public List<Trainer> getAllTrainers();

    public void saveTrainer(Trainer trainer);

    public Optional<Trainer> getTrainerById(Integer id);

    public void deleteTrainer(Trainer trainer);
}

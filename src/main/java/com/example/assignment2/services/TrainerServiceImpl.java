package com.example.assignment2.services;

import com.example.assignment2.model.Trainer;
import com.example.assignment2.repositories.ITrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements ITrainerService {

    @Autowired
    ITrainerRepository trainerRepository;

    @Override
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @Override
    public void saveTrainer(Trainer trainer) {
        trainerRepository.save(trainer);
    }

    @Override
    public Optional<Trainer> getTrainerById(Integer id) {
        return trainerRepository.findById(id);
    }

    @Override
    public void deleteTrainer(Trainer trainer) {
        trainerRepository.delete(trainer);
    }

}

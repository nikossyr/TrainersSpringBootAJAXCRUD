package com.example.assignment2.repositories;

import com.example.assignment2.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrainerRepository extends JpaRepository<Trainer, Integer> {

}

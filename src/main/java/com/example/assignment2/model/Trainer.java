package com.example.assignment2.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "trainers", schema = "private_school")
public class Trainer {

    private int trainerId;
    private String firstName;
    private String lastName;
    private String subject;

    @Id
    @Column(name = "trainer_id")
    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    @Basic
    @Column(name = "first_name")
    @NotBlank(message = "First Name is mandatory")
    @Size(min = 3, max = 45, message = "First Name must be between 3 and 45 characters long")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    @NotBlank(message = "Last Name is mandatory")
    @Size(min = 3, max = 45, message = "Last Name must be between 3 and 45 characters long")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return trainerId == trainer.trainerId &&
                Objects.equals(firstName, trainer.firstName) &&
                Objects.equals(lastName, trainer.lastName) &&
                Objects.equals(subject, trainer.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainerId, firstName, lastName, subject);
    }
}

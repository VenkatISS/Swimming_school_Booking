package com.hemanth.entities;

import java.util.ArrayList;
import java.util.List;

public class SwimmingSession {
    private int id;
    private Slots slots;
    private Trainer trainer;
    private SessionDay day;
    private int grade;
    private List<Trainee> attendees;

    // only for testing
    public SwimmingSession(int id) {
        this.id = id;
        this.grade = 2;
        this.attendees = new ArrayList<>();
    }
    // only for testing
    public SwimmingSession(int id, int grade) {
        this.id = id;
        this.grade = grade;
        this.attendees = new ArrayList<>();
    }

    public SwimmingSession(int id, Slots slots, SessionDay day, int grade) {
        this.id = id;
        this.slots = slots;
        this.day = day;
        this.grade = grade;
        this.attendees = new ArrayList<>();
    }

    public SwimmingSession(int id, Slots slots, Trainer trainer, SessionDay day, int grade) {
        this.id = id;
        this.slots = slots;
        this.trainer = trainer;
        this.day = day;
        this.grade = grade;
        this.attendees = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFull() {
        return this.attendees.size() == 4;
    }

    public void addAttendee(Trainee trainee) {
        this.attendees.add(trainee);
    }

    public void removeAttendee(Trainee trainee) {
        this.attendees.remove(trainee);
    }

    public Slots getSlots() {
        return slots;
    }

    public void setSlots(Slots slots) {
        this.slots = slots;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public SessionDay getDay() {
        return day;
    }

    public void setDay(SessionDay day) {
        this.day = day;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public List<Trainee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Trainee> attendees) {
        this.attendees = attendees;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SwimmingSession other = (SwimmingSession) obj;
        return id == other.id;
    }
}

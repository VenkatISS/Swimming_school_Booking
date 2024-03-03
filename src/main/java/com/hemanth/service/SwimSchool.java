package com.hemanth.service;

import com.hemanth.entities.*;
import com.hemanth.strategies.GenerateReportStrategy;
import com.hemanth.strategies.GenerateTrainersReportStrategy;
import com.hemanth.strategies.GenerateTraineesReportStrategy;
import com.hemanth.exceptions.*;
import java.util.ArrayList;
import java.util.List;

public class SwimSchool implements ISwimSchool {

    private List<SwimmingSession> sessions;
    private List<Trainer> trainers;
    private List<Trainee> trainees;
    private List<Report> reports;
    private GenerateReportStrategy generateReportStrategy;

    public SwimSchool(){
        this.sessions = new ArrayList<>();
        this.trainers = new ArrayList<>();
        this.trainees = new ArrayList<>();
        this.reports = new ArrayList<>();
    }

    @Override
    public void assignTrainerToSession(Trainer trainer, SwimmingSession session) throws SwimmingSessionAlreadyHasTrainerException {
        if(session.getTrainer() != null){
            throw new SwimmingSessionAlreadyHasTrainerException("This session already has a Trainer");
        }
        session.setTrainer(trainer);
    }

    @Override
    public void addTrainee(Trainee trainee) throws TraineeAlreadyExistsException {
        if(trainees.contains(trainee)){
            throw new TraineeAlreadyExistsException("Trainee already exists");
        }
        trainees.add(trainee);
    }

    @Override
    public void removeTrainee(Trainee trainee) {
        trainees.remove(trainee);
    }

    @Override
    public void addTrainer(Trainer trainer) throws TrainerAlreadyExistsException {
        if(trainers.contains(trainer)){
           throw new TrainerAlreadyExistsException("Trainer already exists");
        }
        trainers.add(trainer);
    }

    @Override
    public void removeTrainer(Trainer trainer) {
        trainers.remove(trainer);
    }

    @Override
    public void addSession(SwimmingSession session) {
        if(!sessions.contains(session)){
            sessions.add(session);
        }
        sessions.add(session);
    }

    @Override
    public void removeSession(SwimmingSession session) {
        sessions.remove(session);
    }

    @Override
    public void viewSlotsByDay(SessionDay day) {
        List<SwimmingSession> sessionsByDay = sessions.stream()
                .filter(session -> session.getDay() == day)
                .toList();
        printSeparator();
        System.out.println("Swimming Sessions on " + day + ":");
        printSeparator();
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Session ID", "Day", "Time", "Grade", "Trainer");
        for(SwimmingSession session : sessionsByDay){
            System.out.printf("%-20s%-20s%-20s%-20s%-20s\n",
                    session.getId(),
                    session.getDay(),
                    session.getSlots(),
                    session.getGrade(),
                    session.getTrainer().getName()
            );
        }
        printSeparator();
    }

    @Override
    public void viewSlotsByGrade(int grade) {
        List<SwimmingSession> sessionsByGrade = sessions.stream()
                .filter(session -> session.getGrade() == grade)
                .toList();
        printSeparator();
        System.out.println("Swimming Sessions for Grade " + grade + ":");
        printSeparator();
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Session ID", "Day", "Time", "Grade", "Trainer");
        for(SwimmingSession session : sessionsByGrade){
            System.out.printf("%-20s%-20s%-20s%-20s%-20s\n",
                    session.getId(),
                    session.getDay(),
                    session.getSlots(),
                    session.getGrade(),
                    session.getTrainer().getName()
            );
        }
        printSeparator();
    }

    @Override
    public void viewSlotsByTrainer(Trainer trainer) {
        List<SwimmingSession> sessionsBytrainer = sessions.stream()
                .filter(session -> session.getTrainer().equals(trainer))
                .toList();
        printSeparator();
        System.out.println("Swimming Sessions by " + trainer.getName() + ":");
        printSeparator();
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Session ID", "Day", "Time", "Grade", "Trainer");
        for(SwimmingSession session : sessionsBytrainer){
            System.out.printf("%-20s%-20s%-20s%-20s%-20s\n",
                    session.getId(),
                    session.getDay(),
                    session.getSlots(),
                    session.getGrade(),
                    session.getTrainer().getName()
            );
        }
        printSeparator();
    }

    @Override
    public void bookSession(Trainee trainee, SwimmingSession session) throws NotEligibleForSessionException, SessionAlreadyBookedException, SessionIsFullException {
        if(!trainee.isEligibleForSession(session)){
            throw new NotEligibleForSessionException("You are not eligible for this session");
        }
        if(trainee.isSessionBooked(session)){
           throw new SessionAlreadyBookedException("You are already attending this session");
        }
        if(session.isFull()){
           throw new SessionIsFullException("This session is full");
        }
        session.addAttendee(trainee);
        trainee.bookSession(session);
    }

    @Override
    public void cancelSession(Trainee trainee, SwimmingSession session) throws SessionNotBookedException, SessionIsAttendedException {
        if(!trainee.isSessionBooked(session)){
            throw new SessionNotBookedException("You are not attending this session");
        }
        if(trainee.isSessionAttended(session)){
            throw new SessionIsAttendedException("You cannot cancel a session you have already attended");
        }
        session.removeAttendee(trainee);
        trainee.cancelSession(session);
    }

    @Override
    public void attendSession(Trainee trainee, SwimmingSession session) throws SessionNotBookedException {
        if(!trainee.isSessionBooked(session)){
            throw new SessionNotBookedException("You can't attend a session you haven't booked");
        }
        trainee.attendSession(session);
    }

    @Override
    public void writeReport(Trainee trainee, SwimmingSession session, int rating) throws RatingOutOfRangeException, WriteReportException {
        if(rating < 1 || rating > 5){
            throw new RatingOutOfRangeException("Rating must be between 1 and 5");
        }
        System.out.println("session.getAttendees().contains(trainee)");
        System.out.println(session.getAttendees().contains(trainee));
        if(!session.getAttendees().contains(trainee)){
           throw new WriteReportException("You can't write a report for a session you haven't attended");
        }
        reports.add(
                new Report(trainee, rating, session)
        );
    }

    @Override
    public void generateTraineeReport() {
        generateReportStrategy = new GenerateTraineesReportStrategy();
        generateReportStrategy.generateReport(this);
    }

    @Override
    public void generateTrainerReport() {
        generateReportStrategy = new GenerateTrainersReportStrategy();
        generateReportStrategy.generateReport(this);
    }

    public List<SwimmingSession> getSessions() {
        return sessions;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public List<Report> getReports() {
        return reports;
    }

    private void printSeparator(){
        System.out.println("---------------------------------------------");
    }
}

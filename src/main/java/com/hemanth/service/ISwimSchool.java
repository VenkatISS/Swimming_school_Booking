package com.hemanth.service;

import com.hemanth.entities.SessionDay;
import com.hemanth.entities.SwimmingSession;
import com.hemanth.entities.Trainee;
import com.hemanth.entities.Trainer;
import com.hemanth.exceptions.*;

public interface ISwimSchool {

    void viewSlotsByDay(SessionDay day);
    void viewSlotsByGrade(int grade);
    void viewSlotsByTrainer(Trainer trainer);

    void bookSession(Trainee trainee, SwimmingSession session) throws NotEligibleForSessionException, SessionAlreadyBookedException, SessionIsFullException;
    void cancelSession(Trainee trainee, SwimmingSession session) throws SessionNotBookedException, SessionIsAttendedException;
    void attendSession(Trainee trainee, SwimmingSession session) throws SessionNotBookedException;

    void assignTrainerToSession(Trainer trainer, SwimmingSession session) throws SwimmingSessionAlreadyHasTrainerException;

    void addTrainee(Trainee trainee) throws TraineeAlreadyExistsException;
    void removeTrainee(Trainee trainee);

    void addTrainer(Trainer trainer) throws TrainerAlreadyExistsException;
    void removeTrainer(Trainer trainer);

    void addSession(SwimmingSession session);
    void removeSession(SwimmingSession session);

    void writeReport(Trainee trainee, SwimmingSession session, int rating) throws RatingOutOfRangeException, WriteReportException;

    void generateTraineeReport();
    void generateTrainerReport();
}

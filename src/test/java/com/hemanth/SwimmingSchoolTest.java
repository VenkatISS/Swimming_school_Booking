package com.hemanth;

import com.hemanth.exceptions.*;
import com.hemanth.entities.Report;
import com.hemanth.entities.SwimmingSession;
import com.hemanth.entities.Trainee;
import com.hemanth.entities.Trainer;
import com.hemanth.service.SwimSchool;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class SwimmingSchoolTest {

    private SwimSchool swimmingSchool;
    private Trainee trainee;
    private SwimmingSession session;
    @Before
    public void setUp() throws TraineeAgeException {
        swimmingSchool = new SwimSchool();
        trainee = new Trainee("Surya","Male", 10,"+94771234568",1);
        session = new SwimmingSession(1234,2);
    }


    @Test
    public void testAddTrainee_Successful() throws TraineeAlreadyExistsException, TraineeAgeException {
        swimmingSchool.addTrainee(trainee);
        assertTrue(swimmingSchool.getTrainees().contains(trainee));
    }

    @Test(expected = TraineeAlreadyExistsException.class)
    public void testAddTrainee_AlreadyExists() throws TraineeAlreadyExistsException, TraineeAgeException {
        swimmingSchool.addTrainee(trainee); // Adding the trainee once
        swimmingSchool.addTrainee(trainee); // Trying to add the same trainee again should throw an exception
    }

    @Test
    public void testRemoveTrainee_Successful() throws TraineeAlreadyExistsException, TraineeAgeException {
        swimmingSchool.addTrainee(trainee);
        assertTrue(swimmingSchool.getTrainees().contains(trainee));
        swimmingSchool.removeTrainee(trainee);
        assertFalse(swimmingSchool.getTrainees().contains(trainee));
    }

    @Test
    public void testAddTrainer_Successful() throws TrainerAlreadyExistsException {
        Trainer trainer = new Trainer("Trainer John");
        swimmingSchool.addTrainer(trainer);
        assertTrue(swimmingSchool.getTrainers().contains(trainer));
    }

    @Test(expected = TrainerAlreadyExistsException.class)
    public void testAddTrainer_AlreadyExists() throws TrainerAlreadyExistsException {
        Trainer trainer = new Trainer("Trainer Jane");
        swimmingSchool.addTrainer(trainer); // Adding the trainer once
        swimmingSchool.addTrainer(trainer); // Trying to add the same trainer again should throw an exception
    }

    @Test
    public void testRemovetrainer_Successful() throws TrainerAlreadyExistsException {
        Trainer trainer = new Trainer("Trainer Alice");
        swimmingSchool.addTrainer(trainer);
        assertTrue(swimmingSchool.getTrainers().contains(trainer));
        swimmingSchool.removeTrainer(trainer);
        assertFalse(swimmingSchool.getTrainers().contains(trainer));
    }

    @Test
    public void testAssignTrainerToSession_Successful() throws SwimmingSessionAlreadyHasTrainerException {
        Trainer trainer = new Trainer("Trainer John");
        SwimmingSession session = new SwimmingSession(1);
        swimmingSchool.assignTrainerToSession(trainer, session);
        assertEquals(trainer, session.getTrainer());
    }

    @Test(expected = SwimmingSessionAlreadyHasTrainerException.class)
    public void testAssignTrainerToSession_AlreadyHasTrainer() throws SwimmingSessionAlreadyHasTrainerException {
        Trainer trainer1 = new Trainer("Trainer Alice");
        Trainer trainer2 = new Trainer("Trainer Bob");
        SwimmingSession session = new SwimmingSession(2);
        swimmingSchool.assignTrainerToSession(trainer1, session); // Assigning the first trainer
        swimmingSchool.assignTrainerToSession(trainer2, session); // Trying to assign another trainer should throw an exception
    }

    @Test
    public void testBookSession_Successful() throws NotEligibleForSessionException, SessionAlreadyBookedException, SessionIsFullException, TraineeAlreadyExistsException {
        swimmingSchool.addTrainee(trainee);
        swimmingSchool.addSession(session);
        swimmingSchool.bookSession(trainee, session);
        assertTrue(trainee.isSessionBooked(session));
        assertTrue(session.getAttendees().contains(trainee));
    }

    @Test(expected = SessionAlreadyBookedException.class)
    public void testBookSession_AlreadyBooked() throws NotEligibleForSessionException, SessionAlreadyBookedException, SessionIsFullException, TraineeAlreadyExistsException {
        swimmingSchool.addTrainee(trainee);
        swimmingSchool.addSession(session);
        swimmingSchool.bookSession(trainee, session);
        swimmingSchool.bookSession(trainee, session);
    }

    @Test
    public void testCancelSession_Successful() throws NotEligibleForSessionException, SessionAlreadyBookedException, SessionIsFullException, SessionNotBookedException, SessionIsAttendedException, TraineeAlreadyExistsException {
        swimmingSchool.addTrainee(trainee);
        swimmingSchool.addSession(session);
        swimmingSchool.bookSession(trainee, session);
        swimmingSchool.cancelSession(trainee, session);
        assertFalse(trainee.isSessionBooked(session));
        assertFalse(session.getAttendees().contains(trainee));
    }

    @Test(expected = SessionNotBookedException.class)
    public void testCancelSession_NotBooked() throws SessionNotBookedException, SessionIsAttendedException {
        swimmingSchool.cancelSession(trainee, session);
    }

    @Test
    public void testAttendSession_Successful() throws NotEligibleForSessionException, SessionAlreadyBookedException, SessionIsFullException, SessionNotBookedException, TraineeAlreadyExistsException {
        swimmingSchool.addTrainee(trainee);
        swimmingSchool.addSession(session);
        swimmingSchool.bookSession(trainee, session);
        swimmingSchool.attendSession(trainee, session);
        assertTrue(trainee.isSessionAttended(session));
    }

    @Test(expected = SessionNotBookedException.class)
    public void testAttendSession_NotBooked() throws SessionNotBookedException {
        swimmingSchool.attendSession(trainee, session);
    }

    @Test
    public void testWriteReport_Successful() throws NotEligibleForSessionException, RatingOutOfRangeException, WriteReportException, TraineeAlreadyExistsException, SessionIsFullException, SessionAlreadyBookedException, SessionNotBookedException {
        swimmingSchool.addTrainee(trainee);
        swimmingSchool.addSession(session);
        swimmingSchool.bookSession(trainee, session);
        swimmingSchool.attendSession(trainee, session);

        int rating = 5;
        swimmingSchool.writeReport(trainee, session, rating);

        Report report = swimmingSchool.getReports().get(0);
        assertEquals(trainee, report.getTrainee());
        assertEquals(session, report.getSession());
        assertEquals(rating, report.getRating());
    }

    @Test(expected = NotEligibleForSessionException.class)
    public void testWriteReview_NotEligibleForSession() throws NotEligibleForSessionException, RatingOutOfRangeException, WriteReportException, TraineeAlreadyExistsException, SessionIsFullException, SessionAlreadyBookedException {
        SwimmingSession session = new SwimmingSession(1234, 3);
        swimmingSchool.addTrainee(trainee);
        swimmingSchool.addSession(session);
        swimmingSchool.bookSession(trainee, session);
        swimmingSchool.writeReport(trainee, session, 5); // Trainee hasn't booked or attended the session
    }

    @Test(expected = WriteReportException.class)
    public void testWriteReview_NotAttendedSession() throws  RatingOutOfRangeException, WriteReportException, TraineeAlreadyExistsException, SessionIsFullException, SessionAlreadyBookedException {
        swimmingSchool.addTrainee(trainee);
        swimmingSchool.addSession(session);
        swimmingSchool.writeReport(trainee, session, 5); // Trainee hasn't attended the session
    }

    @Test(expected = RatingOutOfRangeException.class)
    public void testWriteReview_RatingOutOfRange() throws NotEligibleForSessionException, RatingOutOfRangeException, WriteReportException, TraineeAlreadyExistsException, SessionIsFullException, SessionAlreadyBookedException, SessionNotBookedException {
        swimmingSchool.addTrainee(trainee);
        swimmingSchool.addSession(session);
        swimmingSchool.bookSession(trainee, session);
        swimmingSchool.attendSession(trainee, session);
        swimmingSchool.writeReport(trainee, session, 6); // Rating out of range
    }

    // Helper method to capture System.out
    private String captureOutput(Runnable code) {
        // Redirect System.out to a string
        var outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the code
        code.run();

        // Reset System.out
        System.setOut(System.out);

        // Return the captured output
        return outContent.toString();
    }
}
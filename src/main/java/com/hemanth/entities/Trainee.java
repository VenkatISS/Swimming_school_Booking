package com.hemanth.entities;

import com.hemanth.exceptions.TraineeAgeException;

import java.util.ArrayList;
import java.util.List;

public class Trainee {
    private String name;
    private String gender;
    private int age;
    private String emergencyContact;
    private int grade;
    private List<SwimmingSession> sessionsBooked;
    private List<SwimmingSession> sessionsAttended;
    private List<SwimmingSession> sessionsCancelled;

    public Trainee(String name, String gender , int age, String emergencyContact, int grade) throws TraineeAgeException {
        if(age < 4 || age > 11) {
            throw new TraineeAgeException("Age should be between 4 and 11");
        }
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.emergencyContact = emergencyContact;
        this.grade = grade;
        this.sessionsBooked = new ArrayList<>();
        this.sessionsAttended = new ArrayList<>();
        this.sessionsCancelled = new ArrayList<>();
    }

    public boolean isSessionAttended(SwimmingSession session) {
        return sessionsAttended.contains(session);
    }

    public boolean isSessionBooked(SwimmingSession session) {
        return sessionsBooked.contains(session);
    }

    public boolean isEligibleForSession(SwimmingSession session){
        return grade == session.getGrade() || grade == session.getGrade() - 1;
    }

    public void bookSession(SwimmingSession session) {
        sessionsBooked.add(session);
    }

    public void attendSession(SwimmingSession session) {
        sessionsBooked.remove(session);
        sessionsAttended.add(session);
        setGrade(session.getGrade());
    }

    public void cancelSession(SwimmingSession session) {
        sessionsBooked.remove(session);
        sessionsCancelled.add(session);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public List<SwimmingSession> getSessionsBooked() {
        return sessionsBooked;
    }

    public List<SwimmingSession> getSessionsAttended() {
        return sessionsAttended;
    }


    public List<SwimmingSession> getSessionsCancelled() {
        return sessionsCancelled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainee trainee = (Trainee) o;
        return name.equals(trainee.name);
    }


    @Override
    public String toString() {
        String sb = "Name: " + name + "\n" +
                "Gender: " + gender + "\n" +
                "Age: " + age + "\n" +
                "Emergency Contact: " + emergencyContact + "\n" +
                "Grade: " + grade + "\n" +
                "Sessions Booked: " + sessionsBooked.size() + "\n" +
                "Sessions Attended: " + sessionsAttended.size() + "\n" +
                "Sessions Cancelled: " + sessionsCancelled.size() + "\n";
        return sb;
    }
}

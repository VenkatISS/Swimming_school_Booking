package com.hemanth.entities;

public class Report {
    private Trainee trainee;
    private int rating;
    private SwimmingSession session;
    public Report(Trainee trainee, int rating, SwimmingSession session) {
        this.trainee = trainee;
        this.rating = rating;
        this.session = session;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public SwimmingSession getSession() {
        return session;
    }

    public void setSession(SwimmingSession session) {
        this.session = session;
    }
}

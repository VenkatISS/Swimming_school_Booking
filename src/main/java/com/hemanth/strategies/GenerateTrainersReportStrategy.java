package com.hemanth.strategies;

import com.hemanth.entities.Report;
import com.hemanth.entities.Trainer;
import com.hemanth.service.SwimSchool;
import com.hemanth.utils.Helper;

import java.util.List;

public class GenerateTrainersReportStrategy implements GenerateReportStrategy {
    @Override
    public void generateReport(SwimSchool swimmingSchool) {
        List<Report> reports = swimmingSchool.getReports();
        List<Trainer> trainers = swimmingSchool.getTrainers();
        System.out.println("---------------------------------------------");
        System.out.println("Trainers Report");
        System.out.println("---------------------------------------------");
        System.out.println("Number of trainers: " + trainers.size());
        System.out.println("Trainers: ");
        System.out.printf("%-20s%-20s%-20s\n", "Name", "Average Rating","Global Rating");
        for (Trainer trainer : trainers) {
            double rating = getAverageRating(reports, trainer);
            System.out.printf("%-20s%-20s%-20s\n", trainer.getName(), rating, Helper.getRatingString((int) rating));
        }
        System.out.println("---------------------------------------------");
    }

    private double getAverageRating(List<Report> reports, Trainer trainer) {
        double sum = 0;
        int count = 0;
        for (Report report : reports) {
            if (report.getSession().getTrainer().equals(trainer)) {
                sum += report.getRating();
                count++;
            }
        }
        return sum / count;
    }
}

package com.hemanth.strategies;

import com.hemanth.entities.Trainee;
import com.hemanth.service.SwimSchool;

import java.util.List;

public class GenerateTraineesReportStrategy implements GenerateReportStrategy {
    @Override
    public void generateReport(SwimSchool swimmingSchool) {
        List<Trainee> trainees = swimmingSchool.getTrainees();
        System.out.println("---------------------------------------------");
        System.out.println("Trainees Report");
        System.out.println("---------------------------------------------");
        for (Trainee trainee : trainees) {
            System.out.println(trainee);
        }
        System.out.println("---------------------------------------------");
    }
}

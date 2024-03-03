package com.hemanth;

import com.hemanth.entities.*;
import com.hemanth.exceptions.*;
import com.hemanth.service.SwimSchool;

import java.util.Scanner;

public class Application {
    private static final SwimSchool swimmingSchool = new SwimSchool();
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            clearConsole();
            separator();
            System.out.println("Welcome to the Swim School Management System");
            separator();
            System.out.println("1. Add Trainee");
            System.out.println("2. Remove Trainee");
            System.out.println("3. Add Trainer");
            System.out.println("4. Remove Trainer");
            System.out.println("5. Add Session");
            System.out.println("6. Remove Session");
            System.out.println("7. View Slots by Day");
            System.out.println("8. View Slots by Grade");
            System.out.println("9. View Slots by Trainer");
            System.out.println("10. Book Session");
            System.out.println("11. Cancel Session");
            System.out.println("12. Attend Session");
            System.out.println("13. Write Report");
            System.out.println("14. Generate Trainees Report");
            System.out.println("15. Generate Trainers Report");
            System.out.println("0. Exit");
            separator();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    clearConsole();
                    separator();
                    System.out.println("Add Trainee");
                    separator();
                    addTrainee();
                    break;
                case 2:
                    clearConsole();
                    separator();
                    System.out.println("Remove Trainee");
                    separator();
                    removeTrainee();
                    break;
                case 3:
                    clearConsole();
                    separator();
                    System.out.println("Add Trainer");
                    separator();
                    addTrainer();
                    break;
                case 4:
                    clearConsole();
                    separator();
                    System.out.println("Remove Trainer");
                    separator();
                    removeTrainer();
                    break;
                case 5:
                    clearConsole();
                    separator();
                    System.out.println("Add Session");
                    separator();
                    addSession();
                    break;
                case 6:
                    clearConsole();
                    separator();
                    System.out.println("Remove Session");
                    separator();
                    removeSession();
                    break;
                case 7:
                    clearConsole();
                    separator();
                    System.out.println("Slots by Day");
                    separator();
                    viewTimetableByDay();
                    break;
                case 8:
                    clearConsole();
                    separator();
                    System.out.println("Slots by Grade");
                    separator();
                    viewSlotsByGrade();
                    break;
                case 9:
                    clearConsole();
                    separator();
                    System.out.println("Slots by Trainer");
                    separator();
                    viewSlotsByTrainer();
                    break;
                case 10:
                    clearConsole();
                    separator();
                    System.out.println("Book Session");
                    separator();
                    bookSession();
                    break;
                case 11:
                    clearConsole();
                    separator();
                    System.out.println("Cancel Session");
                    separator();
                    cancelSession();
                    break;
                case 12:
                    clearConsole();
                    separator();
                    System.out.println("Attend Session");
                    separator();
                    attendSession();
                    break;
                case 13:
                    clearConsole();
                    separator();
                    System.out.println("Write Review");
                    separator();
                    writeReport();
                    break;
                case 14:
                    swimmingSchool.generateTraineeReport();
                    break;
                case 15:
                    swimmingSchool.generateTrainerReport();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    scanner.nextLine();
            }
        }
    }

    private static void addTrainee() {
        System.out.print("Enter trainee name: ");
        String traineeName = scanner.nextLine();
        System.out.print("Enter trainee gender: ");
        String traineeGender = scanner.nextLine();
        System.out.print("Enter trainee age: ");
        int traineeAge = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter trainee emergency contact: ");
        String traineeEmergencyContact = scanner.nextLine();
        System.out.print("Enter trainee grade: ");
        int traineeGrade = scanner.nextInt();
        scanner.nextLine();
        try {
            swimmingSchool.addTrainee(
                    new Trainee(
                            traineeName,
                            traineeGender,
                            traineeAge,
                            traineeEmergencyContact,
                            traineeGrade
                    )
            );
            System.out.println("Trainee added successfully!");
            scanner.nextLine();
        } catch (TraineeAlreadyExistsException | TraineeAgeException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
    }

    private static void removeTrainee() {
        System.out.print("Enter trainee name: ");
        Trainee trainee = swimmingSchool.getTrainees().stream().filter(
                l -> l.getName().equals(scanner.nextLine())
        ).findFirst().orElse(null);
        if (trainee == null) {
            System.out.println("Trainee not found!");
            scanner.nextLine();
            return;
        }
        swimmingSchool.removeTrainee(trainee);
        System.out.println("Trainee removed successfully!");
        scanner.nextLine();
    }

    private static void addTrainer() {
        System.out.print("Enter trainer name: ");
        String trainerName = scanner.nextLine();
        try {
            swimmingSchool.addTrainer(
                    new Trainer(
                            trainerName
                    )
            );
            System.out.println("Trainer added successfully!");
            scanner.nextLine();
        } catch (TrainerAlreadyExistsException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
    }

    private static void removeTrainer() {
        System.out.print("Enter trainer name: ");
        Trainer trainer = swimmingSchool.getTrainers().stream().filter(
                c -> c.getName().equals(scanner.nextLine())
        ).findFirst().orElse(null);
        if (trainer == null) {
            System.out.println("Trainer not found!");
            scanner.nextLine();
            return;
        }
        swimmingSchool.removeTrainer(trainer);
        System.out.println("Trainer removed successfully!");
        scanner.nextLine();
    }

    private static void addSession() {
        System.out.print("Days");
        System.out.println("1. "+ SessionDay.MONDAY);
        System.out.println("2. "+ SessionDay.WEDNESDAY);
        System.out.println("3. "+ SessionDay.FRIDAY);
        System.out.println("4. "+ SessionDay.SATURDAY);
        System.out.println("0. Exit");
        SessionDay day = null;
        System.out.print("Enter day: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                day = SessionDay.MONDAY;
                break;
            case 2:
                day = SessionDay.WEDNESDAY;
                break;
            case 3:
                day = SessionDay.FRIDAY;
                break;
            case 4:
                day = SessionDay.SATURDAY;
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice!");
                scanner.nextLine();
                return;
        }
        System.out.print("Times");
        System.out.println("1. "+ Slots.From2To3);
        System.out.println("2. "+ Slots.From3To4);
        System.out.println("3. "+ Slots.From4To5);
        System.out.println("4. "+ Slots.From5To6);
        System.out.println("5. "+ Slots.From6To7);
        System.out.println("0. Exit");
        Slots time = null;
        System.out.print("Enter time: ");
        choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                time = Slots.From2To3;
                break;
            case 2:
                time = Slots.From3To4;
                break;
            case 3:
                time = Slots.From4To5;
                break;
            case 4:
                time = Slots.From5To6;
                break;
            case 5:
                time = Slots.From6To7;
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice!");
                scanner.nextLine();
                return;
        }
        System.out.print("Enter grade: ");
        int grade = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter trainer name: ");
        String trainerName = scanner.nextLine();
        Trainer trainer = swimmingSchool.getTrainers().stream().filter(
                c -> c.getName().equals(trainerName)
        ).findFirst().orElse(null);
        if (trainer == null) {
            System.out.println("Trainer not found!");
            scanner.nextLine();
            return;
        }
        scanner.nextLine();
        swimmingSchool.addSession(
                new SwimmingSession(
                        (int) Math.random()*1000,
                        time,
                        trainer,
                        day,
                        grade
                )
        );
        System.out.println("Session added successfully!");
        scanner.nextLine();
    }

    private static void removeSession() {
        System.out.print("Enter session id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimmingSession session = swimmingSchool.getSessions().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Session not found!");
            scanner.nextLine();
            return;
        }
        swimmingSchool.removeSession(
                session
        );
        System.out.println("Session removed successfully!");
        scanner.nextLine();
    }

    private static void viewTimetableByDay() {
        System.out.print("Days");
        System.out.println("1. "+ SessionDay.MONDAY);
        System.out.println("2. "+ SessionDay.WEDNESDAY);
        System.out.println("3. "+ SessionDay.FRIDAY);
        System.out.println("4. "+ SessionDay.SATURDAY);
        System.out.println("0. Exit");
        SessionDay day = null;
        System.out.print("Enter day: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                day = SessionDay.MONDAY;
                break;
            case 2:
                day = SessionDay.WEDNESDAY;
                break;
            case 3:
                day = SessionDay.FRIDAY;
                break;
            case 4:
                day = SessionDay.SATURDAY;
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice!");
                scanner.nextLine();
                return;
        }
        swimmingSchool.viewSlotsByDay(day);
        scanner.nextLine();
    }

    private static void viewSlotsByGrade() {
        System.out.print("Enter grade: ");
        int grade = scanner.nextInt();
        scanner.nextLine();
        swimmingSchool.viewSlotsByGrade(grade);
        scanner.nextLine();
    }

    private static void viewSlotsByTrainer() {
        System.out.print("Enter trainer name: ");
        String trainerName = scanner.nextLine();
        Trainer trainer = swimmingSchool.getTrainers().stream().filter(
                c -> c.getName().equals(trainerName)
        ).findFirst().orElse(null);

        if (trainer == null) {
            System.out.println("Trainer not found!");
            scanner.nextLine();
            return;
        }

        swimmingSchool.viewSlotsByTrainer(
                trainer
        );
        scanner.nextLine();
    }

    private static void bookSession() {
        System.out.print("Enter trainee name: ");
        String traineeName = scanner.nextLine();
        Trainee trainee = swimmingSchool.getTrainees().stream().filter(
                l -> l.getName().equals(traineeName)
        ).findFirst().orElse(null);
        if (trainee == null) {
            System.out.println("Trainee not found!");
            scanner.nextLine();
            return;
        }
        System.out.print("Enter session id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimmingSession session = swimmingSchool.getSessions().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Session not found!");
            scanner.nextLine();
            return;
        }
        try {
            swimmingSchool.bookSession(
                    trainee,
                    session
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        System.out.println("Session booked successfully!");
        scanner.nextLine();
    }

    private static void cancelSession() {
        System.out.print("Enter trainee name: ");
        String traineeName = scanner.nextLine();
        Trainee trainee = swimmingSchool.getTrainees().stream().filter(
                l -> l.getName().equals(traineeName)
        ).findFirst().orElse(null);
        if (trainee == null) {
            System.out.println("Trainee not found!");
            scanner.nextLine();
            return;
        }
        System.out.print("Enter session id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimmingSession session = swimmingSchool.getSessions().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Session not found!");
            scanner.nextLine();
            return;
        }
        try {
            swimmingSchool.cancelSession(
                    trainee,
                    session
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        System.out.println("Session cancelled successfully!");
        scanner.nextLine();
    }

    private static void attendSession() {
        System.out.print("Enter trainee name: ");
        String traineeName = scanner.nextLine();
        Trainee trainee = swimmingSchool.getTrainees().stream().filter(
                l -> l.getName().equals(traineeName)
        ).findFirst().orElse(null);
        if (trainee == null) {
            System.out.println("Trainee not found!");
            scanner.nextLine();
            return;
        }
        System.out.print("Enter session id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimmingSession session = swimmingSchool.getSessions().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Session not found!");
            return;
        }
        try {
            swimmingSchool.attendSession(
                    trainee,
                    session
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        System.out.println("Session attended successfully!");
        scanner.nextLine();
    }

    private static void writeReport() {
        System.out.print("Enter trainee name: ");
        String traineeName = scanner.nextLine();
        Trainee trainee = swimmingSchool.getTrainees().stream().filter(
                l -> l.getName().equals(traineeName)
        ).findFirst().orElse(null);
        if (trainee == null) {
            System.out.println("Trainee not found!");
            scanner.nextLine();
            return;
        }
        System.out.println("Enter Session ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        SwimmingSession session = swimmingSchool.getSessions().stream().filter(
                l -> l.getId() == id
        ).findFirst().orElse(null);
        if (session == null) {
            System.out.println("Session not found!");
            scanner.nextLine();
            return;
        }
        System.out.print("Enter report: 1-5");
        int review = scanner.nextInt();
        scanner.nextLine();
        try {
            swimmingSchool.writeReport(
                    trainee,
                            session,
                            review
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        System.out.println("Report written successfully!");
        scanner.nextLine();
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    private static void separator() {
        System.out.println("------------------------------------------------");
    }
}

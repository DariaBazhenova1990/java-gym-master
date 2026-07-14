package ru.yandex.practicum.gym.service;

import ru.yandex.practicum.gym.model.*;

import java.util.Scanner;

public class GymMaster {
    private static final Scanner scanner = new Scanner(System.in);

    static Timetable timetable = new Timetable();
    static Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
    static Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
    static TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
            DayOfWeek.THURSDAY, new TimeOfDay(20, 0));
    static Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
    static TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
            DayOfWeek.MONDAY, new TimeOfDay(13, 0));
    static TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
            DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
    static TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
            DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println("Ввод не должен быть пустым.");
                return;
            }

            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1:
                    timetable.addNewTrainingSession(thursdayAdultTrainingSession);
                    timetable.addNewTrainingSession(mondayChildTrainingSession);
                    timetable.addNewTrainingSession(thursdayChildTrainingSession);
                    timetable.addNewTrainingSession(saturdayChildTrainingSession);
                    break;
                case 2:
                    System.out.println(timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY));
                    break;
                case 3:
                    System.out.println(timetable
                            .getTrainingSessionsForDayAndTime(DayOfWeek.THURSDAY, new TimeOfDay(20, 0)));
                    break;
                case 0:
                    running = false;
                    System.out.println("Завершение...");
                    break;
                default:
                    System.out.println("Неверный выбор.\n");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить новую тренировку в расписание");
        System.out.println("2 — Получить все тренировки за конкретный день");
        System.out.println("3 — Получить все тренировки за конкретный день и конкретное время");
        System.out.println("0 — Завершить");
        System.out.print("Ваш выбор: ");
    }

}

package ru.yandex.practicum.gym.service;

import ru.yandex.practicum.gym.model.*;

import java.util.Scanner;

public class GymMaster {
    public static final Scanner scanner = new Scanner(System.in);
    static Timetable timetable = new Timetable();
    static DayOfWeek day;
    static TimeOfDay time;
    static ConsoleInput consoleInput = new ConsoleInput();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            String input = scanner.nextLine();
            if (consoleInput.checkEmpty(input)) return;
            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1:
                    TrainingSession session = consoleInput.setSession();
                    timetable.addNewTrainingSession(session);
                    break;
                case 2:
                    day = consoleInput.setDayOfWeek();
                    Printer.print(timetable.getTrainingSessionsForDay(day));
                    break;
                case 3:
                    day = consoleInput.setDayOfWeek();
                    time = consoleInput.setTimeOfDay();
                    Printer.print(timetable.getTrainingSessionsForDayAndTime(day, time));
                    break;
                case 4:
                    Printer.print(timetable.getCountByCoaches());
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
        System.out.println("4 — Посчитать тренировки у разных тренеров");
        System.out.println("0 — Завершить");
        System.out.print("Ваш выбор: ");
    }

}

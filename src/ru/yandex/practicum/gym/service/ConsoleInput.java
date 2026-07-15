package ru.yandex.practicum.gym.service;

import ru.yandex.practicum.gym.model.*;

import static ru.yandex.practicum.gym.service.GymMaster.scanner;

public class ConsoleInput {

    public boolean checkEmpty(String input) {
        if (input.isEmpty()) {
            System.out.println("Ввод не должен быть пустым.");
            return true;
        }
        return false;
    }

    public DayOfWeek setDayOfWeek() {
        DayOfWeek day = null;
        System.out.println("Выберите день недели:");
        System.out.println("1 — ПН, 2 — ВТ, 3 — СР, 4 — ЧТ, 5 — ПТ, 6 — СБ, 7 — ВС.");
        System.out.print("Ваш выбор: ");
        String input = scanner.nextLine();
        int choice = Integer.parseInt(input);
        switch (choice) {
            case 1:
                day = DayOfWeek.MONDAY;
                break;
            case 2:
                day = DayOfWeek.TUESDAY;
                break;
            case 3:
                day = DayOfWeek.WEDNESDAY;
                break;
            case 4:
                day = DayOfWeek.THURSDAY;
                break;
            case 5:
                day = DayOfWeek.FRIDAY;
                break;
            case 6:
                day = DayOfWeek.SATURDAY;
                break;
            case 7:
                day = DayOfWeek.SUNDAY;
                break;
            default:
                System.out.println("Неверный выбор.\n");
        }
        return day;
    }

    public TimeOfDay setTimeOfDay() {
        System.out.print("Введите время начала тренировки (часы): ");
        String inputHours = scanner.nextLine();
        int hours = Integer.parseInt(inputHours);
        System.out.print("Введите время начала тренировки (минуты): ");
        String inputMinutes = scanner.nextLine();
        int minutes = Integer.parseInt(inputMinutes);

        return new TimeOfDay(hours, minutes);
    }

    public Coach setCoach() {
        System.out.print("Введите фамилию тренера: ");
        String surname = scanner.nextLine();
        System.out.print("Введите имя тренера: ");
        String name = scanner.nextLine();
        System.out.print("Введите отчество тренера: ");
        String middleName = scanner.nextLine();

        return new Coach(surname, name, middleName);
    }

    public Group setGroup() {
        Age age = null;
        System.out.print("Введите название тренировки : ");
        String title = scanner.nextLine();
        System.out.print("Введите возрастную группу (1 — Взрослые, 2 — Дети): ");
        String inputAge = scanner.nextLine();
        int choice = Integer.parseInt(inputAge);
        switch (choice) {
            case 1:
                age = Age.ADULT;
                break;
            case 2:
                age = Age.CHILD;
                break;
            default:
                System.out.println("Неверный выбор.\n");
        }
        System.out.print("Введите длительность : ");
        String inputDuration = scanner.nextLine();
        int duration = Integer.parseInt(inputDuration);

        return new Group(title, age, duration);
    }

    public TrainingSession setSession() {
        DayOfWeek day = setDayOfWeek();
        TimeOfDay time = setTimeOfDay();
        Group group = setGroup();
        Coach coach = setCoach();

        return new TrainingSession(group, coach, day, time);
    }
}


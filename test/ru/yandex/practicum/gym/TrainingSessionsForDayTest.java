package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.gym.model.*;

import java.util.ArrayList;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TrainingSessionsForDayTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник вернулось одно занятие
        int countSessions = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size();
        assertEquals(1, countSessions, "Количество тренировок отличается от ожидаемого.");

    }

    @Test
    void testGetTrainingSessionsForDayNoSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за вторник не вернулось занятий
        assertNull(timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY),
                "Количество тренировок отличается от ожидаемого.");
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        // Проверить, что за четверг вернулось два занятия в правильном порядке: сначала в 13:00, потом в 20:00
        int countMultipleSessions = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY).size();
        assertEquals(2, countMultipleSessions, "Количество тренировок отличается от ожидаемого.");
        TimeOfDay firstSession = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY).firstKey();
        assertEquals(new TimeOfDay(13, 0), firstSession,
                "Неверный порядок возвращаемых тренировок.");
        TimeOfDay lastSession = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY).lastKey();
        assertEquals(new TimeOfDay(20, 0), lastSession,
                "Неверный порядок возвращаемых тренировок.");

    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessionsPerSlot() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TimeOfDay time = new TimeOfDay(13, 0);

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, time);

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, time);
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, time);
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        // Проверить, что за понедельник вернулось одно занятие
        int countSingleSessions = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY).size();
        assertEquals(1, countSingleSessions, "Количество тренировок отличается от ожидаемого.");
        // Проверить, что за четверг вернулось два занятия
        TreeMap<TimeOfDay, ArrayList<TrainingSession>> multipleSessions = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY);
        int countMultipleSessionsPerSlot = multipleSessions.get(time).size();
        assertEquals(2, countMultipleSessionsPerSlot, "Количество тренировок отличается от ожидаемого.");

    }

}

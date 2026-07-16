package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.gym.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class TrainingSessionsForDayAndTimeTest {

    @Test
    void testGetTrainingSessionsForDayAndTimeSingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        DayOfWeek day = DayOfWeek.MONDAY;
        TimeOfDay time = new TimeOfDay(13, 0);
        TrainingSession singleTrainingSession = new TrainingSession(group, coach, day, time);
        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за понедельник в 13:00 вернулось одно занятие
        int countSessions = timetable.getTrainingSessionsForDayAndTime(day, time).size();
        assertEquals(1, countSessions, "Количество тренировок отличается от ожидаемого.");
    }

    @Test
    void testGetTrainingSessionsForDayAndTimeNoSessionForDay() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        DayOfWeek day = DayOfWeek.MONDAY;
        TimeOfDay time = new TimeOfDay(13, 0);
        TrainingSession singleTrainingSession = new TrainingSession(group, coach, day, time);
        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за вторник не вернулось занятий
        assertNull(timetable.getTrainingSessionsForDayAndTime(DayOfWeek.TUESDAY, time),
                "Количество тренировок отличается от ожидаемого.");
    }

    @Test
    void testGetTrainingSessionsForDayAndTimeNoSessionForTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        DayOfWeek day = DayOfWeek.MONDAY;
        TimeOfDay time = new TimeOfDay(13, 0);
        TrainingSession singleTrainingSession = new TrainingSession(group, coach, day, time);
        timetable.addNewTrainingSession(singleTrainingSession);

        //Проверить, что за другое время не вернулось занятий
        assertNull(timetable.getTrainingSessionsForDayAndTime(day, new TimeOfDay(14, 0)),
                "Количество тренировок отличается от ожидаемого.");
    }

    @Test
    void testGetTrainingSessionsForDayAndTimeMultipleSession() {
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

        // Проверить, что вернулось два занятия
        int countMultipleSessionsPerSlot = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.THURSDAY, time).size();
        assertEquals(2, countMultipleSessionsPerSlot, "Количество тренировок отличается от ожидаемого.");

    }

}

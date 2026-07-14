package ru.yandex.practicum.gym.model;

import java.util.*;

public class Timetable {

    private final HashMap<DayOfWeek, TreeMap<TimeOfDay, ArrayList<TrainingSession>>> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        DayOfWeek dayOfWeek = trainingSession.getDayOfWeek();
        TimeOfDay timeOfDay = trainingSession.getTimeOfDay();

        TreeMap<TimeOfDay, ArrayList<TrainingSession>> daySchedule =
                timetable.computeIfAbsent(dayOfWeek, k -> new TreeMap<>());

        ArrayList<TrainingSession> sessionsList =
                daySchedule.computeIfAbsent(timeOfDay, k -> new ArrayList<>());

        sessionsList.add(trainingSession);
        System.out.println("Добавлена следующая информация: " + trainingSession);

    }

    public TreeMap<TimeOfDay, ArrayList<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        return timetable.get(dayOfWeek);
    }

    public ArrayList<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        TreeMap<TimeOfDay, ArrayList<TrainingSession>> daySchedule = getTrainingSessionsForDay(dayOfWeek);
        return daySchedule.get(timeOfDay);
    }

}

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
        return timetable.getOrDefault(dayOfWeek, new TreeMap<>());
    }

    public ArrayList<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        TreeMap<TimeOfDay, ArrayList<TrainingSession>> daySchedule = getTrainingSessionsForDay(dayOfWeek);
        if (daySchedule.isEmpty()) return new ArrayList<>();
        return daySchedule.getOrDefault(timeOfDay, new ArrayList<>());
    }


    public LinkedHashMap<Coach, Integer> getCountByCoaches() {
        LinkedHashMap<Coach, Integer> statistics = new LinkedHashMap<>();

        for (TreeMap<TimeOfDay, ArrayList<TrainingSession>> allDaysSessions : timetable.values()) {
            for (ArrayList<TrainingSession> sessionList : allDaysSessions.values()) {
                for (TrainingSession session : sessionList) {
                    Coach coach = session.getCoach();
                    statistics.put(coach, statistics.getOrDefault(coach, 0) + 1);
                }
            }
        }

        List<Map.Entry<Coach, Integer>> list = new ArrayList<>(statistics.entrySet());
        list.sort(new Comparator<>() {
            @Override
            public int compare(Map.Entry<Coach, Integer> o1, Map.Entry<Coach, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        statistics.clear();

        for (Map.Entry<Coach, Integer> entry : list) {
            statistics.put(entry.getKey(), entry.getValue());
        }

        return statistics;
    }

}

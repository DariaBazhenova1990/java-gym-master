package ru.yandex.practicum.gym.model;

public enum Age {
    CHILD("Дети"),
    ADULT("Взрослые");

    private final String title;

    Age(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}

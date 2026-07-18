package ru.yandex.practicum.gym.service;

import java.util.Map;

public class Printer {

    public static <E> void print(Iterable<E> objects) {
        for (Object object : objects) {
            System.out.println(object);
        }
    }

    public static <K, V> void print(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}

package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {

    private static final Model instance = new Model();
    private final Map<Integer, User> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new HashMap<>();
        model.put(1, new User("Ilya", "Serov", 9999));
        model.put(2, new User("Anton", "Osipov", 12345));
        model.put(3, new User("Vlad", "Kar", 123456));
        model.put(4, new User("Vlad", "Kar", 1234561));
    }

    public void add(User user, int id) {
        model.put(id, user);
    }

    public void delete(int id) {
        model.remove(id);
    }

    public void update(int id, User user) {
        model.put(id, user);
    }

    public Map<Integer, User> getFromList() {
        return model;
    }
}

package com.mycompany.coelhorapido.Model;

public class User {
    private static int id;
    private static String name;

    // Getters e Setters
    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        User.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        User.name = name;
    }
}
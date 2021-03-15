package Lesson4;

import java.util.Locale;

public class Employee {
    private String name;
    private String email;
    private String post;
    private int wage;
    private int age;
    private String number;
    private static int count = 0;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
        this.post = "helper";
        this.wage = 10000;
        this.email = new String("helper_");
        for (int i = 0; i < name.length() && i < 5; i++) {
            this.email += name.toCharArray()[i];
        }
        this.email += "@groke.ru";
        this.number = new String("+56712-1" + count);
    }

    public Employee(String name, String post, int wage, int age) {
        count++;
        this.name = name;
        this.post = post;
        this.wage = wage;
        this.age = age;
        this.email = new String("");
        for (int i = 0; i < name.length() && i < 5; i++) {
            this.email += name.toCharArray()[i];
        }
        this.email += "@groke.ru";
        this.number = new String("+56712-1" + count);
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return name;
    }

    public String getPost() {
        return this.post;
    }

    public void setPost(String newPost) {
        this.post = newPost;
    }

    public int getWage() {
        return this.wage;
    }

    public int setWage() {
        return this.wage;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNumber() {
        return this.number;
    }

    @Override
    public String toString() {
        return "ФИО сотрудника: %s%nВозраст: %d%nДолжность: %s%nЗарплата: %d%nE-mail: %s%nТелефон: %s%n";
    }

    public void printInfo() {
        System.out.printf(this.toString(), name, age, post, wage, email, number);
    }
}


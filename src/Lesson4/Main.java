package Lesson4;

public class Main {
    private static Employee[] staff;
    private static final int EMPLOYEES_MAX_COUNT = 5;

    public static void main(String[] args) {
        staff = new Employee[EMPLOYEES_MAX_COUNT];
        recruitStaff();
        showEmployee(40);
    }

    private static void recruitStaff() {
        staff[0] = new Employee("Олегов Кирилл", 50);
        staff[1] = new Employee("Курага Четыре", "Администратор", 35000, 32);
        staff[2] = new Employee("Ольга Евгеньевич", 80);
        staff[3] = new Employee("Groke Akamorra", "Насяльнике", 10, 26);
        staff[4] = new Employee("Андреева Ира", "Уборщица", 50, 104);
    }

    private static void showEmployees() {
        for (int i = 0; i < EMPLOYEES_MAX_COUNT; i++) {
            staff[i].printInfo();
            System.out.println("\n ######################");
        }
    }

    private static void showEmployee(int olderThan) {
        System.out.println("Список сотрудников старше " + olderThan+": \n");
        for (int i = 0; i < EMPLOYEES_MAX_COUNT; i++) {
            if (staff[i].getAge() > 40) {
                staff[i].printInfo();
                System.out.println("\n ######################");
            }
        }
    }
}
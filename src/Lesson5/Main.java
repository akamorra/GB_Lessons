package Lesson5;

public class Main {
    public static void main(String[] args) {
        Animal[] animalsArray = {
                new Cat("Васька", "рыжий"),
                new Cat("Петька", "альбинос"),
                new Cat("Жорик", "грязный"),
                new Dog("Догги", "рыжий"),
                new Dog("Бобик", "рыжий")
        };
        for (Animal a : animalsArray) a.demonstration(100,6);
        Cat.howMany();
        Dog.howMany();
    }
}

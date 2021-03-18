package Lesson5;

import java.util.Random;

public class Dog extends Animal{
    private static int count=0;
    public Dog(String name, String color){
        this.maxDistanceRun=500;
        this.maxDistanceSwim=10;
        this.name=name;
        this.color=color;
        count++;
    }

    public static void howMany(){
        System.out.println("Создано собак: "+count);
    }

    @Override
    public void sing() {
        System.out.println("Вуф вуф");
    }

    @Override
    public void demonstration(float runDistance, float swimDistance) {
        printInfo();
        super.actions(runDistance, swimDistance);
    }

    @Override
    public String toString(){
        return "%nСобака "+super.toString();
    }

}

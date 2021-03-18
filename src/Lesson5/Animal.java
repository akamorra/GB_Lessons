package Lesson5;

import java.util.Random;

public abstract class Animal {
    public String name, color;
    public int maxDistanceRun;
    public int maxDistanceSwim;


    public boolean run(float distance) {
        System.out.println("run ("+distance+"):" + (distance <= this.maxDistanceRun));
        return distance <= this.maxDistanceRun;
    }

    public abstract void sing();

    public boolean swim(float distance){
        System.out.println("swim ("+distance+"):" + (distance <= this.maxDistanceSwim));
        return distance <= this.maxDistanceSwim;
    }

    public abstract void demonstration(float runDistance, float swimDistance);
    public void actions(float runDistance, float swimDistance){
        this.sing();
        this.run(runDistance);
        this.swim(swimDistance);
        System.out.println();
        System.out.println();
    }

    public String toString(){
        return  "по имени %s%nОкрас: %s%nДальность бега:%d%nДистанция плавания:%d%n";
    }

    public void printInfo(){
        System.out.printf(this.toString(),this.name,this.color,this.maxDistanceRun,this.maxDistanceSwim);
    }
}

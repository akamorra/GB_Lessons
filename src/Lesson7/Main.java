package Lesson7;

import java.util.Arrays;
import java.util.Random;

import static Lesson7.ObstacleType.WALL;
import static Lesson7.ObstacleType.ROAD;

/*
        1. Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса.
        Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в консоль).
        2. Создайте два класса: беговая дорожка и стена, при прохождении через которые,
        участники должны выполнять соответствующие действия (бежать или прыгать),
        результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
        3. Создайте два массива: с участниками и препятствиями, и заставьте всех
        участников пройти этот набор препятствий.
        4. * У препятствий есть длина (для дорожки) или высота (для стены), а
        участников ограничения на бег и прыжки. Если участник не смог пройти одно
        из препятствий, то дальше по списку он препятствий не идет.*/
public class Main {
    static final public ObstacleType[] arr = {ROAD,WALL,WALL, WALL,ROAD,ROAD,WALL,ROAD,WALL};
    public static void main(String[] args) {
        Obstacle[] roadMap = initRoad();
        Actions[] participants={new Robot(20),
                                new Human(10,2),
                                new Cat(80,6) };
        for(Actions a:participants){
            a.go(roadMap);
        }
    }

    private static Obstacle[] initRoad() {
        Random rnd=new Random();
        Obstacle[] road = new Obstacle[arr.length];
        for (int i = 0; i < road.length; i++) {
            road[i]=new Obstacle(arr[i],rnd.nextInt(6));
        }

        System.out.println("\nПолоса препятствий: "+Arrays.toString(road));
        return road;
    }
}
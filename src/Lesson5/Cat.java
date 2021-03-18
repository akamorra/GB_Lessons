package Lesson5;

public class Cat extends Animal {
    private static int count=0;

    public Cat(String name, String color){
        this.maxDistanceRun=200;
        this.maxDistanceSwim=0;
        this.name=name;
        this.color=color;
        count++;
    }


    public static void howMany(){
        System.out.println("Создано котов: "+count);
    }

    @Override
    public void sing() {
        System.out.println("Мяу");
    }

    @Override
    public boolean swim(float distance) {
        System.out.print("Кошки плавают как топор ... ");
        return false;
    }

    @Override
    public String toString(){
        return ("%nКот "+super.toString());
    }


    @Override
    public void demonstration(float runDistance, float swimDistance) {
        printInfo();
        super.actions(runDistance, swimDistance);
    }
}

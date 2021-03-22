package Lesson7;

public class Robot implements Actions{
    private int maxRunDistance;
    Robot(int maxRunDistance){
        this.maxRunDistance=maxRunDistance;
    }
    @Override
    public void run(Obstacle obstacle) {
        if (obstacle.size>this.maxRunDistance) {
            System.out.println("Препятствие слишком длинное, робот не справился");
            this.maxRunDistance=0;
        }else{
            this.maxRunDistance-=obstacle.size;
            System.out.println("Робот одолел препятствие, сможет еще пробежать:"+this.maxRunDistance);
        }
    }

    @Override
    public void jump(Obstacle obstacle) {
        System.out.println("Робот не умеет прыгать:( Он застрял на полосе препятствий");
        maxRunDistance=0;
    }

    @Override
    public void go(Obstacle[] road) {
        System.out.println("\nРобот приступает к прохождению полосы:");
        for (int i = 0; i < road.length; i++) {

                switch(road[i].type){
                    case WALL :
                        jump(road[i]);
                        break;
                    case ROAD :
                        run(road[i]);
                        break;
                }
            if (this.maxRunDistance<=0){
                System.out.println("Робот остановился на препятствии №"+(i+1)+": "+road[i].toString());
                break;
            }
        }
        if (maxRunDistance>0) System.out.println("Полоса препятствий пройдена успешно!");
    }
}

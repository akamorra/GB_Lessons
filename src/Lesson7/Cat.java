package Lesson7;

public class Cat implements Actions{
    private int maxRunDistance;
    private int maxJumpHeight;

    public Cat (int maxRunDistance, int maxJumpHeight){
        this.maxJumpHeight=maxJumpHeight;
        this.maxRunDistance=maxRunDistance;
    }

    @Override
    public void run(Obstacle obstacle) {
        if (obstacle.size>this.maxRunDistance) {
            System.out.println("Препятствие слишком длинное, котенька не справился");
            this.maxRunDistance=0;
        }else{
            this.maxRunDistance-=obstacle.size;
            System.out.println("Котейка одолел препятствие, сможет еще пробежать:"+this.maxRunDistance);
        }
    }

    @Override
    public void jump(Obstacle obstacle) {
        if (obstacle.size>this.maxJumpHeight) {
            System.out.println("Котейка это не блоха, эта стенка ему не по лапкам(");
            maxRunDistance = 0;
        } else {
            System.out.println("Котейка перепрыгнул стенку :)");
        }
    }

    @Override
    public void go(Obstacle[] road) {
        System.out.println("\nКотэ приступает к прохождению полосы:");
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
                System.out.println("Котейка устал и лег полежать на препятствии №"+(i+1)+": "+road[i].toString());
                break;
            }
        }
        if (maxRunDistance>0) System.out.println("Полоса препятствий пройдена успешно!");
    }
}

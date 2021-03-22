package Lesson7;

public class Human implements Actions{
    private int maxRunDistance;
    private int maxJumpHeight;

    public Human (int maxRunDistance, int maxJumpHeight){
        this.maxJumpHeight=maxJumpHeight;
        this.maxRunDistance=maxRunDistance;
    }

    @Override
    public void run(Obstacle obstacle) {
        if (obstacle.size>this.maxRunDistance) {
            System.out.println("Препятствие слишком длинное, человек не гепард( Он не справился");
            this.maxRunDistance=0;
        }else{
            this.maxRunDistance-=obstacle.size;
            System.out.println("Чеовек одолел препятствие, сможет еще пробежать:"+this.maxRunDistance);
        }
    }

    @Override
    public void jump(Obstacle obstacle) {
        if (obstacle.size>this.maxJumpHeight) {
            System.out.println("Человек без джетпака - всего лишь человек( Он не может перебраться через эту стену");
            maxRunDistance = 0;
        }  else {
            if (maxJumpHeight>2) {
                System.out.println("Человек то конечно перепрыгнул, но какого хрена? Где вы видели человека который так прыгает??");
            } else {
                System.out.println("Человек перепрыгнул стенку :)");
            }
        }
    }

    @Override
    public void go(Obstacle[] road){
        System.out.println("\nЧеловек приступает к прохождению полосы:");
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
                System.out.println("Человек упал в изнеможении на препятствии №"+(i+1)+": "+road[i].toString());
                break;
            }
        }
        if (maxRunDistance>0) System.out.println("Полоса препятствий пройдена успешно!");
    }
}

package Lesson7;

public class Obstacle {
    public int size;
    public ObstacleType type;
    Obstacle(ObstacleType t, int size){
        this.size=size;
        type=t;
    }

    @Override
    public String toString() {
        return type.text()+" " + size;
    }
}

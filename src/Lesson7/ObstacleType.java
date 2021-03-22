package Lesson7;

public enum ObstacleType {
    WALL("Стена"), ROAD("Дорожка");

    private String text;
    ObstacleType(String text){
        this.text=text;
    }
    public String text(){
        return text;
    }
}

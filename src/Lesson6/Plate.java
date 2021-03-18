package Lesson6;

public class Plate {
    private int food;

    public int getFood() {
        return food;
    }
    public void addFood(int value){
        this.food+=value;
        System.out.println("Жалкий человек насыпал "+value+" еды в тарелку");
    }

    public Plate(int foodValue){
        this.food=foodValue;
        System.out.println("Добавлена тарелка с едой, количество:"+food);
    }
    public Plate (){
        this.food=0;
        System.out.println("Добавлена пустая тарелка");
    }

    public boolean removeFood (int value){
        if (value > this.food){
            return false;
        } else {
            this.food -= value;
            System.out.println("В тарелке осталось:" + food);
            return true;
        }

    }
}



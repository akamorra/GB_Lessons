package Lesson6;

public class Cat {
    private int hunger;
    private boolean starving;
    private String name;

    public Cat (String name){
        this.name = name;
        this.hunger = 0;
        starving=true;
    }

    public void eat (Plate plate, int amount){
        if (plate.removeFood(amount)){
            this.hunger+=amount;
            System.out.println(name+" поел "+amount+" еды");
            starving=false;
        } else {
            this.hunger=0;
            System.out.println("Кот отказался есть, слишком мааало мао мао");
            starving=true;
        }
    }

    @Override
    public String toString(){
        return "Кот "+this.name+"; Голодает: "+starving+"\n";
    }
}

package Lesson6;

public class Main {
    public static void main(String[] args) {
        Plate plate = new Plate(300);
        Cat[] catsArray = {
                new Cat("Васька"),
                new Cat("Петька"),
                new Cat("Жорик"),
                new Cat("Шмыга"),
                new Cat("Совесть"),
                new Cat("Апполинарий"),
                new Cat("Сэр Кот")
        };

        for (int i=0; i<catsArray.length; i++){
            catsArray[i].eat(plate, (i+1)*50);
            System.out.println(catsArray[i]);
        }
        Plate plate1 = new Plate();
        plate1.addFood(500);
    }
}

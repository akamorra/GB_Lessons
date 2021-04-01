package Lesson8;

import java.lang.reflect.Type;

//        Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4.
//                При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
//        Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
//                Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит
//                символ или текст вместо числа), должно быть брошено исключение MyArrayDataException
//                с детализацией, в какой именно ячейке лежат неверные данные.
//        В методе main() вызвать полученный метод, обработать возможные исключения MyArraySizeException
//                и MyArrayDataException и вывести результат расчета.
public class Main {
    static final int size = 4;
    static String[][] arr1 = new String[][] {
            {"1","0","s","5"},
            {"-1","0","44","5"},
            {"1","0","12","5"},
            {"4","22","11","3"}
    };
    static String[][] arr2 = new String[][] {
            {"11","0","55","5"},
            {"-1","0","44","5"},
            {"1","0","122","5"},
            {"4","22","46","3"}
    };
    static String[][] arr3 = new String[][] {
            {"1","0","3456789012345","5","6"},
            {"-1","0","44","5","6"},
            {"1","0","12","5","55"},
            {"4","22","11","555","3"}

    };


    public static void main(String[] args) {
        try {
            System.out.println("Сумма arr1 :"+sum(arr1));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Сумма arr2 :"+sum(arr2));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Сумма arr3 :"+sum(arr3));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static int sum(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if(array[0].length!=4 || array.length !=4) throw new MyArraySizeException("\n Array must be 4 x 4!");
        int sum=0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    sum+=Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e){

                    throw new MyArrayDataException("\n Data at pos ["+i+"]["+j+"] not numeric! ");

                }
            }
        }
        return sum;
    }

    public static class MyArraySizeException extends Throwable{
        public MyArraySizeException(String s){ super(s); }
    }
    public static class MyArrayDataException extends Throwable{
        public MyArrayDataException(String s){ super(s); }
    }
}

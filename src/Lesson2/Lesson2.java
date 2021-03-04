package Lesson2;

public class Lesson2 {
    public static void main(String[] args) {
        int[] inputArray_5_7=new int[]{89, 5, 44, 3, 1, 22, -15, 2, 8, 9, 115};
        int[] inputArray_6=new int[]{1,0,5,6,8,2,11,4,7};
        function_1();
        function_2();
        function_3();
        function_4();
        function_5(inputArray_5_7);
        System.out.println(function_6(inputArray_6)+"\n");
        function_7(inputArray_5_7, 5);
    }

    public static void function_1(){
        /*
         * 1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
         * С помощью цикла и условия заменить 0 на 1, 1 на 0;
         */
        int length=10;
        int[] arr = new int[length];
        for (int i =0; i<arr.length; i++) arr[i] = i%2;
        System.out.println("Задание 1: ");
        System.out.println("Исходный массив: ");
        printArray(arr);
        for (int i=0; i<arr.length; i++){
            arr[i] = (arr[i]==0) ? 1 : 0;
        }
        System.out.println("Результат работы функции: ");
        printArray(arr);
    }

    public static void function_2(){
        /* 2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21; */
        int length=8;
        int[] arr = new int[length];
        System.out.println("Задание 2: ");
        System.out.println(" Исходный массив: ");
        printArray(arr);
        for (int i=0; i<arr.length; i++){
            arr[i] = i*3;
        }
        System.out.println("Результат работы функции: ");
        printArray(arr);

    }

    public static void function_3(){
        /* 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2; */
        int[] arr = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        System.out.println("Задание 3: \n");
        System.out.println("Исходный массив: ");
        printArray(arr);
        for (int i=0; i<arr.length; i++) arr[i] *= (arr[i]<6) ? 2 : 1;
        System.out.println("Результат работы функции: ");
        printArray(arr);


    }

    public static void function_4(){
        /* 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
         * и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
         */
        int[][] arr = new int[9][9];
        for (int i=0; i<arr.length; i++){
            for (int j=0; j<arr[i].length; j++){
                arr[i][j]=i*j+13;
            }
        }
        System.out.println("Задание 4: \n");
        System.out.println("Исходный массив: ");
        printArray(arr);
        for (int i=0; i<arr.length; i++) {
            arr[i][i]=1;
            for (int j=arr.length-1; j>=0; j--){
                arr[j][arr.length-1-j]=1;
            }
        }
        System.out.println("Результат работы функции: ");
        printArray(arr);


    }

    public static void function_5(int[] arr){
        /* 5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета); */
        System.out.println("Задание 5: \n");
        System.out.println("Исходный массив: ");
        printArray(arr);
        int minimum=arr[0], maximum=arr[0];
        for (int o: arr){
            maximum = (maximum<o) ? o : maximum;
            minimum = (minimum>o) ? o : minimum;
        }
        System.out.println("Результат работы функции: ");
        System.out.println("Максимум: "+maximum+"   Минимум :"+minimum+"\n");

    }

    public static boolean function_6(int[] arr){
        /*
         * 6. ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
         * если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры:
         * checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
         * граница показана символами ||, эти символы в массив не входят.
         */
        System.out.println("Задание 6: \n");
        System.out.println("Исходный массив: ");
        printArray(arr);
        System.out.println("Результат работы функции: ");
        boolean result=false;
        int sumLeft=0, sumRight=0;

        for(int i=0; i<arr.length; i++){
            sumLeft+=arr[i];
            System.out.print(arr[i]+" ");
            for (int j=arr.length-1; j>=i; j--){
                sumRight+=arr[j];
                if (sumLeft==sumRight) {
                    System.out.print("|| ");
                    result=true;
                }
            }
            sumRight=0;
        }
        System.out.println();
        return result;
    }

    public static void function_7(int[] arr, int n){
        /*
         * 7. Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным),
         * при этом метод должен сместить все элементы массива на n позиций. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
         */
        final int LEFT=1, RIGHT=-1;
        System.out.println("Задание 7: \n");

        System.out.println("n ="+n+"\nИсходный массив: ");
        printArray(arr);
        int buf;

        if (n!=0) {
            int side = (n<0) ? LEFT : RIGHT;
            for (int count=Math.abs(n); count>0; count--){
                switch (side) {
                    case LEFT:
                        buf = arr[0];
                        for (int i=0; i<arr.length-1; i++) arr[i]=arr[i+1];
                        arr[arr.length-1]=buf;
                        break;
                    case RIGHT:
                        buf = arr[arr.length-1];
                        for (int i=arr.length-1; i>0; i--) arr[i]=arr[i-1];
                        arr[0]=buf;
                        break;
                }
            }
        } else {
            System.out.println("Массив не изменился, т.к. n=0");
        }
        System.out.println("Результат работы функции: ");
        printArray(arr);


    }

    public static void printArray(int arr[]) {
        for (int i : arr) System.out.print(i+" ");
        System.out.println("\n");
    }
    public static void printArray(int arr[][]){
        for (int i=0; i<arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) System.out.print(arr[i][j] + " ");
            System.out.println("\n");
        }
    }

}
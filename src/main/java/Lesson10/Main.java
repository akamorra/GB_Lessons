package Lesson10;

import java.util.*;

//Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
//        Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
//        Посчитать, сколько раз встречается каждое слово.

public class Main {
    public static void main(String[] args) {
        wordsCollection();
        Telephoner.add("Алешкин","+1234");
        Telephoner.add("Алешкин","+2345");
        Telephoner.add("Алешкин","+876543");
        Telephoner.add("Васечкин","+14254354");
        Telephoner.add("Тюлякбаев","+543222");
        Telephoner.showAll();
        Telephoner.get("Улюлюев");
        Telephoner.get("Алешкин");
    }

    private static void wordsCollection() {
        var words = new ArrayList<String>();
        words.addAll(Arrays.asList("кочка","кучка","пень","кочка","кучка","кочка","жучка",
                                     "огонь","кучка","кочка","черепаха","кочка","кучка",
                                        "цветок","четыре","кочка","тест","семь","кони"));
        var wordsUnique = new HashSet<String>();
        wordsUnique.addAll(words);
        System.out.println("Исходный список: \n"+words+"\n");
        System.out.println("Список уникальных слов: \n"+wordsUnique+"\n");
        System.out.println("Слова повторяются: ");
        for (String s:wordsUnique) {
            int i=0;
            System.out.print(s + " повторяется: ");
            for (String s1:words){
                if (s.equals(s1)){
                    i++;
                }
            }
            System.out.println(i+ " раз");
        }
    }
}

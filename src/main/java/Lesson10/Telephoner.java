package Lesson10;

import java.util.*;
import java.util.function.BiConsumer;

//        Написать простой класс Телефонный Справочник, который хранит в себе список фамилий
//        и телефонных номеров. В этот телефонный справочник с помощью метода add()
//        можно добавлять записи, а с помощью метода get() искать номер телефона по фамилии.
//        Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
//        тогда при запросе такой фамилии должны выводиться все телефоны. Желательно не добавлять
//        лишний функционал (дополнительные поля (имя, отчество, адрес), взаимодействие с пользователем
//        через консоль и т.д). Консоль использовать только для вывода результатов проверки телефонного справочника.
public abstract class Telephoner {
    private static TreeMap<String,Set<String>> map = new TreeMap<>();

    public static void add(String secondName, String phoneNumber){
        Set<String> numbers = map.getOrDefault (secondName, new HashSet<>());
        numbers.add(phoneNumber);
        map.put(secondName, numbers);
    }

    public static void get(String secondName){
        if (map.containsKey(secondName)){
            System.out.println("\n По запросу \""+secondName+"\" найдено: ");
            for (String s:map.get(secondName)
                 ) {
                System.out.println(s);
            }
        }else{
            System.out.println("\n Нет такого контакта в базе : \""+secondName+"\"");
        }
    }
    public static void showAll(){
        System.out.println("\n Телефонный справочник: ");
        map.forEach((s, strings) -> {
            System.out.println(s+" : "+strings);
        });
    }
}


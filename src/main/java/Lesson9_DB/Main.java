package Lesson9_DB;


//Создать класс кота, с полями имя, цвет, что-нибудь еще.
//        Создать в базе таблицу с котами.
//        ***Сделть класс для работы с котами в бд: запись кота в БД, чтение кота из базы, изменение...

public class Main {
    static SQLite_Handler handler = new SQLite_Handler();
    public static void main(String[] args) {

        Cat.createSQLBase(handler);
        System.out.println("\n\nДо добавления котов:");
        Cat.loadData(handler);
        Cat.showAll();

        Cat[] catsArray = new Cat[] {
                new Cat ("Валера","черный",10),
                new Cat ("Гоша","красный",25),
                new Cat ("Гость","синий",1)
        };
        Cat oneMoreKitty = new Cat ("Черепаха","ветхий",16);

        System.out.println("\n\nПосле добавления котов: \n");
        Cat.addCatToBase(handler,catsArray);
        Cat.addCatToBase(handler,oneMoreKitty);
        Cat.loadData(handler);
        Cat.showAll();

        System.out.print("Попытка изменить ID=8 - ");
        Cat.editCatSQL(handler,8,"Алёша","Фиолетовенький",4);
        Cat.loadData(handler);
        Cat.showAll();

        System.out.print("Попытка исключить ID=12 - ");
        Cat.deleteCatSQL(handler,12);
        Cat.loadData(handler);
        Cat.showAll();

        /* Закомментировано до востребования
        System.out.println("Очищаем базу");
        Cat.eraseCatSQL(handler);
        Cat.loadData(handler);
        Cat.showAll();
        */
    }
}

package Lesson9_DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Cat {
    private String name;
    private String color;
    private int id;
    private int age;
    private static ArrayList<Cat> catsList = new ArrayList<>();



    public Cat (String name, String color, int age){
        this.name=name;
        this.color=color;
        this.age=age;
        catsList.add(this);
        catsList.trimToSize();
    }

    /**Конструктор для объектов загруженных из базы данных **/
    public Cat (String name, String color, int age, int id){
        this.name=name;
        this.color=color;
        this.age=age;
        this.id=id;
    }

    public static void editCatSQL(SQLite_Handler handler, int id, String newName, String newColor, int newAge){
        try {
            handler.connect("cats.db");
            ResultSet rs = handler.statement.executeQuery("select * from cats where id ="+id);
            if (rs.next()) {
                System.out.println(" Изменен ID "+id+"\n");
            String data = "update cats "+
                          "set name = ?,"+
                          " color = ?,"+
                          " age = ? " +
                    " where (id = ?) AND (exists (select * from cats where id=?));";
            handler.preparedStatement = handler.connection.prepareStatement(data);
            handler.preparedStatement.setString(1, newName);
            handler.preparedStatement.setString(2,  newColor);
            handler.preparedStatement.setInt(3,  newAge);
            handler.preparedStatement.setInt(4,  id);
            handler.preparedStatement.setInt(5,  id);
            handler.preparedStatement.executeUpdate();
            } else {
                System.out.println("No such ID at base : "+id);
            }
            rs.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            handler.disconnect();

        }
    }
    public static void deleteCatSQL(SQLite_Handler handler, int id){
        try {
            handler.connect("cats.db");
            ResultSet rs = handler.statement.executeQuery("select * from cats where id ="+id);
            if (rs.next()) {
                System.out.println(" Исключен ID "+id+"\n");
                String data = "delete from cats " +
                        " where (id = ?) AND (exists (select * from cats where id=?));";
                handler.preparedStatement = handler.connection.prepareStatement(data);
                handler.preparedStatement.setInt(1, id);
                handler.preparedStatement.setInt(2, id);
                handler.preparedStatement.executeUpdate();
            }else {
                System.out.println("No such ID at base : "+id);
            }
            rs.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            handler.disconnect();
        }
    }
    public static void eraseCatSQL(SQLite_Handler handler){
        try {
            handler.connect("cats.db");
            ResultSet rs = handler.statement.executeQuery("select * from cats");
            while (rs.next()) {
                int id = rs.getInt("id");
                System.out.print(" Исключен ID "+id+"\n");
                String data = "delete from cats " +
                        " where (id = ?) AND (exists (select * from cats where id=?));";
                handler.preparedStatement = handler.connection.prepareStatement(data);
                handler.preparedStatement.setInt(1, id);
                handler.preparedStatement.setInt(2, id);
                handler.preparedStatement.executeUpdate();
            }
            rs.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            handler.disconnect();
        }
    }

    public static void createSQLBase (SQLite_Handler handler){
        try {
            handler.connect("cats.db");
            handler.statement.execute("create table if not exists cats (id integer primary key autoincrement, name text, color text, age integer);");
            System.out.println("Table connected! \n");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            handler.disconnect();
        }
    }

    /**
     *  Загрузка базы данных в программу
     *  Так же используется для того, чтобы обновить эти данные в программе после внесения изменений в базу
     *  **/
    public static void loadData (SQLite_Handler handler){
        try {
            catsList=new ArrayList<Cat>();
            handler.connect("cats.db");
            String data = "select count(*) from cats";
            ResultSet rs = handler.statement.executeQuery(data);
            rs.next();
            int catsCount=rs.getInt(1);
            rs.close();
            System.out.println("Количество котов в базе: "+catsCount);
            data="select * from cats";
            rs=handler.statement.executeQuery(data);
            int i=0;
            while (i<catsCount && rs.next()){
                i++;
                String name = rs.getString("name");
                String color = rs.getString("color");
                int age=rs.getInt("age");
                int id = rs.getInt("id");
                catsList.add(new Cat(name, color ,age,id));
            }
            rs.close();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            handler.disconnect();
        }
    }

    public static void addCatToBase (SQLite_Handler handler, Cat obj){
        try {
            handler.connect("cats.db");
            handler.statement.execute("insert into cats "+obj.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            handler.disconnect();
        }
    }
    public static void addCatToBase (SQLite_Handler handler, Cat[] obj){
        try {
            handler.connect("cats.db");
            for (Cat c: obj) {
                handler.statement.execute("insert into cats " + c.toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            handler.disconnect();
        }
    }

    public static void showAll(){
        for (Cat c : catsList){
            System.out.println("ID:"+c.id+" : "+c.toString());

        }
    }
    @Override
    public String toString () {
        return "(name, color, age) values ('"+this.name+"','"+this.color+"',"+this.age+")";
    }
}

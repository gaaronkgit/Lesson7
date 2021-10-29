import org.sqlite.SQLite;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBHandler
{
    static Connection conn = null;
    static String dbName = "weather.db";
    static void CreateDB() {
        String db = "jdbc:sqlite:" + dbName;

        File f = new File(dbName);
        if (!f.exists()) {

            try (Connection conn = DriverManager.getConnection(db)) {
                if (conn != null)
                {
                    DatabaseMetaData meta = conn.getMetaData();
                    System.out.println("The driver name is " + meta.getDriverName());
                    System.out.println("A new database has been created.");
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else
        {
            System.out.println("База существует! Продолжаем...");
        }
    }

    public static void connect(){
        if(conn != null){
            return;
        }
        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbName);
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static boolean tableExists(String tableName){
        connect();
        try{
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getTables(null, null, tableName, null);
            rs.last();
            return rs.getRow() > 0;
        }
        catch(SQLException ex){
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    static void insertTemperature(TemperatureTemp temperature)
    {
        connect();
        try {
            PreparedStatement insert = conn.prepareStatement("INSERT INTO temperature (city,date, minimum,maximum, weather) " +
                    "VALUES (?, ?, ?, ?, ?)");
            insert.setObject(1,temperature.getCity());
            insert.setObject(2,temperature.getDate());
            insert.setObject(3,temperature.getMinimum());
            insert.setObject(4,temperature.getMaximum());
            insert.setObject(5,temperature.getWeather());
            insert.execute();

            System.out.println("Строка: " + temperature.getCity() + "|" + temperature.getDate()
                    + "|" + temperature.getMinimum() + "|" + temperature.getMaximum() + "|" + temperature.getWeather() + " - Успешно добавлена!");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    static void deleteAllDataFromTemperature()
    {
        connect();
        try {
            PreparedStatement insert = conn.prepareStatement("DELETE FROM temperature");
            insert.execute();

            System.out.println("Таблица temperature очищена");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    static List<TemperatureTemp> GetDataFromDB()
    {
        List<TemperatureTemp> tt = new ArrayList<>();
        try(Statement statement = conn.createStatement())
        {
            ResultSet res = statement.executeQuery("select * from temperature");
            while (res.next())
            {
                tt.add(new TemperatureTemp(res.getString("city"),
                        res.getString("date"),
                        res.getDouble("minimum"),
                        res.getDouble("maximum"),
                        res.getString("weather")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tt;
    }

}

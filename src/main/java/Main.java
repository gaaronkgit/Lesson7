import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        String city = "Moscow";
        String cityKey = RequestHandler.getCity(city);
        System.out.println("Recived key: " + cityKey);
        String json = RequestHandler.getWeather(cityKey);

        ObjectMapper om = new ObjectMapper();
        Root root = om.readValue(json, Root.class);

        DBHandler.CreateDB();


        for(int i = 0; i < root.dailyForecasts.size(); i++)
        {
            TemperatureTemp tt = new TemperatureTemp(city,root.dailyForecasts.get(i).getDate(),root.dailyForecasts.get(i).temperature.minimum.value,root.dailyForecasts.get(i).temperature.maximum.value,root.dailyForecasts.get(i).day.getIconPhrase());
            DBHandler.insertTemperature(tt);
        }

        for(int i = 0; i < DBHandler.GetDataFromDB().size(); i++)
        {
            System.out.println("Данные из БД. Погода в городе " + city);
            System.out.println(DBHandler.GetDataFromDB().get(i).date + "|"
                    + DBHandler.GetDataFromDB().get(i).getMinimum() + "|"
                    + DBHandler.GetDataFromDB().get(i).getMaximum() + "|"
                    + DBHandler.GetDataFromDB().get(i).getWeather() + "|");
        }

    }
}

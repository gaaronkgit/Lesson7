import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        String cityKey = RequestHandler.getCity("Moscow");
        System.out.println("Recived key: " + cityKey);
        String json = RequestHandler.getWeather(cityKey);

        ObjectMapper om = new ObjectMapper();
        Root root = om.readValue(json, Root.class);

        for(int i = 0; i < root.dailyForecasts.size(); i++)
        {

        }


    }
}

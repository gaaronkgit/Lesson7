import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public  class RequestHandler
{
    static OkHttpClient client = new OkHttpClient();
    static ObjectMapper mapper = new ObjectMapper();

    public static String getCity(String name) throws IOException {
        String cityKey = "";
        HttpUrl urlCity = new HttpUrl.Builder().scheme("http").host("dataservice.accuweather.com")
                .addPathSegment("locations").addPathSegment("v1").addPathSegment("cities").addPathSegment("search")
                .addQueryParameter("apikey","CT1LRJznpz0mASHzUGYo0ukaIWektxT8")
                .addQueryParameter("q",name).build();

        Request request = new Request.Builder().addHeader("Accept","application/json").url(urlCity).build();

        Response response = client.newCall(request).execute();

        String json = response.body().string();
        if(!response.isSuccessful())
        {
            throw new IOException("Не удалось выпонить запрос");
        }


        if(mapper.readTree(json).size() > 0)
        {
            String cityName = mapper.readTree(json).get(0).at("/LocalizedName").asText();
            String countryName = mapper.readTree(json).get(0).at("/Country/LocalizedName").asText();
            cityKey = mapper.readTree(json).get(0).at("/Key").asText();
            System.out.println(cityName + "\r\n" + countryName + "\r\nkey:" + cityKey);
        }
        else
        {
            throw new IOException("Не удалось получить город!");
        }

        return cityKey;
    }

    public static String getWeather(String cityKey) throws IOException {

        HttpUrl urlWeather = new HttpUrl.Builder().scheme("http").host(Params.HOST)
                .addPathSegment("forecasts")
                .addPathSegment("v1")
                .addPathSegment("daily")
                .addPathSegment("5day")
                .addPathSegment(cityKey)
                .addQueryParameter("apikey",Params.API_KEY)
                .addQueryParameter("metriv",Params.API_KEY)
                .build();

        Request request = new Request.Builder().addHeader("Accept","application/json").url(urlWeather).build();

        Response response = client.newCall(request).execute();

        if(!response.isSuccessful())
        {
            throw new IOException("Не удалось выпонить запрос\r\n" + response.code() + "\r\n" + response.body().string());
        }
        return response.body().string();
    }
}

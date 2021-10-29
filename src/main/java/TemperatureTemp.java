import java.util.Date;

public class TemperatureTemp
{
    String city;
    String date;
    double minimum;
    double maximum;
    String weather;

    public TemperatureTemp(String city, String date, double minimum, double maximum, String weather) {
        this.city = city;
        this.date = date;
        this.minimum = minimum;
        this.maximum = maximum;
        this.weather = weather;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class DailyForecast
{
    @JsonProperty("Date")
    public Date date;
    @JsonProperty("EpochDate")
    public int epochDate;
    @JsonProperty("Temperature")
    public Temperature temperature;
    @JsonProperty("Day")
    public Day day;
    @JsonProperty("Night")
    public Night night;

    public DailyForecast()
    {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getEpochDate() {
        return epochDate;
    }

    public void setEpochDate(int epochDate) {
        this.epochDate = epochDate;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Night getNight() {
        return night;
    }

    public void setNight(Night night) {
        this.night = night;
    }

    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    public String getMobileLink() {
        return mobileLink;
    }

    public void setMobileLink(String mobileLink) {
        this.mobileLink = mobileLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @JsonProperty("Sources")
    public List<String> sources;
    @JsonProperty("MobileLink")
    public String mobileLink;
    @JsonProperty("Link")
    public String link;
}

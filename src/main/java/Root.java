import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Root{
    @JsonProperty("Headline")
    public Headline headline;

    @JsonProperty("DailyForecasts")
    public List<DailyForecast> dailyForecasts;

    public Root() {
    }

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public List<DailyForecast> getDailyForecasts() {
        return dailyForecasts;
    }

    public void setDailyForecasts(List<DailyForecast> dailyForecasts) {
        this.dailyForecasts = dailyForecasts;
    }
}
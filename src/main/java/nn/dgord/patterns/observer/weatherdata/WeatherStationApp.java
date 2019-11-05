package nn.dgord.patterns.observer.weatherdata;

import nn.dgord.patterns.observer.weatherdata.domain.CommonWeatherData;
import nn.dgord.patterns.observer.weatherdata.observer.CurrentWeatherDisplay;
import nn.dgord.patterns.observer.weatherdata.observer.DailyAverageWeatherDisplay;
import nn.dgord.patterns.observer.weatherdata.observer.WeeklyAverageWeatherStatisticsDisplay;
import nn.dgord.patterns.observer.weatherdata.service.WeatherDataServiceImpl;

public class WeatherStationApp {
    public static void main(String[] args) throws InterruptedException {
        // Scenario
        CommonWeatherData commonWeatherData = CommonWeatherData.builder()
                .humidity(80.1f)
                .pressure(3.2f)
                .temperature(28.1f)
                .build();

        WeatherDataServiceImpl weatherDataService = new WeatherDataServiceImpl();
        weatherDataService.setUpdatedData(commonWeatherData);

        CurrentWeatherDisplay observer = new CurrentWeatherDisplay(weatherDataService);
        weatherDataService.notifyObservers();
        System.out.println(observer.display());

        CurrentWeatherDisplay observer2 = new CurrentWeatherDisplay(weatherDataService);
        commonWeatherData.setHumidity(13.2f);
        commonWeatherData.setTemperature(40.1f);
        weatherDataService.setUpdatedData(commonWeatherData);
        System.out.println(weatherDataService.notifyObservers());

        System.out.println(observer.display());
        System.out.println(observer2.display());

        System.out.println(weatherDataService.removeObserver(observer2));

        String message = "Proceeding to the next scenario";
        System.out.println(message);
        Thread.sleep(2000);

        System.out.println(weatherDataService.cleanAll());
        weatherDataService.setUpdatedData(commonWeatherData);

        // one CurrentWeatherDisplay
        CurrentWeatherDisplay currentDisplay = new CurrentWeatherDisplay(weatherDataService);


        // one DailyAverageWeatherDisplay
        DailyAverageWeatherDisplay dailyDisplay = new DailyAverageWeatherDisplay(weatherDataService);

        // one Weekly display, but there is should be an error
        WeeklyAverageWeatherStatisticsDisplay weeklyDisplay = new
                WeeklyAverageWeatherStatisticsDisplay(weatherDataService);

        weatherDataService.notifyObservers();


        System.out.println(currentDisplay.display());
        System.out.println(dailyDisplay.display());
        System.out.println(weeklyDisplay.display());
    }
}

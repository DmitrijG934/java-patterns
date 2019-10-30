package nn.dgord.patterns.observer.weatherdata;

import nn.dgord.patterns.observer.weatherdata.domain.WeatherData;
import nn.dgord.patterns.observer.weatherdata.observer.CurrentWeatherDisplay;
import nn.dgord.patterns.observer.weatherdata.service.WeatherDataServiceImpl;

public class WeatherStationApp {
    public static void main(String[] args) {
        // Scenario
        WeatherData weatherData = WeatherData.builder()
                .humidity(80.1f)
                .pressure(3.2f)
                .temperature(28.1f)
                .build();

        WeatherDataServiceImpl weatherDataService = new WeatherDataServiceImpl();
        weatherDataService.setUpdatedData(weatherData);

        CurrentWeatherDisplay observer = new CurrentWeatherDisplay(weatherDataService);
        weatherDataService.notifyObservers();
        System.out.println(observer.display());

        CurrentWeatherDisplay observer2 = new CurrentWeatherDisplay(weatherDataService);
        weatherData.setHumidity(13.2f);
        weatherData.setTemperature(40.1f);
        weatherDataService.setUpdatedData(weatherData);
        System.out.println(weatherDataService.notifyObservers());

        System.out.println(observer.display());
        System.out.println(observer2.display());

        System.out.println(weatherDataService.removeObserver(observer2));

    }
}

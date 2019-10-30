package nn.dgord.patterns.observer.weatherdata.service;

import nn.dgord.patterns.BaseTest;
import nn.dgord.patterns.observer.weatherdata.domain.ResponseData;
import nn.dgord.patterns.observer.weatherdata.domain.WeatherData;
import nn.dgord.patterns.observer.weatherdata.observer.CurrentWeatherDisplay;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static nn.dgord.patterns.observer.weatherdata.service.WeatherDataServiceImpl.OperationType.UPDATE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class WeatherDataServiceImplTest extends BaseTest {
    private WeatherDataServiceImpl serviceUnderTest;

    @Before
    public void setUp() {
        serviceUnderTest = new WeatherDataServiceImpl();
    }

    @Test
    public void when_constructorIsCalling_thenInstanceNotNull() {
        assertNotNull(serviceUnderTest);
    }

    @Test
    public void when_setUpdatedData_thenWeatherDataNotNull() {
        WeatherData weatherData = WeatherData.builder()
                .temperature(1f)
                .pressure(1f)
                .humidity(1f)
                .build();
        ResponseData actual = serviceUnderTest.setUpdatedData(weatherData);
        assertEquals(UPDATE, actual.getOperationType());
        assertTrue(actual.isSucceed());
        assertNotNull(actual.getReceivedData());
        assertNotNull(serviceUnderTest.getWeatherData());
    }

    @Test
    @Ignore
    public void when_registerObserverAndObserverAlreadyExists_thenOperationIsFailed() {
        CurrentWeatherDisplay observer = new CurrentWeatherDisplay(serviceUnderTest);
        ResponseData succeedResponseData = serviceUnderTest.registerObserver(observer);
        ResponseData badResponseData = serviceUnderTest.registerObserver(observer);

        assertTrue(succeedResponseData.isSucceed());
        assertNotEquals(succeedResponseData, badResponseData);
        assertFalse(badResponseData.isSucceed());
    }
}
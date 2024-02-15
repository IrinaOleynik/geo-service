package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageSenderImplTest {

    @Mock
    GeoServiceImpl geoService;
    @Mock
    LocalizationServiceImpl localizationService;
    @InjectMocks
    MessageSenderImpl messageSender;

    @Test
    void test_send_russian_text() {
        Map<String, String> map = new HashMap<>();
        final String ip = "172.10.25.30";
        final String expected = "Добро пожаловать";

        when(geoService.byIp(ip)).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String actual = messageSender.send(map);
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void test_send_american_text() {
        Map<String, String> map = new HashMap<>();
        final String ip = "96.10.25.30";
        final String expected = "Welcome";

        when(geoService.byIp(ip)).thenReturn(new Location("New York", Country.USA, null, 0));
        when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String actual = messageSender.send(map);
        Assertions.assertEquals(expected, actual);
    }
}
package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

class GeoServiceImplTest {

    public static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.0.32.111", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.44.183.159", new Location("New York", Country.USA, null, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void test_byIp(String ip, Location expected) {
        GeoServiceImpl geoService = Mockito.spy(GeoServiceImpl.class);
        Location actual = geoService.byIp(ip);
        Assertions.assertEquals(expected.getCity(), actual.getCity());
        Assertions.assertEquals(expected.getCountry(), actual.getCountry());
        Assertions.assertEquals(expected.getStreet(), actual.getStreet());
    }


}
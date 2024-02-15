package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;

import java.util.stream.Stream;

class LocalizationServiceImplTest {

    public static Stream<Arguments> textCountry() {
        return Stream.of(
                Arguments.of(Country.BRAZIL, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome"),
                Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.RUSSIA, "Добро пожаловать")
        );
    }

    @ParameterizedTest
    @MethodSource("textCountry")
    void locale(Country country, String expected) {
        LocalizationServiceImpl localizationService = Mockito.spy(LocalizationServiceImpl.class);
        String actual = localizationService.locale(country);
        Assertions.assertEquals(expected, actual);
    }
}
package i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

public class LocalizationServiceTests {

    static LocalizationService localizationService;

    @BeforeAll
    static void setUp() {
        localizationService = new LocalizationServiceImpl();
    }

    public static Stream<Arguments> getReturnsEnglishTextTestParameters() {
        return Stream.of(
                Arguments.of(Country.USA),
                Arguments.of(Country.BRAZIL),
                Arguments.of(Country.GERMANY)
        );
    }

    @Test
    void serviceReturnsRussianTextWhenCountryIsRussiaTest() {
        String expectedMessage = "Добро пожаловать";

        String actualMessage = localizationService.locale(Country.RUSSIA);

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @MethodSource("getReturnsEnglishTextTestParameters")
    void serviceReturnsEnglishTextWhenCountryIsNotRussiaTest(Country country) {
        String expectedMessage = "Welcome";

        String actualMessage = localizationService.locale(country);

        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
package geo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GeoServiceTests {

    static GeoService geoService;

    @BeforeAll
    static void setUp() {
        geoService = new GeoServiceImpl();
    }

    public static Stream<Arguments> getReturnsExistingLocationTestParameters() {
        return Stream.of(
                Arguments.of(GeoServiceImpl.MOSCOW_IP, new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of(GeoServiceImpl.NEW_YORK_IP, new Location("New York", Country.USA, " 10th Avenue", 32))
        );
    }

    @Test
    void serviceReturnsEmptyAddressWhenIPEqualsLocalhostTest() {
        String localhostIp = GeoServiceImpl.LOCALHOST;

        Location actualLocation = geoService.byIp(localhostIp);

        assertNull(actualLocation.getCountry());
        assertNull(actualLocation.getCity());
        assertNull(actualLocation.getStreet());
    }

    @ParameterizedTest
    @MethodSource("getReturnsExistingLocationTestParameters")
    void serviceReturnsExistingLocationAddressWhenIPNotLocalhostTest(String ip, Location location) {
        Location actualLocation = geoService.byIp(ip);

        assertEquals(location.getCountry(), actualLocation.getCountry());
        assertEquals(location.getCity(), actualLocation.getCity());
        assertEquals(location.getStreet(), actualLocation.getStreet());
        assertEquals(location.getBuiling(), actualLocation.getBuiling());
    }
}
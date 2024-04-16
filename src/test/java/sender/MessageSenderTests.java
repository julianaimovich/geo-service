package sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderTests {

    static GeoService geoService;
    static LocalizationService localizationService;
    static MessageSender messageSender;

    @BeforeAll
    static void setUp() {
        geoService = Mockito.spy(GeoServiceImpl.class);
        localizationService = Mockito.spy(LocalizationServiceImpl.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    public void senderSendsOnlyRussianTextWhenIPIsRussianTest() {
        String ruIpAddress = "172.123.12.19";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ruIpAddress);

        String actualLocale = messageSender.send(headers);

        Assertions.assertEquals(localizationService.locale(Country.RUSSIA), actualLocale);
    }

    @Test
    public void senderSendsOnlyEnglishTextWhenIPIsAmericanTest() {
        String usIpAddress = "96.128.10.21";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, usIpAddress);

        String actualLocale = messageSender.send(headers);

        Assertions.assertEquals(localizationService.locale(Country.USA), actualLocale);
    }
}

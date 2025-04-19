package com.mga44.complaint_service.complaint.integration;

import com.mga44.complaint_service.complaint.service.CountryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = {
        "countryService.url=http://ip-api.com/json/"
})
class CountryServiceIT {

    @Autowired
    CountryService countryService;

    @Test
    void shouldResolveCountryByValidIp() {
        var ip = "169.3.4.2";

        var country = countryService.resolveByIp(ip);

        assertThat(country).hasValue("United States");
    }
}
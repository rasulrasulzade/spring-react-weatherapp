package com.company.server.client;

import com.company.server.client.model.CityWeather;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.error.AnnotationErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "open-weather-map",
        url = "${application.service.open-weather-app.url}"
)
@Component
public interface OpenWeatherMapClient {

    @GetMapping(value = "/data/2.5/weather")
    CityWeather getCityWeatherByID(@RequestParam("id") Long id, @RequestParam("units") String units);

    @Configuration
    class FeignConfiguration {
        @Value("${application.service.open-weather-app.apiKey}")
        private String apiKey;

        @Bean
        Logger.Level feignLoggerLevel() {
            return Logger.Level.FULL;
        }

        @Bean
        public ErrorDecoder feignErrorDecoder() {
            return AnnotationErrorDecoder.builderFor(OpenWeatherMapClient.class)
                    .withResponseBodyDecoder(new JacksonDecoder())
                    .build();
        }

        @Bean
        public Decoder feignDecoder() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return new JacksonDecoder(objectMapper);
        }

        @Bean
        public OkHttpClient client() {
            return new OkHttpClient();
        }

        @Bean
        public RequestInterceptor requestInterceptor() {
            return template -> {
                template.header("Content-Type", "application/json");
                template.query("appid", apiKey);
            };
        }
    }
}
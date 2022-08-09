package com.dido.holidaybay.client;

import com.dido.holidaybay.model.dto.HotelDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class HotelClient {

    private static final String BASE_HOTEL_URL = "http://localhost:7777/hotels/";

    public List<HotelDto> getHotels() {

        WebClient clientBlocking = WebClient.create();

        WebClient.UriSpec<WebClient.RequestBodySpec> hotelRequest =
                (WebClient.UriSpec<WebClient.RequestBodySpec>) clientBlocking.get();
        WebClient.RequestBodySpec requestUri =
                hotelRequest.uri(BASE_HOTEL_URL+"all");

        return requestUri.retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<HotelDto>>() {
                })
                .block();
    }

    public List<HotelDto> getHotelsWithFreeRooms() {

        WebClient clientBlocking = WebClient.create();

        WebClient.UriSpec<WebClient.RequestBodySpec> hotelRequest =
                (WebClient.UriSpec<WebClient.RequestBodySpec>) clientBlocking.get();
        WebClient.RequestBodySpec requestUri =
                hotelRequest.uri(BASE_HOTEL_URL+"all/free-rooms");

        return requestUri.retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<HotelDto>>() {
                })
                .block();
    }

    public HotelDto getHotelById(Long id) {

        WebClient clientBlocking = WebClient.create();

        WebClient.UriSpec<WebClient.RequestBodySpec> hotelRequest =
                (WebClient.UriSpec<WebClient.RequestBodySpec>) clientBlocking.get();
        WebClient.RequestBodySpec requestUri =
                hotelRequest.uri(BASE_HOTEL_URL+"details?id="+id);

        return requestUri.retrieve()
                .bodyToMono(HotelDto.class)
                .block();
    }
}

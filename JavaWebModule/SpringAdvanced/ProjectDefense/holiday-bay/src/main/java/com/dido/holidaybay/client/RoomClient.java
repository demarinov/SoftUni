package com.dido.holidaybay.client;

import com.dido.holidaybay.model.dto.RoomDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class RoomClient {

    public RoomDto getRoomById(Long id) {

        WebClient clientBlocking = WebClient.create();

        WebClient.UriSpec<WebClient.RequestBodySpec> hotelRequest =
                (WebClient.UriSpec<WebClient.RequestBodySpec>) clientBlocking.get();
        WebClient.RequestBodySpec requestUri =
                hotelRequest.uri("http://localhost:7777/rooms/"+id);

        return requestUri.retrieve()
                .bodyToMono(RoomDto.class)
                .block();
    }

    public boolean updateRoomStatus(Long id, boolean isFree) {

        WebClient clientBlocking = WebClient.create();

        WebClient.UriSpec<WebClient.RequestBodySpec> hotelRequest =
                clientBlocking.post();
        WebClient.RequestBodySpec requestUri =
                hotelRequest.uri("http://localhost:7777/rooms/update-status?id="+id+"&free="+isFree)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED);

        return Boolean.TRUE.equals(requestUri.retrieve()
                .bodyToMono(Boolean.class)
                .block());
    }
}

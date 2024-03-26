package com.enigma.auctionapp.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CommonResponse<T>{
    private Integer statusCode;
    private String message;
    private T data;
    private PagingResponse pagingResponse;
}

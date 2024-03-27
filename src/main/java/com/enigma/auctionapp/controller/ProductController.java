package com.enigma.auctionapp.controller;

import com.enigma.auctionapp.constant.AppPath;
import com.enigma.auctionapp.model.request.ProductRequest;
import com.enigma.auctionapp.model.response.CommonResponse;
import com.enigma.auctionapp.model.response.PagingResponse;
import com.enigma.auctionapp.model.response.ProductResponse;
import com.enigma.auctionapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = AppPath.PRODUCT)
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size){

        Page<ProductResponse> productResponses = productService.getAll(page,size);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Retrieved Products")
                        .data(productResponses)
                        .pagingResponse(PagingResponse.builder()
                                .currentPage(page)
                                .totalPage(productResponses.getTotalPages())
                                .size(size)
                                .build())
                        .build());

    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.create(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully Created New Product")
                        .data(productResponse)
                        .build());

    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProductRequest productRequest){
        try {
            ProductResponse productResponse = productService.update(productRequest);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.OK.value())
                            .message("Successfully Update Product")
                            .data(productResponse)
                            .build());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.NO_CONTENT.value())
                            .message(e.getMessage())
                            .build());
        }
    }









}

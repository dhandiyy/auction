package com.enigma.auctionapp.controller;

import com.enigma.auctionapp.constant.AppPath;
import com.enigma.auctionapp.model.request.AdminRequest;
import com.enigma.auctionapp.model.request.CustomerRequest;
import com.enigma.auctionapp.model.response.AdminResponse;
import com.enigma.auctionapp.model.response.CommonResponse;
import com.enigma.auctionapp.model.response.CustomerResponse;
import com.enigma.auctionapp.model.response.PagingResponse;
import com.enigma.auctionapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = AppPath.CUSTOMER)
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping(AppPath.GET_BY_ID)
    @PreAuthorize("hasAnyRole('ROLE_BIDDER', 'ROLE_OWNER')")
    public ResponseEntity<?> getById(@PathVariable String id){
        CustomerResponse customerResponse = customerService.getByIdDto(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get Customer")
                        .data(customerResponse)
                        .build());

    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size){

        Page<CustomerResponse> customerResponses = customerService.getAll(page,size);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get All Customer")
                        .data(customerResponses)
                        .pagingResponse(PagingResponse.builder()
                                .currentPage(page)
                                .totalPage(customerResponses.getTotalPages())
                                .size(size)
                                .build())
                        .build());

    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody CustomerRequest customerRequest){
        try {
            CustomerResponse customerResponse = customerService.update(customerRequest);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.OK.value())
                            .message("Successfully Update Customer")
                            .data(customerResponse)
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

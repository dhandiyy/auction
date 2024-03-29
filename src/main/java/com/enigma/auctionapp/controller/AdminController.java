package com.enigma.auctionapp.controller;

import com.enigma.auctionapp.constant.AppPath;
import com.enigma.auctionapp.model.request.AdminRequest;
import com.enigma.auctionapp.model.response.AdminResponse;
import com.enigma.auctionapp.model.response.CommonResponse;
import com.enigma.auctionapp.model.response.PagingResponse;
import com.enigma.auctionapp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = AppPath.ADMIN)
public class AdminController {
    private final AdminService adminService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody AdminRequest adminRequest){
        AdminResponse adminResponse = adminService.create(adminRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully Created New Admin")
                        .data(adminResponse)
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size){

        Page<AdminResponse> adminResponses = adminService.getAll(page,size);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Admins")
                        .data(adminResponses)
                        .pagingResponse(PagingResponse.builder()
                                .currentPage(page)
                                .totalPage(adminResponses.getTotalPages())
                                .size(size)
                                .build())
                        .build());
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody AdminRequest adminRequest){
        try {
            AdminResponse adminResponse = adminService.update(adminRequest);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.OK.value())
                            .message("Successfully Update Admin")
                            .data(adminResponse)
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

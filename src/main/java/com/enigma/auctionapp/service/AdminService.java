package com.enigma.auctionapp.service;

import com.enigma.auctionapp.model.request.AdminRequest;
import com.enigma.auctionapp.model.response.AdminResponse;
import org.springframework.data.domain.Page;

public interface AdminService {
    AdminResponse create (AdminRequest adminRequest);
    Page<AdminResponse> getAll(Integer page, Integer size);
    AdminResponse update (AdminRequest adminRequest);
}

package com.enigma.auctionapp.service.impl;

import com.enigma.auctionapp.model.entity.Admin;
import com.enigma.auctionapp.model.request.AdminRequest;
import com.enigma.auctionapp.model.response.AdminResponse;
import com.enigma.auctionapp.repository.AdminRepository;
import com.enigma.auctionapp.service.AdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    @Override
    public AdminResponse create(AdminRequest adminRequest) {
        Admin admin = Admin.builder()
                .id(UUID.randomUUID().toString())
                .name(adminRequest.getName())
                .mobilePhone(adminRequest.getMobilePhone())
                .emailAddress(adminRequest.getEmailAddress())
                .build();
        adminRepository.create(admin);

        return getAdminResponse(admin);
    }

    private static AdminResponse getAdminResponse(Admin admin) {
        return AdminResponse.builder()
                .id(admin.getId())
                .name(admin.getName())
                .mobilePhone(admin.getMobilePhone())
                .emailAddress(admin.getEmailAddress())
                .build();
    }

    @Override
    public Page<AdminResponse> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Admin> adminPage = adminRepository.getAllNative(pageable);
        List<AdminResponse> adminResponseList = new ArrayList<>();

        for(Admin admin : adminPage){
            adminResponseList.add(
                    AdminResponse.builder()
                            .name(admin.getName())
                            .mobilePhone(admin.getMobilePhone())
                            .emailAddress(admin.getEmailAddress())
                            .build());
        }

        return new PageImpl<>(adminResponseList, pageable, adminPage.getTotalElements());
    }

    @Transactional
    @Override
    public AdminResponse update(AdminRequest adminRequest) {
        Admin currentAdmin = Admin.builder()
                .id(adminRequest.getId())
                .name(adminRequest.getName())
                .mobilePhone(adminRequest.getMobilePhone())
                .emailAddress(adminRequest.getEmailAddress())
                .build();

        adminRepository.update(currentAdmin);
        return getAdminResponse(currentAdmin);

    }
}

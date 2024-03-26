package com.enigma.auctionapp.service;

import com.enigma.auctionapp.model.entity.Role;
import com.enigma.auctionapp.util.ERole;

public interface RoleService {
    Role getOrSave (ERole eRole);

}

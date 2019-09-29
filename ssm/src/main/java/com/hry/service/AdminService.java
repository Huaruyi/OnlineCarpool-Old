package com.hry.service;

import com.hry.domain.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminService {
    public Admin check(String aname, String apassword, Integer arole);
}

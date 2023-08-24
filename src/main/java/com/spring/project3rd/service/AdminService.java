package com.spring.project3rd.service;

import com.spring.project3rd.domain.admin.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;


}

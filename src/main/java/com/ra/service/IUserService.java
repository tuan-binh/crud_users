package com.ra.service;

import com.ra.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
	Page<Users> findAll(Pageable pageable,String search);
}

package com.ra.service.impl;

import com.ra.model.Users;
import com.ra.repository.IUserRepository;
import com.ra.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
	private final IUserRepository userRepository;
	
	@Override
	public Page<Users> findAll(Pageable pageable, String search) {
		Page<Users> users;
		if(search.isEmpty()) {
			users = userRepository.findAll(pageable);
		} else {
			users = userRepository.findAllByNameContains(search,pageable);
		}
		return users;
	}
}

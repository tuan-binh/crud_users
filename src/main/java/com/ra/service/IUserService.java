package com.ra.service;

import com.ra.dto.request.UserRequest;
import com.ra.exception.CustomException;
import com.ra.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
	Page<Users> findAll(Pageable pageable,String search);
	Users findById(Long findId) throws CustomException;
	Users addNewUser(UserRequest userRequest);
	Users updateUser(UserRequest userRequest,Long updateId) throws CustomException;
	void deleteById(Long deleteId) throws CustomException;
}

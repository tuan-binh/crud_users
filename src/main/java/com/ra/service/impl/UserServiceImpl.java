package com.ra.service.impl;

import com.ra.dto.request.UserRequest;
import com.ra.exception.CustomException;
import com.ra.model.Users;
import com.ra.repository.IUserRepository;
import com.ra.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
	private final IUserRepository userRepository;
	
	@Override
	public Page<Users> findAll(Pageable pageable, String search) {
		Page<Users> users;
		if (search.isEmpty()) {
			users = userRepository.findAll(pageable);
		} else {
			users = userRepository.findAllByNameContains(search, pageable);
		}
		return users;
	}
	
	@Override
	public Users findById(Long findId) throws CustomException {
		return userRepository.findById(findId).orElseThrow(() -> new CustomException("user not found", HttpStatus.NOT_FOUND));
	}
	
	@Override
	public Users addNewUser(UserRequest userRequest) {
		return userRepository.save(toEntity(userRequest));
	}
	
	@Override
	public Users updateUser(UserRequest userRequest, Long updateId) throws CustomException {
		Users userUpdate = findById(updateId);
		userUpdate.setName(userUpdate.getName());
		userUpdate.setEmail(userUpdate.getEmail());
		userUpdate.setDateOfBirth(userRequest.getDateOfBirth());
		userUpdate.setGender(userRequest.getGender());
		userUpdate.setAddress(userUpdate.getAddress());
		return userRepository.save(userUpdate);
	}
	
	@Override
	public void deleteById(Long deleteId) throws CustomException {
		if (userRepository.existsById(deleteId)) {
			userRepository.deleteById(deleteId);
		} else {
			throw new CustomException("user not found", HttpStatus.NOT_FOUND);
		}
	}
	
	public Users toEntity(UserRequest userRequest) {
		return Users.builder()
				  .name(userRequest.getName())
				  .dateOfBirth(userRequest.getDateOfBirth())
				  .gender(userRequest.getGender())
				  .email(userRequest.getEmail())
				  .address(userRequest.getAddress())
				  .build();
	}
}

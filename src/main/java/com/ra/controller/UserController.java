package com.ra.controller;

import com.ra.dto.request.UserRequest;
import com.ra.exception.CustomException;
import com.ra.model.Users;
import com.ra.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
	private final IUserService userService;
	
	@GetMapping
	public ResponseEntity<Page<Users>> findAll(
			  @PageableDefault(page = 0, size = 3, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
			  @RequestParam(defaultValue = "") String search
	) {
		return ResponseEntity.ok().body(userService.findAll(pageable, search));
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> findById(@PathVariable Long userId) throws CustomException {
		return ResponseEntity.ok().body(userService.findById(userId));
	}
	
	@PostMapping
	public ResponseEntity<?> addNewUser(@Valid @RequestBody UserRequest userRequest) {
		return ResponseEntity.created(URI.create("api/v1/users")).body(userService.addNewUser(userRequest));
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable Long userId, @Valid @RequestBody UserRequest userRequest) throws CustomException {
		return ResponseEntity.ok(userService.updateUser(userRequest,userId));
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Long userId) throws CustomException {
		userService.deleteById(userId);
		return ResponseEntity.ok().body("Delete successfully");
	}
	
}

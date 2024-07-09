package com.ra.controller;

import com.ra.model.Users;
import com.ra.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
	private final IUserService userService;
	
	/**
	 * @param pageable Pageable
	 * @param search String
	 * @apiNote handle find all with pagination {
	 * 	page: currPage trang hiện tại
	 *  	size: limit số lượng phần tử của 1 trang
	 *  	sort: viết theo tên trường dữ liệu muốn sort,ASC or DESC
	 *  	search: tìm kiếm tương đối theo tên
	 * }
	 * */
	@GetMapping
	public ResponseEntity<Page<Users>> findAll(
			  @PageableDefault(page = 0, size = 3, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
			  @RequestParam(defaultValue = "") String search
	) {
		return ResponseEntity.ok().body(userService.findAll(pageable, search));
	}
	
}

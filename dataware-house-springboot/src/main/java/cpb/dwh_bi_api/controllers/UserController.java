package cpb.dwh_bi_api.controllers;

import cpb.dwh_bi_api.dto.ApiResponse;
import cpb.dwh_bi_api.dto.request.CreateUserRequest;
import cpb.dwh_bi_api.dto.request.UpdateUserRequest;
import cpb.dwh_bi_api.dto.response.UserResponse;
import cpb.dwh_bi_api.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class UserController {

	private final UserService userService;

	@GetMapping
	public ResponseEntity<ApiResponse<List<UserResponse>>> getAll() {
		log.info("GET all users");
		List<UserResponse> users = userService.getAll();
		return ResponseEntity.ok(ApiResponse.success("Users retrieved successfully", users));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable UUID id) {
		log.info("GET user by id: {}", id);
		UserResponse user = userService.getById(id);
		return ResponseEntity.ok(ApiResponse.success(user));
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<ApiResponse<UserResponse>> getByUsername(@PathVariable String username) {
		log.info("GET user by username: {}", username);
		UserResponse user = userService.getByUsername(username);
		return ResponseEntity.ok(ApiResponse.success(user));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<UserResponse>> create(@Valid @RequestBody CreateUserRequest request) {
		log.info("POST create user: {}", request.getUsername());
		UserResponse created = userService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success("User created successfully", created));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<UserResponse>> update(@PathVariable UUID id,
			@Valid @RequestBody UpdateUserRequest request) {
		log.info("PUT update user: {}", id);
		UserResponse updated = userService.update(id, request);
		return ResponseEntity.ok(ApiResponse.success("User updated successfully", updated));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
		log.info("DELETE user: {}", id);
		userService.delete(id);
		return ResponseEntity.ok(ApiResponse.success("User deleted successfully", null));
	}

	@PostMapping("/{id}/reset-password")
	public ResponseEntity<ApiResponse<UserResponse>> resetPassword(@PathVariable UUID id,
			@RequestParam String newPassword) {
		log.info("POST reset password for user: {}", id);
		UserResponse user = userService.resetPassword(id, newPassword);
		return ResponseEntity.ok(ApiResponse.success("Password reset successfully", user));
	}

	@PostMapping("/profile")
	public ResponseEntity<ApiResponse<UserResponse>> getProfile(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		log.info("POST get user profile");
		UserResponse profile = userService.getProfile(authorization);
		return ResponseEntity.ok(ApiResponse.success("Profile retrieved successfully", profile));
	}
}

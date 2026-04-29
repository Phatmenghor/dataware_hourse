package cpb.dwh_bi_api.features.user.controller;

import cpb.dwh_bi_api.features.user.dto.request.CreateUserRequest;
import cpb.dwh_bi_api.features.user.dto.request.UpdateUserRequest;
import cpb.dwh_bi_api.features.user.dto.response.UserResponse;
import cpb.dwh_bi_api.features.user.service.UserService;
import cpb.dwh_bi_api.shared.dto.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

	private final UserService userService;

	/**
	 * Retrieve all users.
	 * @return List of all users
	 */
	@GetMapping
	public ResponseEntity<ApiResponse<List<UserResponse>>> getAll() {
		log.info("GET all users");
		List<UserResponse> users = userService.getAll();
		return ResponseEntity.ok(ApiResponse.success("Users retrieved successfully", users));
	}

	/**
	 * Retrieve a user by ID.
	 * @param id The user ID
	 * @return User response with the specified ID
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable UUID id) {
		log.info("GET user by id: {}", id);
		UserResponse user = userService.getById(id);
		return ResponseEntity.ok(ApiResponse.success(user));
	}

	/**
	 * Retrieve a user by username.
	 * @param username The username to search for
	 * @return User response with the specified username
	 */
	@GetMapping("/username/{username}")
	public ResponseEntity<ApiResponse<UserResponse>> getByUsername(@PathVariable String username) {
		log.info("GET user by username: {}", username);
		UserResponse user = userService.getByUsername(username);
		return ResponseEntity.ok(ApiResponse.success(user));
	}

	/**
	 * Create a new user.
	 * @param request The user creation request
	 * @return Created user response
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<UserResponse>> create(@Valid @RequestBody CreateUserRequest request) {
		log.info("POST create user: {}", request.getUsername());
		UserResponse created = userService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success("User created successfully", created));
	}

	/**
	 * Update an existing user.
	 * @param id The user ID to update
	 * @param request The user update request
	 * @return Updated user response
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<UserResponse>> update(@PathVariable UUID id,
			@Valid @RequestBody UpdateUserRequest request) {
		log.info("PUT update user: {}", id);
		UserResponse updated = userService.update(id, request);
		return ResponseEntity.ok(ApiResponse.success("User updated successfully", updated));
	}

	/**
	 * Delete a user.
	 * @param id The user ID to delete
	 * @return Success response
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
		log.info("DELETE user: {}", id);
		userService.delete(id);
		return ResponseEntity.ok(ApiResponse.success("User deleted successfully", null));
	}

	/**
	 * Reset a user's password.
	 * @param id The user ID
	 * @param newPassword The new password
	 * @return Updated user response
	 */
	@PostMapping("/{id}/reset-password")
	public ResponseEntity<ApiResponse<UserResponse>> resetPassword(@PathVariable UUID id,
			@RequestParam String newPassword) {
		log.info("POST reset password for user: {}", id);
		UserResponse user = userService.resetPassword(id, newPassword);
		return ResponseEntity.ok(ApiResponse.success("Password reset successfully", user));
	}

	/**
	 * Retrieve the authenticated user's profile.
	 * @param authorization The JWT token from the Authorization header
	 * @return User profile response
	 */
	@PostMapping("/profile")
	public ResponseEntity<ApiResponse<UserResponse>> getProfile(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		log.info("POST get user profile");
		UserResponse profile = userService.getProfile(authorization);
		return ResponseEntity.ok(ApiResponse.success("Profile retrieved successfully", profile));
	}
}

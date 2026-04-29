package cpb.dwh_bi_api.features.role.controller;

import cpb.dwh_bi_api.features.role.dto.request.CreateRoleRequest;
import cpb.dwh_bi_api.features.role.dto.request.UpdateRoleRequest;
import cpb.dwh_bi_api.features.role.dto.response.RoleResponse;
import cpb.dwh_bi_api.features.role.service.RoleService;
import cpb.dwh_bi_api.shared.dto.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Role Management", description = "APIs for managing roles")
public class RoleController {

	private final RoleService roleService;

	/**
	 * Retrieve all roles.
	 * @return List of all roles
	 */
	@GetMapping
	public ResponseEntity<ApiResponse<List<RoleResponse>>> getAll() {
		log.info("GET all roles");
		List<RoleResponse> roles = roleService.getAll();
		return ResponseEntity.ok(ApiResponse.success("Roles retrieved successfully", roles));
	}

	/**
	 * Retrieve a role by ID.
	 * @param id The role ID
	 * @return Role response with the specified ID
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<RoleResponse>> getById(@PathVariable UUID id) {
		log.info("GET role by id: {}", id);
		RoleResponse role = roleService.getById(id);
		return ResponseEntity.ok(ApiResponse.success(role));
	}

	/**
	 * Create a new role.
	 * @param request The role creation request
	 * @return Created role response
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<RoleResponse>> create(@Valid @RequestBody CreateRoleRequest request) {
		log.info("POST create role: {}", request.getName());
		RoleResponse created = roleService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success("Role created successfully", created));
	}

	/**
	 * Update an existing role.
	 * @param id The role ID to update
	 * @param request The role update request
	 * @return Updated role response
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<RoleResponse>> update(@PathVariable UUID id,
			@Valid @RequestBody UpdateRoleRequest request) {
		log.info("PUT update role: {}", id);
		RoleResponse updated = roleService.update(id, request);
		return ResponseEntity.ok(ApiResponse.success("Role updated successfully", updated));
	}

	/**
	 * Delete a role.
	 * @param id The role ID to delete
	 * @return Success response
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
		log.info("DELETE role: {}", id);
		roleService.delete(id);
		return ResponseEntity.ok(ApiResponse.success("Role deleted successfully", null));
	}
}

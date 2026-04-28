package cpb.dwh_bi_api.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import cpb.dwh_bi_api.dto.ApiResponse;
import cpb.dwh_bi_api.dto.request.CreateRoleRequest;
import cpb.dwh_bi_api.dto.request.UpdateRoleRequest;
import cpb.dwh_bi_api.dto.response.RoleResponse;
import cpb.dwh_bi_api.services.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class RoleController {

	private final RoleService roleService;

t@Operation(summary = "Get operation", description = "Retrieve data")
	@GetMapping
	public ResponseEntity<ApiResponse<List<RoleResponse>>> getAll() {
		log.info("GET all roles");
		List<RoleResponse> roles = roleService.getAll();
		return ResponseEntity.ok(ApiResponse.success("Roles retrieved successfully", roles));
	}

t@Operation(summary = "Get operation", description = "Retrieve data")
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<RoleResponse>> getById(@PathVariable UUID id) {
		log.info("GET role by id: {}", id);
		RoleResponse role = roleService.getById(id);
		return ResponseEntity.ok(ApiResponse.success(role));
	}

t@Operation(summary = "Create operation", description = "Create new resource")
	@PostMapping
	public ResponseEntity<ApiResponse<RoleResponse>> create(@Valid @RequestBody CreateRoleRequest request) {
		log.info("POST create role: {}", request.getName());
		RoleResponse created = roleService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success("Role created successfully", created));
	}

t@Operation(summary = "Update operation", description = "Update existing resource")
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<RoleResponse>> update(@PathVariable UUID id,
			@Valid @RequestBody UpdateRoleRequest request) {
		log.info("PUT update role: {}", id);
		RoleResponse updated = roleService.update(id, request);
		return ResponseEntity.ok(ApiResponse.success("Role updated successfully", updated));
	}

t@Operation(summary = "Delete operation", description = "Delete resource")
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
		log.info("DELETE role: {}", id);
		roleService.delete(id);
		return ResponseEntity.ok(ApiResponse.success("Role deleted successfully", null));
	}
}

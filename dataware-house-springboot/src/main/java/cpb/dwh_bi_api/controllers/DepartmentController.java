package cpb.dwh_bi_api.controllers;

import cpb.dwh_bi_api.dto.ApiResponse;
import cpb.dwh_bi_api.dto.request.CreateDepartmentRequest;
import cpb.dwh_bi_api.dto.request.UpdateDepartmentRequest;
import cpb.dwh_bi_api.dto.response.DepartmentResponse;
import cpb.dwh_bi_api.services.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
@Tag(name = "Department Management", description = "API for managing departments")
@SecurityRequirement(name = "Bearer")
public class DepartmentController {

	private final DepartmentService departmentService;

	@GetMapping
	@Operation(summary = "Get all departments", description = "Retrieve all departments from the system")
	public ResponseEntity<ApiResponse<List<DepartmentResponse>>> getAll() {
		log.info("GET all departments");
		List<DepartmentResponse> departments = departmentService.getAll();
		return ResponseEntity.ok(ApiResponse.success("Departments retrieved successfully", departments));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get department by ID", description = "Retrieve a specific department by its ID")
	public ResponseEntity<ApiResponse<DepartmentResponse>> getById(@PathVariable UUID id) {
		log.info("GET department by id: {}", id);
		DepartmentResponse department = departmentService.getById(id);
		return ResponseEntity.ok(ApiResponse.success(department));
	}

	@PostMapping
	@Operation(summary = "Create new department", description = "Create a new department in the system")
	public ResponseEntity<ApiResponse<DepartmentResponse>> create(@Valid @RequestBody CreateDepartmentRequest request) {
		log.info("POST create department: {}", request.getName());
		DepartmentResponse created = departmentService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success("Department created successfully", created));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update department", description = "Update an existing department")
	public ResponseEntity<ApiResponse<DepartmentResponse>> update(@PathVariable UUID id,
			@Valid @RequestBody UpdateDepartmentRequest request) {
		log.info("PUT update department: {}", id);
		DepartmentResponse updated = departmentService.update(id, request);
		return ResponseEntity.ok(ApiResponse.success("Department updated successfully", updated));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete department", description = "Delete a department from the system")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
		log.info("DELETE department: {}", id);
		departmentService.delete(id);
		return ResponseEntity.ok(ApiResponse.success("Department deleted successfully", null));
	}
}

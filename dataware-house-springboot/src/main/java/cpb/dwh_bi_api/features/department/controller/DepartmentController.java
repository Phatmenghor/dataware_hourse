package cpb.dwh_bi_api.features.department.controller;

import cpb.dwh_bi_api.features.department.dto.request.CreateDepartmentRequest;
import cpb.dwh_bi_api.features.department.dto.request.UpdateDepartmentRequest;
import cpb.dwh_bi_api.features.department.dto.response.DepartmentResponse;
import cpb.dwh_bi_api.features.department.service.DepartmentService;
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
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Department Management", description = "APIs for managing departments")
public class DepartmentController {

	private final DepartmentService departmentService;

	/**
	 * Retrieve all departments.
	 * @return List of all departments
	 */
	@GetMapping
	public ResponseEntity<ApiResponse<List<DepartmentResponse>>> getAll() {
		log.info("GET all departments");
		List<DepartmentResponse> departments = departmentService.getAll();
		return ResponseEntity.ok(ApiResponse.success("Departments retrieved successfully", departments));
	}

	/**
	 * Retrieve a department by ID.
	 * @param id The department ID
	 * @return Department response with the specified ID
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<DepartmentResponse>> getById(@PathVariable UUID id) {
		log.info("GET department by id: {}", id);
		DepartmentResponse department = departmentService.getById(id);
		return ResponseEntity.ok(ApiResponse.success(department));
	}

	/**
	 * Create a new department.
	 * @param request The department creation request
	 * @return Created department response
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<DepartmentResponse>> create(@Valid @RequestBody CreateDepartmentRequest request) {
		log.info("POST create department: {}", request.getName());
		DepartmentResponse created = departmentService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success("Department created successfully", created));
	}

	/**
	 * Update an existing department.
	 * @param id The department ID to update
	 * @param request The department update request
	 * @return Updated department response
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<DepartmentResponse>> update(@PathVariable UUID id,
			@Valid @RequestBody UpdateDepartmentRequest request) {
		log.info("PUT update department: {}", id);
		DepartmentResponse updated = departmentService.update(id, request);
		return ResponseEntity.ok(ApiResponse.success("Department updated successfully", updated));
	}

	/**
	 * Delete a department.
	 * @param id The department ID to delete
	 * @return Success response
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
		log.info("DELETE department: {}", id);
		departmentService.delete(id);
		return ResponseEntity.ok(ApiResponse.success("Department deleted successfully", null));
	}
}

package cpb.dwh_bi_api.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import cpb.dwh_bi_api.dto.ApiResponse;
import cpb.dwh_bi_api.dto.request.CreateUnitRequest;
import cpb.dwh_bi_api.dto.response.UnitResponse;
import cpb.dwh_bi_api.services.UnitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/units")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class UnitController {

	private final UnitService unitService;

t@Operation(summary = "Get operation", description = "Retrieve data")
	@GetMapping
	public ResponseEntity<ApiResponse<List<UnitResponse>>> getAll() {
		log.info("GET all units");
		List<UnitResponse> units = unitService.getAll();
		return ResponseEntity.ok(ApiResponse.success("Units retrieved successfully", units));
	}

t@Operation(summary = "Get operation", description = "Retrieve data")
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<UnitResponse>> getById(@PathVariable UUID id) {
		log.info("GET unit by id: {}", id);
		UnitResponse unit = unitService.getById(id);
		return ResponseEntity.ok(ApiResponse.success(unit));
	}

t@Operation(summary = "Get operation", description = "Retrieve data")
	@GetMapping("/department/{departmentId}")
	public ResponseEntity<ApiResponse<List<UnitResponse>>> getByDepartmentId(@PathVariable UUID departmentId) {
		log.info("GET units by department id: {}", departmentId);
		List<UnitResponse> units = unitService.getByDepartmentId(departmentId);
		return ResponseEntity.ok(ApiResponse.success("Units retrieved successfully", units));
	}

t@Operation(summary = "Create operation", description = "Create new resource")
	@PostMapping
	public ResponseEntity<ApiResponse<UnitResponse>> create(@Valid @RequestBody CreateUnitRequest request) {
		log.info("POST create unit: {}", request.getName());
		UnitResponse created = unitService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success("Unit created successfully", created));
	}

t@Operation(summary = "Update operation", description = "Update existing resource")
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<UnitResponse>> update(@PathVariable UUID id,
			@Valid @RequestBody CreateUnitRequest request) {
		log.info("PUT update unit: {}", id);
		UnitResponse updated = unitService.update(id, request);
		return ResponseEntity.ok(ApiResponse.success("Unit updated successfully", updated));
	}

t@Operation(summary = "Delete operation", description = "Delete resource")
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
		log.info("DELETE unit: {}", id);
		unitService.delete(id);
		return ResponseEntity.ok(ApiResponse.success("Unit deleted successfully", null));
	}
}

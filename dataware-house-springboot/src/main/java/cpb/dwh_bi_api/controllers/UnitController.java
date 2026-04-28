package cpb.dwh_bi_api.controllers;

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

	@GetMapping
	public ResponseEntity<ApiResponse<List<UnitResponse>>> getAll() {
		log.info("GET all units");
		List<UnitResponse> units = unitService.getAll();
		return ResponseEntity.ok(ApiResponse.success("Units retrieved successfully", units));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<UnitResponse>> getById(@PathVariable UUID id) {
		log.info("GET unit by id: {}", id);
		UnitResponse unit = unitService.getById(id);
		return ResponseEntity.ok(ApiResponse.success(unit));
	}

	@GetMapping("/department/{departmentId}")
	public ResponseEntity<ApiResponse<List<UnitResponse>>> getByDepartmentId(@PathVariable UUID departmentId) {
		log.info("GET units by department id: {}", departmentId);
		List<UnitResponse> units = unitService.getByDepartmentId(departmentId);
		return ResponseEntity.ok(ApiResponse.success("Units retrieved successfully", units));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<UnitResponse>> create(@Valid @RequestBody CreateUnitRequest request) {
		log.info("POST create unit: {}", request.getName());
		UnitResponse created = unitService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success("Unit created successfully", created));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<UnitResponse>> update(@PathVariable UUID id,
			@Valid @RequestBody CreateUnitRequest request) {
		log.info("PUT update unit: {}", id);
		UnitResponse updated = unitService.update(id, request);
		return ResponseEntity.ok(ApiResponse.success("Unit updated successfully", updated));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
		log.info("DELETE unit: {}", id);
		unitService.delete(id);
		return ResponseEntity.ok(ApiResponse.success("Unit deleted successfully", null));
	}
}

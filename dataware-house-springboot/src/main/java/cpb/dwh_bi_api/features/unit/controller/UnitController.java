package cpb.dwh_bi_api.features.unit.controller;

import cpb.dwh_bi_api.features.unit.dto.request.CreateUnitRequest;
import cpb.dwh_bi_api.features.unit.dto.request.UpdateUnitRequest;
import cpb.dwh_bi_api.features.unit.dto.response.UnitResponse;
import cpb.dwh_bi_api.features.unit.service.UnitService;
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
@RequestMapping("/api/v1/units")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Unit Management", description = "APIs for managing units")
public class UnitController {

	private final UnitService unitService;

	/**
	 * Retrieve all units.
	 * @return List of all units
	 */
	@GetMapping
	public ResponseEntity<ApiResponse<List<UnitResponse>>> getAll() {
		log.info("GET all units");
		List<UnitResponse> units = unitService.getAll();
		return ResponseEntity.ok(ApiResponse.success("Units retrieved successfully", units));
	}

	/**
	 * Retrieve a unit by ID.
	 * @param id The unit ID
	 * @return Unit response with the specified ID
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<UnitResponse>> getById(@PathVariable UUID id) {
		log.info("GET unit by id: {}", id);
		UnitResponse unit = unitService.getById(id);
		return ResponseEntity.ok(ApiResponse.success(unit));
	}

	/**
	 * Create a new unit.
	 * @param request The unit creation request
	 * @return Created unit response
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<UnitResponse>> create(@Valid @RequestBody CreateUnitRequest request) {
		log.info("POST create unit: {}", request.getName());
		UnitResponse created = unitService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success("Unit created successfully", created));
	}

	/**
	 * Update an existing unit.
	 * @param id The unit ID to update
	 * @param request The unit update request
	 * @return Updated unit response
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<UnitResponse>> update(@PathVariable UUID id,
			@Valid @RequestBody UpdateUnitRequest request) {
		log.info("PUT update unit: {}", id);
		UnitResponse updated = unitService.update(id, request);
		return ResponseEntity.ok(ApiResponse.success("Unit updated successfully", updated));
	}

	/**
	 * Delete a unit.
	 * @param id The unit ID to delete
	 * @return Success response
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
		log.info("DELETE unit: {}", id);
		unitService.delete(id);
		return ResponseEntity.ok(ApiResponse.success("Unit deleted successfully", null));
	}
}

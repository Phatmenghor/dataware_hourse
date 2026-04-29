package cpb.dwh_bi_api.features.position.controller;

import cpb.dwh_bi_api.features.position.dto.request.CreatePositionRequest;
import cpb.dwh_bi_api.features.position.dto.request.UpdatePositionRequest;
import cpb.dwh_bi_api.features.position.dto.response.PositionResponse;
import cpb.dwh_bi_api.features.position.service.PositionService;
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
@RequestMapping("/api/v1/positions")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Position Management", description = "APIs for managing positions")
public class PositionController {

	private final PositionService positionService;

	/**
	 * Retrieve all positions.
	 * @return List of all positions
	 */
	@GetMapping
	public ResponseEntity<ApiResponse<List<PositionResponse>>> getAll() {
		log.info("GET all positions");
		List<PositionResponse> positions = positionService.getAll();
		return ResponseEntity.ok(ApiResponse.success("Positions retrieved successfully", positions));
	}

	/**
	 * Retrieve a position by ID.
	 * @param id The position ID
	 * @return Position response with the specified ID
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<PositionResponse>> getById(@PathVariable UUID id) {
		log.info("GET position by id: {}", id);
		PositionResponse position = positionService.getById(id);
		return ResponseEntity.ok(ApiResponse.success(position));
	}

	/**
	 * Create a new position.
	 * @param request The position creation request
	 * @return Created position response
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<PositionResponse>> create(@Valid @RequestBody CreatePositionRequest request) {
		log.info("POST create position: {}", request.getName());
		PositionResponse created = positionService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success("Position created successfully", created));
	}

	/**
	 * Update an existing position.
	 * @param id The position ID to update
	 * @param request The position update request
	 * @return Updated position response
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<PositionResponse>> update(@PathVariable UUID id,
			@Valid @RequestBody UpdatePositionRequest request) {
		log.info("PUT update position: {}", id);
		PositionResponse updated = positionService.update(id, request);
		return ResponseEntity.ok(ApiResponse.success("Position updated successfully", updated));
	}

	/**
	 * Delete a position.
	 * @param id The position ID to delete
	 * @return Success response
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
		log.info("DELETE position: {}", id);
		positionService.delete(id);
		return ResponseEntity.ok(ApiResponse.success("Position deleted successfully", null));
	}
}

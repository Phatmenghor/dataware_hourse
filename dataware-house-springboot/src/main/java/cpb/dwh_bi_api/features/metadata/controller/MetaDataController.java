package cpb.dwh_bi_api.features.metadata.controller;

import cpb.dwh_bi_api.features.metadata.dto.request.CreateMetaDataRequest;
import cpb.dwh_bi_api.features.metadata.dto.request.UpdateMetaDataRequest;
import cpb.dwh_bi_api.features.metadata.dto.response.MetaDataResponse;
import cpb.dwh_bi_api.features.metadata.service.MetaDataService;
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
@RequestMapping("/api/v1/metadata")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Metadata Management", description = "APIs for managing metadata")
public class MetaDataController {

	private final MetaDataService metaDataService;

	/**
	 * Retrieve all metadata.
	 * @return List of all metadata
	 */
	@GetMapping
	public ResponseEntity<ApiResponse<List<MetaDataResponse>>> getAll() {
		log.info("GET all metadata");
		List<MetaDataResponse> metaDataList = metaDataService.getAll();
		return ResponseEntity.ok(ApiResponse.success("Metadata retrieved successfully", metaDataList));
	}

	/**
	 * Retrieve metadata by ID.
	 * @param id The metadata ID
	 * @return Metadata response with the specified ID
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<MetaDataResponse>> getById(@PathVariable UUID id) {
		log.info("GET metadata by id: {}", id);
		MetaDataResponse metaData = metaDataService.getById(id);
		return ResponseEntity.ok(ApiResponse.success(metaData));
	}

	/**
	 * Create new metadata.
	 * @param request The metadata creation request
	 * @return Created metadata response
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<MetaDataResponse>> create(@Valid @RequestBody CreateMetaDataRequest request) {
		log.info("POST create metadata: {}", request.getType());
		MetaDataResponse created = metaDataService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success("Metadata created successfully", created));
	}

	/**
	 * Update existing metadata.
	 * @param id The metadata ID to update
	 * @param request The metadata update request
	 * @return Updated metadata response
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<MetaDataResponse>> update(@PathVariable UUID id,
			@Valid @RequestBody UpdateMetaDataRequest request) {
		log.info("PUT update metadata: {}", id);
		MetaDataResponse updated = metaDataService.update(id, request);
		return ResponseEntity.ok(ApiResponse.success("Metadata updated successfully", updated));
	}

	/**
	 * Delete metadata.
	 * @param id The metadata ID to delete
	 * @return Success response
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
		log.info("DELETE metadata: {}", id);
		metaDataService.delete(id);
		return ResponseEntity.ok(ApiResponse.success("Metadata deleted successfully", null));
	}
}

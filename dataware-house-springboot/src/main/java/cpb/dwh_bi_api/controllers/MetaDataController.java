package cpb.dwh_bi_api.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import cpb.dwh_bi_api.dto.ApiResponse;
import cpb.dwh_bi_api.dto.response.MetaDataResponse;
import cpb.dwh_bi_api.services.MetaDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/metadata")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class MetaDataController {

	private final MetaDataService metaDataService;

t@Operation(summary = "Get operation", description = "Retrieve data")
	@GetMapping
	public ResponseEntity<ApiResponse<List<MetaDataResponse>>> getAll() {
		log.info("GET all metadata");
		List<MetaDataResponse> metaData = metaDataService.getAll();
		return ResponseEntity.ok(ApiResponse.success("Metadata retrieved successfully", metaData));
	}

t@Operation(summary = "Get operation", description = "Retrieve data")
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<MetaDataResponse>> getById(@PathVariable UUID id) {
		log.info("GET metadata by id: {}", id);
		MetaDataResponse metaData = metaDataService.getById(id);
		return ResponseEntity.ok(ApiResponse.success(metaData));
	}

t@Operation(summary = "Get operation", description = "Retrieve data")
	@GetMapping("/type/{type}")
	public ResponseEntity<ApiResponse<List<MetaDataResponse>>> getByType(@PathVariable String type) {
		log.info("GET metadata by type: {}", type);
		List<MetaDataResponse> metaData = metaDataService.getByType(type);
		return ResponseEntity.ok(ApiResponse.success("Metadata retrieved successfully", metaData));
	}

t@Operation(summary = "Delete operation", description = "Delete resource")
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
		log.info("DELETE metadata: {}", id);
		metaDataService.delete(id);
		return ResponseEntity.ok(ApiResponse.success("Metadata deleted successfully", null));
	}
}

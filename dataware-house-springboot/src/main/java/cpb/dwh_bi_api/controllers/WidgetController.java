package cpb.dwh_bi_api.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import cpb.dwh_bi_api.dto.ApiResponse;
import cpb.dwh_bi_api.dto.response.WidgetResponse;
import cpb.dwh_bi_api.services.WidgetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/widgets")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class WidgetController {

	private final WidgetService widgetService;

@Operation(summary = "Get operation", description = "Retrieve data")
	@GetMapping
	public ResponseEntity<ApiResponse<List<WidgetResponse>>> getAll() {
		log.info("GET all widgets");
		List<WidgetResponse> widgets = widgetService.getAll();
		return ResponseEntity.ok(ApiResponse.success("Widgets retrieved successfully", widgets));
	}

@Operation(summary = "Get operation", description = "Retrieve data")
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<WidgetResponse>> getById(@PathVariable UUID id) {
		log.info("GET widget by id: {}", id);
		WidgetResponse widget = widgetService.getById(id);
		return ResponseEntity.ok(ApiResponse.success(widget));
	}

@Operation(summary = "Delete operation", description = "Delete resource")
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
		log.info("DELETE widget: {}", id);
		widgetService.delete(id);
		return ResponseEntity.ok(ApiResponse.success("Widget deleted successfully", null));
	}
}

package cpb.dwh_bi_api.controllers;

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

	@GetMapping
	public ResponseEntity<ApiResponse<List<WidgetResponse>>> getAll() {
		log.info("GET all widgets");
		List<WidgetResponse> widgets = widgetService.getAll();
		return ResponseEntity.ok(ApiResponse.success("Widgets retrieved successfully", widgets));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<WidgetResponse>> getById(@PathVariable UUID id) {
		log.info("GET widget by id: {}", id);
		WidgetResponse widget = widgetService.getById(id);
		return ResponseEntity.ok(ApiResponse.success(widget));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
		log.info("DELETE widget: {}", id);
		widgetService.delete(id);
		return ResponseEntity.ok(ApiResponse.success("Widget deleted successfully", null));
	}
}

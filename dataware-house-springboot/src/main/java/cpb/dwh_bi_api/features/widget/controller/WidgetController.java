package cpb.dwh_bi_api.features.widget.controller;

import cpb.dwh_bi_api.features.widget.dto.request.CreateWidgetRequest;
import cpb.dwh_bi_api.features.widget.dto.request.UpdateWidgetRequest;
import cpb.dwh_bi_api.features.widget.dto.response.WidgetResponse;
import cpb.dwh_bi_api.features.widget.service.WidgetService;
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
@RequestMapping("/api/v1/widgets")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Widget Management", description = "APIs for managing widgets")
public class WidgetController {

	private final WidgetService widgetService;

	/**
	 * Retrieve all widgets.
	 * @return List of all widgets
	 */
	@GetMapping
	public ResponseEntity<ApiResponse<List<WidgetResponse>>> getAll() {
		log.info("GET all widgets");
		List<WidgetResponse> widgets = widgetService.getAll();
		return ResponseEntity.ok(ApiResponse.success("Widgets retrieved successfully", widgets));
	}

	/**
	 * Retrieve a widget by ID.
	 * @param id The widget ID
	 * @return Widget response with the specified ID
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<WidgetResponse>> getById(@PathVariable UUID id) {
		log.info("GET widget by id: {}", id);
		WidgetResponse widget = widgetService.getById(id);
		return ResponseEntity.ok(ApiResponse.success(widget));
	}

	/**
	 * Create a new widget.
	 * @param request The widget creation request
	 * @return Created widget response
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<WidgetResponse>> create(@Valid @RequestBody CreateWidgetRequest request) {
		log.info("POST create widget: {}", request.getName());
		WidgetResponse created = widgetService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success("Widget created successfully", created));
	}

	/**
	 * Update an existing widget.
	 * @param id The widget ID to update
	 * @param request The widget update request
	 * @return Updated widget response
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<WidgetResponse>> update(@PathVariable UUID id,
			@Valid @RequestBody UpdateWidgetRequest request) {
		log.info("PUT update widget: {}", id);
		WidgetResponse updated = widgetService.update(id, request);
		return ResponseEntity.ok(ApiResponse.success("Widget updated successfully", updated));
	}

	/**
	 * Delete a widget.
	 * @param id The widget ID to delete
	 * @return Success response
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
		log.info("DELETE widget: {}", id);
		widgetService.delete(id);
		return ResponseEntity.ok(ApiResponse.success("Widget deleted successfully", null));
	}
}

package cpb.dwh_bi_api.features.report.controller;

import cpb.dwh_bi_api.features.report.dto.request.CreateReportRequest;
import cpb.dwh_bi_api.features.report.dto.request.UpdateReportRequest;
import cpb.dwh_bi_api.features.report.dto.response.ReportResponse;
import cpb.dwh_bi_api.features.report.service.ReportService;
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
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Report Management", description = "APIs for managing reports")
public class ReportController {

	private final ReportService reportService;

	/**
	 * Retrieve all reports.
	 * @return List of all reports
	 */
	@GetMapping
	public ResponseEntity<ApiResponse<List<ReportResponse>>> getAll() {
		log.info("GET all reports");
		List<ReportResponse> reports = reportService.getAll();
		return ResponseEntity.ok(ApiResponse.success("Reports retrieved successfully", reports));
	}

	/**
	 * Retrieve a report by ID.
	 * @param id The report ID
	 * @return Report response with the specified ID
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ReportResponse>> getById(@PathVariable UUID id) {
		log.info("GET report by id: {}", id);
		ReportResponse report = reportService.getById(id);
		return ResponseEntity.ok(ApiResponse.success(report));
	}

	/**
	 * Create a new report.
	 * @param request The report creation request
	 * @return Created report response
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<ReportResponse>> create(@Valid @RequestBody CreateReportRequest request) {
		log.info("POST create report: {}", request.getName());
		ReportResponse created = reportService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success("Report created successfully", created));
	}

	/**
	 * Update an existing report.
	 * @param id The report ID to update
	 * @param request The report update request
	 * @return Updated report response
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<ReportResponse>> update(@PathVariable UUID id,
			@Valid @RequestBody UpdateReportRequest request) {
		log.info("PUT update report: {}", id);
		ReportResponse updated = reportService.update(id, request);
		return ResponseEntity.ok(ApiResponse.success("Report updated successfully", updated));
	}

	/**
	 * Delete a report.
	 * @param id The report ID to delete
	 * @return Success response
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
		log.info("DELETE report: {}", id);
		reportService.delete(id);
		return ResponseEntity.ok(ApiResponse.success("Report deleted successfully", null));
	}
}

package cpb.dwh_bi_api.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import cpb.dwh_bi_api.dto.ApiResponse;
import cpb.dwh_bi_api.dto.response.ReportResponse;
import cpb.dwh_bi_api.services.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ReportController {

	private final ReportService reportService;

@Operation(summary = "Get operation", description = "Retrieve data")
	@GetMapping
	public ResponseEntity<ApiResponse<List<ReportResponse>>> getAll() {
		log.info("GET all reports");
		List<ReportResponse> reports = reportService.getAll();
		return ResponseEntity.ok(ApiResponse.success("Reports retrieved successfully", reports));
	}

@Operation(summary = "Get operation", description = "Retrieve data")
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ReportResponse>> getById(@PathVariable UUID id) {
		log.info("GET report by id: {}", id);
		ReportResponse report = reportService.getById(id);
		return ResponseEntity.ok(ApiResponse.success(report));
	}

@Operation(summary = "Get operation", description = "Retrieve data")
	@GetMapping("/department/{departmentId}")
	public ResponseEntity<ApiResponse<List<ReportResponse>>> getByDepartmentId(@PathVariable UUID departmentId) {
		log.info("GET reports by department id: {}", departmentId);
		List<ReportResponse> reports = reportService.getByDepartmentId(departmentId);
		return ResponseEntity.ok(ApiResponse.success("Reports retrieved successfully", reports));
	}

@Operation(summary = "Get operation", description = "Retrieve data")
	@GetMapping("/unit/{unitId}")
	public ResponseEntity<ApiResponse<List<ReportResponse>>> getByUnitId(@PathVariable UUID unitId) {
		log.info("GET reports by unit id: {}", unitId);
		List<ReportResponse> reports = reportService.getByUnitId(unitId);
		return ResponseEntity.ok(ApiResponse.success("Reports retrieved successfully", reports));
	}

@Operation(summary = "Delete operation", description = "Delete resource")
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
		log.info("DELETE report: {}", id);
		reportService.delete(id);
		return ResponseEntity.ok(ApiResponse.success("Report deleted successfully", null));
	}
}

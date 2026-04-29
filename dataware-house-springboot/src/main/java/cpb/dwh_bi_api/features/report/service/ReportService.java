package cpb.dwh_bi_api.features.report.service;

import cpb.dwh_bi_api.features.report.dto.request.CreateReportRequest;
import cpb.dwh_bi_api.features.report.dto.request.UpdateReportRequest;
import cpb.dwh_bi_api.features.report.dto.response.ReportResponse;

import java.util.List;
import java.util.UUID;

public interface ReportService {
	List<ReportResponse> getAll();
	ReportResponse getById(UUID id);
	ReportResponse create(CreateReportRequest request);
	ReportResponse update(UUID id, UpdateReportRequest request);
	void delete(UUID id);
}

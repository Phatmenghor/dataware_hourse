package cpb.dwh_bi_api.services;

import cpb.dwh_bi_api.dto.response.ReportResponse;
import java.util.List;
import java.util.UUID;

public interface ReportService {
	List<ReportResponse> getAll();
	List<ReportResponse> getByDepartmentId(UUID departmentId);
	List<ReportResponse> getByUnitId(UUID unitId);
	ReportResponse getById(UUID id);
	void delete(UUID id);
}

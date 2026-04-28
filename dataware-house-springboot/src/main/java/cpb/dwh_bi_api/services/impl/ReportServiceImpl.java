package cpb.dwh_bi_api.services.impl;
import cpb.dwh_bi_api.exceptions.ResourceNotFoundException;

import cpb.dwh_bi_api.dto.response.ReportResponse;
import cpb.dwh_bi_api.entities.Report;
import cpb.dwh_bi_api.mappers.ReportMapper;
import cpb.dwh_bi_api.repositories.ReportRepository;
import cpb.dwh_bi_api.services.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ReportServiceImpl implements ReportService {

	private final ReportRepository reportRepository;
	private final ReportMapper reportMapper;

	@Override
	@Transactional(readOnly = true)
	public List<ReportResponse> getAll() {
		log.info("Fetching all reports");
		return reportRepository.findAll()
			.stream()
			.map(reportMapper::toResponse)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReportResponse> getByDepartmentId(UUID departmentId) {
		log.info("Fetching reports by department id: {}", departmentId);
		return reportRepository.findByDepartmentId(departmentId)
			.stream()
			.map(reportMapper::toResponse)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReportResponse> getByUnitId(UUID unitId) {
		log.info("Fetching reports by unit id: {}", unitId);
		return reportRepository.findByUnitId(unitId)
			.stream()
			.map(reportMapper::toResponse)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public ReportResponse getById(UUID id) {
		log.info("Fetching report by id: {}", id);
		Report report = reportRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Report not found with id: " + id));
		return reportMapper.toResponse(report);
	}

	@Override
	public void delete(UUID id) {
		log.info("Deleting report with id: {}", id);
		if (!reportRepository.existsById(id)) {
			throw new cpb.dwh_bi_api.exceptions.ResourceNotFoundException("Report not found with id: " + id);
		}
		reportRepository.deleteById(id);
	}
}

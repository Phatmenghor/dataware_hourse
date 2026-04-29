package cpb.dwh_bi_api.features.report.service.impl;

import cpb.dwh_bi_api.features.report.dto.request.CreateReportRequest;
import cpb.dwh_bi_api.features.report.dto.request.UpdateReportRequest;
import cpb.dwh_bi_api.features.report.dto.response.ReportResponse;
import cpb.dwh_bi_api.features.report.mapper.ReportMapper;
import cpb.dwh_bi_api.features.report.models.Report;
import cpb.dwh_bi_api.features.report.repository.ReportRepository;
import cpb.dwh_bi_api.features.report.service.ReportService;
import cpb.dwh_bi_api.shared.exception.ResourceNotFoundException;
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
	public ReportResponse getById(UUID id) {
		log.info("Fetching report by id: {}", id);
		Report report = reportRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Report not found with id: " + id));
		return reportMapper.toResponse(report);
	}

	@Override
	public ReportResponse create(CreateReportRequest request) {
		log.info("Creating new report: {}", request.getName());
		Report report = reportMapper.toEntity(request);
		Report saved = reportRepository.save(report);
		return reportMapper.toResponse(saved);
	}

	@Override
	public ReportResponse update(UUID id, UpdateReportRequest request) {
		log.info("Updating report with id: {}", id);
		Report report = reportRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Report not found with id: " + id));
		reportMapper.updateEntity(request, report);
		Report updated = reportRepository.save(report);
		return reportMapper.toResponse(updated);
	}

	@Override
	public void delete(UUID id) {
		log.info("Deleting report with id: {}", id);
		if (!reportRepository.existsById(id)) {
			throw new ResourceNotFoundException("Report not found with id: " + id);
		}
		reportRepository.deleteById(id);
	}
}

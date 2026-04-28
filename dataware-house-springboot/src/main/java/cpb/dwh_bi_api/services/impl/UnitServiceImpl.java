package cpb.dwh_bi_api.services.impl;
import cpb.dwh_bi_api.exceptions.ResourceNotFoundException;

import cpb.dwh_bi_api.dto.request.CreateUnitRequest;
import cpb.dwh_bi_api.dto.response.UnitResponse;
import cpb.dwh_bi_api.entities.Unit;
import cpb.dwh_bi_api.mappers.UnitMapper;
import cpb.dwh_bi_api.repositories.UnitRepository;
import cpb.dwh_bi_api.services.UnitService;
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
public class UnitServiceImpl implements UnitService {

	private final UnitRepository unitRepository;
	private final UnitMapper unitMapper;

	@Override
	@Transactional(readOnly = true)
	public List<UnitResponse> getAll() {
		log.info("Fetching all units");
		return unitRepository.findAll()
			.stream()
			.map(unitMapper::toResponse)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<UnitResponse> getByDepartmentId(UUID departmentId) {
		log.info("Fetching units by department id: {}", departmentId);
		return unitRepository.findByDepartmentId(departmentId)
			.stream()
			.map(unitMapper::toResponse)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public UnitResponse getById(UUID id) {
		log.info("Fetching unit by id: {}", id);
		Unit unit = unitRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Unit not found with id: " + id));
		return unitMapper.toResponse(unit);
	}

	@Override
	public UnitResponse create(CreateUnitRequest request) {
		log.info("Creating new unit: {}", request.getName());
		Unit unit = unitMapper.toEntity(request);
		Unit saved = unitRepository.save(unit);
		return unitMapper.toResponse(saved);
	}

	@Override
	public UnitResponse update(UUID id, CreateUnitRequest request) {
		log.info("Updating unit with id: {}", id);
		Unit unit = unitRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Unit not found with id: " + id));
		unit.setName(request.getName());
		unit.setStatus(request.getStatus());
		Unit updated = unitRepository.save(unit);
		return unitMapper.toResponse(updated);
	}

	@Override
	public void delete(UUID id) {
		log.info("Deleting unit with id: {}", id);
		if (!unitRepository.existsById(id)) {
			throw new cpb.dwh_bi_api.exceptions.ResourceNotFoundException("Unit not found with id: " + id);
		}
		unitRepository.deleteById(id);
	}
}

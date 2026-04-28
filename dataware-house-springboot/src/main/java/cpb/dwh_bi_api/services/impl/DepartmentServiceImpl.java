package cpb.dwh_bi_api.services.impl;

import cpb.dwh_bi_api.dto.request.CreateDepartmentRequest;
import cpb.dwh_bi_api.dto.request.UpdateDepartmentRequest;
import cpb.dwh_bi_api.dto.response.DepartmentResponse;
import cpb.dwh_bi_api.entities.Department;
import cpb.dwh_bi_api.mappers.DepartmentMapper;
import cpb.dwh_bi_api.repositories.DepartmentRepository;
import cpb.dwh_bi_api.services.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;
	private final DepartmentMapper departmentMapper;

	@Override
	@Transactional(readOnly = true)
	public List<DepartmentResponse> getAll() {
		log.info("Fetching all departments");
		return departmentRepository.findAll()
			.stream()
			.map(departmentMapper::toResponse)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public DepartmentResponse getById(UUID id) {
		log.info("Fetching department by id: {}", id);
		Department department = departmentRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
		return departmentMapper.toResponse(department);
	}

	@Override
	public DepartmentResponse create(CreateDepartmentRequest request) {
		log.info("Creating new department: {}", request.getName());
		Department department = departmentMapper.toEntity(request);
		Department saved = departmentRepository.save(department);
		return departmentMapper.toResponse(saved);
	}

	@Override
	public DepartmentResponse update(UUID id, UpdateDepartmentRequest request) {
		log.info("Updating department with id: {}", id);
		Department department = departmentRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
		departmentMapper.updateEntity(request, department);
		Department updated = departmentRepository.save(department);
		return departmentMapper.toResponse(updated);
	}

	@Override
	public void delete(UUID id) {
		log.info("Deleting department with id: {}", id);
		if (!departmentRepository.existsById(id)) {
			throw new RuntimeException("Department not found with id: " + id);
		}
		departmentRepository.deleteById(id);
	}
}

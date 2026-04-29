package cpb.dwh_bi_api.features.role.service.impl;

import cpb.dwh_bi_api.features.role.dto.request.CreateRoleRequest;
import cpb.dwh_bi_api.features.role.dto.request.UpdateRoleRequest;
import cpb.dwh_bi_api.features.role.dto.response.RoleResponse;
import cpb.dwh_bi_api.features.role.mapper.RoleMapper;
import cpb.dwh_bi_api.features.role.models.Role;
import cpb.dwh_bi_api.features.role.repository.RoleRepository;
import cpb.dwh_bi_api.features.role.service.RoleService;
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
public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;
	private final RoleMapper roleMapper;

	@Override
	@Transactional(readOnly = true)
	public List<RoleResponse> getAll() {
		log.info("Fetching all roles");
		return roleRepository.findAll()
			.stream()
			.map(roleMapper::toResponse)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public RoleResponse getById(UUID id) {
		log.info("Fetching role by id: {}", id);
		Role role = roleRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));
		return roleMapper.toResponse(role);
	}

	@Override
	public RoleResponse create(CreateRoleRequest request) {
		log.info("Creating new role: {}", request.getName());
		Role role = roleMapper.toEntity(request);
		Role saved = roleRepository.save(role);
		return roleMapper.toResponse(saved);
	}

	@Override
	public RoleResponse update(UUID id, UpdateRoleRequest request) {
		log.info("Updating role with id: {}", id);
		Role role = roleRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));
		roleMapper.updateEntity(request, role);
		Role updated = roleRepository.save(role);
		return roleMapper.toResponse(updated);
	}

	@Override
	public void delete(UUID id) {
		log.info("Deleting role with id: {}", id);
		if (!roleRepository.existsById(id)) {
			throw new ResourceNotFoundException("Role not found with id: " + id);
		}
		roleRepository.deleteById(id);
	}
}

package cpb.dwh_bi_api.features.metadata.service.impl;

import cpb.dwh_bi_api.features.metadata.dto.request.CreateMetaDataRequest;
import cpb.dwh_bi_api.features.metadata.dto.request.UpdateMetaDataRequest;
import cpb.dwh_bi_api.features.metadata.dto.response.MetaDataResponse;
import cpb.dwh_bi_api.features.metadata.mapper.MetaDataMapper;
import cpb.dwh_bi_api.features.metadata.models.MetaData;
import cpb.dwh_bi_api.features.metadata.repository.MetaDataRepository;
import cpb.dwh_bi_api.features.metadata.service.MetaDataService;
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
public class MetaDataServiceImpl implements MetaDataService {

	private final MetaDataRepository metaDataRepository;
	private final MetaDataMapper metaDataMapper;

	@Override
	@Transactional(readOnly = true)
	public List<MetaDataResponse> getAll() {
		log.info("Fetching all metadata");
		return metaDataRepository.findAll()
			.stream()
			.map(metaDataMapper::toResponse)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public MetaDataResponse getById(UUID id) {
		log.info("Fetching metadata by id: {}", id);
		MetaData metaData = metaDataRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("MetaData not found with id: " + id));
		return metaDataMapper.toResponse(metaData);
	}

	@Override
	public MetaDataResponse create(CreateMetaDataRequest request) {
		log.info("Creating new metadata: {}", request.getType());
		MetaData metaData = metaDataMapper.toEntity(request);
		MetaData saved = metaDataRepository.save(metaData);
		return metaDataMapper.toResponse(saved);
	}

	@Override
	public MetaDataResponse update(UUID id, UpdateMetaDataRequest request) {
		log.info("Updating metadata with id: {}", id);
		MetaData metaData = metaDataRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("MetaData not found with id: " + id));
		metaDataMapper.updateEntity(request, metaData);
		MetaData updated = metaDataRepository.save(metaData);
		return metaDataMapper.toResponse(updated);
	}

	@Override
	public void delete(UUID id) {
		log.info("Deleting metadata with id: {}", id);
		if (!metaDataRepository.existsById(id)) {
			throw new ResourceNotFoundException("MetaData not found with id: " + id);
		}
		metaDataRepository.deleteById(id);
	}
}

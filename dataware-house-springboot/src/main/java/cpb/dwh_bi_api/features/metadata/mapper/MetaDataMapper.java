package cpb.dwh_bi_api.features.metadata.mapper;

import cpb.dwh_bi_api.features.metadata.dto.request.CreateMetaDataRequest;
import cpb.dwh_bi_api.features.metadata.dto.request.UpdateMetaDataRequest;
import cpb.dwh_bi_api.features.metadata.dto.response.MetaDataResponse;
import cpb.dwh_bi_api.features.metadata.models.MetaData;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MetaDataMapper {

	MetaDataResponse toResponse(MetaData entity);

	MetaData toEntity(CreateMetaDataRequest request);

	void updateEntity(UpdateMetaDataRequest request, @MappingTarget MetaData entity);
}

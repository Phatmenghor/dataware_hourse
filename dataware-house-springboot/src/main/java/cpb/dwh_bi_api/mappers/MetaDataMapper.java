package cpb.dwh_bi_api.mappers;

import cpb.dwh_bi_api.dto.response.MetaDataResponse;
import cpb.dwh_bi_api.entities.MetaData;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MetaDataMapper {

	MetaDataResponse toResponse(MetaData entity);

	MetaData toEntity(MetaDataResponse response);
}

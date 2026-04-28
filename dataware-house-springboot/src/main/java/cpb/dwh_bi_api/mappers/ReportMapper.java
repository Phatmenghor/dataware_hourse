package cpb.dwh_bi_api.mappers;

import cpb.dwh_bi_api.dto.response.ReportResponse;
import cpb.dwh_bi_api.entities.Report;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReportMapper {

	ReportResponse toResponse(Report entity);

	Report toEntity(ReportResponse response);
}

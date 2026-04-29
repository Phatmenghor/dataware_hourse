package cpb.dwh_bi_api.features.report.mapper;

import cpb.dwh_bi_api.features.report.dto.request.CreateReportRequest;
import cpb.dwh_bi_api.features.report.dto.request.UpdateReportRequest;
import cpb.dwh_bi_api.features.report.dto.response.ReportResponse;
import cpb.dwh_bi_api.features.report.models.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReportMapper {

	@Mapping(source = "unit.id", target = "unitId")
	@Mapping(source = "department.id", target = "departmentId")
	ReportResponse toResponse(Report entity);

	Report toEntity(CreateReportRequest request);

	void updateEntity(UpdateReportRequest request, @MappingTarget Report entity);
}

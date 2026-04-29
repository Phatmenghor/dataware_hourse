package cpb.dwh_bi_api.features.department.mapper;

import cpb.dwh_bi_api.features.department.dto.request.CreateDepartmentRequest;
import cpb.dwh_bi_api.features.department.dto.request.UpdateDepartmentRequest;
import cpb.dwh_bi_api.features.department.dto.response.DepartmentResponse;
import cpb.dwh_bi_api.features.department.models.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DepartmentMapper {

	DepartmentResponse toResponse(Department entity);

	Department toEntity(CreateDepartmentRequest request);

	void updateEntity(UpdateDepartmentRequest request, @MappingTarget Department entity);
}

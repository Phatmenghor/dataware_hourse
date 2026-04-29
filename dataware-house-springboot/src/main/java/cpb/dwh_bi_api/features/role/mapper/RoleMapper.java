package cpb.dwh_bi_api.features.role.mapper;

import cpb.dwh_bi_api.features.role.dto.request.CreateRoleRequest;
import cpb.dwh_bi_api.features.role.dto.request.UpdateRoleRequest;
import cpb.dwh_bi_api.features.role.dto.response.RoleResponse;
import cpb.dwh_bi_api.features.role.models.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper {

	RoleResponse toResponse(Role entity);

	Role toEntity(CreateRoleRequest request);

	void updateEntity(UpdateRoleRequest request, @MappingTarget Role entity);
}

package cpb.dwh_bi_api.features.role.repository;

import cpb.dwh_bi_api.features.role.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
	Optional<Role> findByCode(String code);
	Optional<Role> findByName(String name);
}

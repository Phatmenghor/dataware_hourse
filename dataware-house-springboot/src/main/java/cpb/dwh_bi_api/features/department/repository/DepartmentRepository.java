package cpb.dwh_bi_api.features.department.repository;

import cpb.dwh_bi_api.features.department.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
	Optional<Department> findByCode(String code);
	Optional<Department> findByName(String name);
}

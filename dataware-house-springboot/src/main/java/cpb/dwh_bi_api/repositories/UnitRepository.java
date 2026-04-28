package cpb.dwh_bi_api.repositories;

import cpb.dwh_bi_api.entities.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface UnitRepository extends JpaRepository<Unit, UUID> {
	List<Unit> findByDepartmentId(UUID departmentId);
}

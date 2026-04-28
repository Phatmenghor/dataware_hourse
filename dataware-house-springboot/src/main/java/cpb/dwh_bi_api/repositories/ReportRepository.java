package cpb.dwh_bi_api.repositories;

import cpb.dwh_bi_api.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {
	List<Report> findByDepartmentId(UUID departmentId);
	List<Report> findByUnitId(UUID unitId);
}

package cpb.dwh_bi_api.features.report.repository;

import cpb.dwh_bi_api.features.report.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {
}

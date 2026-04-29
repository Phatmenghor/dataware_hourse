package cpb.dwh_bi_api.features.metadata.repository;

import cpb.dwh_bi_api.features.metadata.models.MetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MetaDataRepository extends JpaRepository<MetaData, UUID> {
}

package cpb.dwh_bi_api.repositories;

import cpb.dwh_bi_api.entities.MetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface MetaDataRepository extends JpaRepository<MetaData, UUID> {
	List<MetaData> findByType(String type);
}

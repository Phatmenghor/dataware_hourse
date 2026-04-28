package cpb.dwh_bi_api.repositories;

import cpb.dwh_bi_api.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PositionRepository extends JpaRepository<Position, UUID> {
	Optional<Position> findByCode(String code);
	Optional<Position> findByName(String name);
}

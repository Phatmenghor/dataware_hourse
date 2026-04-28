package cpb.dwh_bi_api.repositories;

import cpb.dwh_bi_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	Optional<User> findByUsername(String username);
	Optional<User> findByStaffId(String staffId);
}

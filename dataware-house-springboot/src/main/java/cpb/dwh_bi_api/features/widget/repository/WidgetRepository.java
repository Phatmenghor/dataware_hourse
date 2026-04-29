package cpb.dwh_bi_api.features.widget.repository;

import cpb.dwh_bi_api.features.widget.models.Widget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WidgetRepository extends JpaRepository<Widget, UUID> {
}

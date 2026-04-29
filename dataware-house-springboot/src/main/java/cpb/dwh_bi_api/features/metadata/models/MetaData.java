package cpb.dwh_bi_api.features.metadata.models;

import cpb.dwh_bi_api.shared.domain.BaseUUIDEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "meta_datas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetaData extends BaseUUIDEntity {

	@Column(nullable = false)
	private String type;

	@Column(nullable = false)
	private String label;

	@Column(nullable = false)
	private String value;
}

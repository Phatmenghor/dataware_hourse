package cpb.dwh_bi_api.entities;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "meta_datas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetaData {

	@Id
	@Column(columnDefinition = "UUID")
	private UUID id;

	@Column(nullable = false)
	private String type;

	@Column(nullable = false)
	private String label;

	@Column(nullable = false)
	private String value;

	@PrePersist
	public void prePersist() {
		if (this.id == null) {
			this.id = UUID.randomUUID();
		}
	}
}

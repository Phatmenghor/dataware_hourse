package cpb.dwh_bi_api.features.widget.models;

import cpb.dwh_bi_api.shared.domain.BaseUUIDEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "widget_displays")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true, exclude = {"widget"})
@ToString(exclude = {"widget"})
public class WidgetDisplay extends BaseUUIDEntity {

	@Column(nullable = false)
	private String display;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String type;

	@Column(nullable = false)
	private Integer ordering = 0;

	@Column(nullable = false)
	private Boolean status = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "widget_id", nullable = false)
	private Widget widget;
}

package cpb.dwh_bi_api.features.widget.models;

import cpb.dwh_bi_api.shared.domain.BaseUUIDEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "widgets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true, exclude = {"widgetDisplays", "widgetParams", "widgetRoles", "widgetFavorites"})
@ToString(exclude = {"widgetDisplays", "widgetParams", "widgetRoles", "widgetFavorites"})
public class Widget extends BaseUUIDEntity {

	@Column
	private String name;

	@Column
	private String type;

	@Column
	private String code;

	@Column
	private String position;

	@OneToMany(mappedBy = "widget", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<WidgetDisplay> widgetDisplays = new HashSet<>();

	@OneToMany(mappedBy = "widget", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<WidgetParam> widgetParams = new HashSet<>();

	@OneToMany(mappedBy = "widget", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<WidgetRole> widgetRoles = new HashSet<>();

	@OneToMany(mappedBy = "widget", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<WidgetFavorite> widgetFavorites = new HashSet<>();
}

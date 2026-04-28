package cpb.dwh_bi_api.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "widgets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Widget {

	@Id
	@Column(columnDefinition = "UUID")
	private UUID id;

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

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@PrePersist
	public void prePersist() {
		if (this.id == null) {
			this.id = UUID.randomUUID();
		}
	}
}

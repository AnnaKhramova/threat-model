package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "methods")
@Accessors(chain = true)
public class MethodEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "threat_method",
            joinColumns = @JoinColumn(name = "method_id"),
            inverseJoinColumns = @JoinColumn(name = "threat_id"))
    List<ThreatEntity> threats;

    @ManyToMany
    @JoinTable(
            name = "source_method",
            joinColumns = @JoinColumn(name = "method_id"),
            inverseJoinColumns = @JoinColumn(name = "source_id"))
    List<SourceEntity> sources;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "method_id", referencedColumnName = "id")
    private Set<ThreatNodeEntity> nodes;

}
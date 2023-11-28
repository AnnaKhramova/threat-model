package ru.akhramova.createthreatmodel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "sources")
@Accessors(chain = true)
public class SourceEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "threat_source",
            joinColumns = @JoinColumn(name = "source_id"),
            inverseJoinColumns = @JoinColumn(name = "threat_id"))
    List<ThreatEntity> threats;

    @ManyToMany
    @JoinTable(
            name = "source_method",
            joinColumns = @JoinColumn(name = "source_id"),
            inverseJoinColumns = @JoinColumn(name = "method_id"))
    List<MethodEntity> methods;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "source_id", referencedColumnName = "id")
    private Set<ThreatNodeEntity> nodes;

}

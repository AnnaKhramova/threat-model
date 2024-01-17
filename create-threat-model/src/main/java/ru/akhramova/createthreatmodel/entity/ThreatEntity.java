package ru.akhramova.createthreatmodel.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "threats")
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ThreatEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "confidentiality")
    private Boolean confidentiality;

    @Column(name = "integrity")
    private Boolean integrity;

    @Column(name = "accessibility")
    private Boolean accessibility;

    @ManyToMany
    @JoinTable(
            name = "threat_target",
            joinColumns = @JoinColumn(name = "threat_id"),
            inverseJoinColumns = @JoinColumn(name = "target_id"))
    List<TargetEntity> targets;

    @ManyToMany
    @JoinTable(
            name = "threat_source",
            joinColumns = @JoinColumn(name = "threat_id"),
            inverseJoinColumns = @JoinColumn(name = "source_id"))
    List<SourceEntity> sources;

    @ManyToMany
    @JoinTable(
            name = "threat_method",
            joinColumns = @JoinColumn(name = "threat_id"),
            inverseJoinColumns = @JoinColumn(name = "method_id"))
    List<MethodEntity> methods;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "threat_id", referencedColumnName = "id")
    private Set<ThreatNodeEntity> nodes;

}

package ru.akhramova.createthreatmodel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "targets")
@Accessors(chain = true)
public class TargetEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "threat_target",
            joinColumns = @JoinColumn(name = "target_id"),
            inverseJoinColumns = @JoinColumn(name = "threat_id"))
    List<ThreatEntity> threats;

}

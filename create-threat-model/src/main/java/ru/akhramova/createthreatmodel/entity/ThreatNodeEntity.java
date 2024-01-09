package ru.akhramova.createthreatmodel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Table(name = "threat_nodes")
@Accessors(chain = true)
public class ThreatNodeEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private ModelEntity model;

    @Column(name = "node_id")
    private Long nodeId;

    @ManyToOne
    @JoinColumn(name = "threat_id", referencedColumnName = "id")
    private ThreatEntity threat;

    @ManyToOne
    @JoinColumn(name = "source_id", referencedColumnName = "id")
    private SourceEntity source;

    @ManyToOne
    @JoinColumn(name = "method_id", referencedColumnName = "id")
    private MethodEntity method;

    @Column(name = "probability_of_implementation")
    private Double probabilityOfImplementation;

    @Column(name = "danger")
    private Double danger;

    @Column(name = "actuality")
    private Boolean actuality;

}

package ru.akhramova.createthreatmodel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "threat_nodes")
@Accessors(chain = true)
public class ThreatNodeEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "model_id")
    private Long modelId;

    @Column(name = "node_id")
    private Long nodeId;

    @Column(name = "threat_id")
    private Long threatId;

    @Column(name = "source_id")
    private Long sourceId;

    @Column(name = "method_id")
    private Long methodId;

    @Column(name = "probability_of_implementation")
    private Double probabilityOfImplementation;

    @Column(name = "danger")
    private Double danger;

    @Column(name = "actuality")
    private Boolean actuality;

}

package entity;

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

    @Column(name = "node_id")
    private Long nodeId;

    @Column(name = "probability_of_implementation")
    private Long probabilityOfImplementation;

    @Column(name = "danger")
    private Long danger;

    @Column(name = "actuality")
    private Boolean actuality;

}

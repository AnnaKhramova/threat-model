package dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@Accessors(chain = true)
public class ThreatNodeDto {

    private Long id;

    private Long modelId;

    private Long nodeNumber;

    private ThreatDto threat;

    private SourceDto source;

    private String property;

    private MethodDto method;

    private TargetDto target;

    private String probabilityOfImplementation;

    private String danger;
//    private Danger danger;

    private Boolean actuality;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThreatNodeDto dto = (ThreatNodeDto) o;
        return threat.equals(dto.threat) && source.equals(dto.source) && property.equals(dto.property) && method.equals(dto.method) && target.equals(dto.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelId, nodeNumber, threat, source, property, method, target, probabilityOfImplementation, danger, actuality);
    }
}

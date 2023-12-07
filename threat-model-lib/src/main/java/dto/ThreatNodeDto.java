package dto;

import lombok.Data;
import lombok.experimental.Accessors;

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

    private Double probabilityOfImplementation;

    private Double danger;

    private Boolean actuality;

}

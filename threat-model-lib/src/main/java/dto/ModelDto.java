package dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ModelDto {

    private Long id;

    private String name;

    private List<ThreatNodeDto> nodes;

}

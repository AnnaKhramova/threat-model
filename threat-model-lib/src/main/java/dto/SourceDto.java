package dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SourceDto {

    private Long id;

    private String name;

    private String shortName;

}

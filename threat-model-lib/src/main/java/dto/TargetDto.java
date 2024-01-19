package dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TargetDto {

    private Long id;

    private String name;

}

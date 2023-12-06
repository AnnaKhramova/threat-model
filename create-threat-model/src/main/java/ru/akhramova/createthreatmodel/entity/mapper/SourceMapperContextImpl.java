package ru.akhramova.createthreatmodel.entity.mapper;

import dto.SourceDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import ru.akhramova.createthreatmodel.entity.SourceEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SourceMapperContextImpl {

    @AfterMapping
    public void shortName(SourceEntity entity, @MappingTarget SourceDto source) {
        String shortName = "";
        if (entity.getName().startsWith("Внешний")) {
            shortName = "внешний";
        }
        if (entity.getName().startsWith("Внутренний")) {
            shortName = "внутренний";
        }
        source.setShortName(shortName);
    }

}

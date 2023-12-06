package ru.akhramova.createthreatmodel.entity.mapper;

import dto.SourceDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import ru.akhramova.createthreatmodel.entity.SourceEntity;

public interface SourceMapperContext {

    @AfterMapping
    public default void shortName(SourceEntity entity, @MappingTarget SourceDto source) {
    }

}

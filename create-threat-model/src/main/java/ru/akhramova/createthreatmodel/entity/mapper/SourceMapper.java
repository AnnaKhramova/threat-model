package ru.akhramova.createthreatmodel.entity.mapper;

import dto.SourceDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import ru.akhramova.createthreatmodel.entity.SourceEntity;

@Mapper(config = MappingConfig.class)
public interface SourceMapper extends BaseMapper<SourceDto, SourceEntity> {

    SourceDto toDto(SourceEntity entity, @Context SourceMapperContext setShortName);

}

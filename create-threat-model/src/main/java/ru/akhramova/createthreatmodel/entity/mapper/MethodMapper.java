package ru.akhramova.createthreatmodel.entity.mapper;

import dto.MethodDto;
import org.mapstruct.Mapper;
import ru.akhramova.createthreatmodel.entity.MethodEntity;

@Mapper(config = MappingConfig.class)
public interface MethodMapper extends BaseMapper<MethodDto, MethodEntity> {
}

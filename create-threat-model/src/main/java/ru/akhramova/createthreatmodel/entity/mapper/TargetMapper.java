package ru.akhramova.createthreatmodel.entity.mapper;

import dto.TargetDto;
import org.mapstruct.Mapper;
import ru.akhramova.createthreatmodel.entity.TargetEntity;

@Mapper(config = MappingConfig.class)
public interface TargetMapper extends BaseMapper<TargetDto, TargetEntity> {
}

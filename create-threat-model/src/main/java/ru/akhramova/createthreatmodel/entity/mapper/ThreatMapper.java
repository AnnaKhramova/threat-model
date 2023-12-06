package ru.akhramova.createthreatmodel.entity.mapper;

import dto.ThreatDto;
import org.mapstruct.Mapper;
import ru.akhramova.createthreatmodel.entity.ThreatEntity;

@Mapper(config = MappingConfig.class)
public interface ThreatMapper extends BaseMapper<ThreatDto, ThreatEntity> {
}

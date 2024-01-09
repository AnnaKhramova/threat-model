package ru.akhramova.createthreatmodel.entity.mapper;

import org.mapstruct.Mapping;

/**
 * @param <D> DTO class
 * @param <E> Entity class
 */
public interface BaseMapper<D, E> {

    D toDto(E entity);

//    @Mapping(target = "id", ignore = true)
    E toEntity(D dto);

}

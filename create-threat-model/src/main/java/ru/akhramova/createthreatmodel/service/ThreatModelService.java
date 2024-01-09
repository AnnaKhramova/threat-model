package ru.akhramova.createthreatmodel.service;

import dto.ModelDto;
import dto.ThreatNodeDto;
import ru.akhramova.createthreatmodel.entity.ModelEntity;
import ru.akhramova.createthreatmodel.entity.SourceEntity;
import ru.akhramova.createthreatmodel.entity.TargetEntity;

import java.util.List;

public interface ThreatModelService {

    List<ModelEntity> getAllModels();

    List<TargetEntity> getAllTargets();

    List<SourceEntity> getAllSources();

    List<TargetEntity> getTargetsByIds(List<Long> ids);

    List<SourceEntity> getSourcesByIds(List<Long> ids);

    List<ThreatNodeDto> getNodes(List<TargetEntity> targets, List<SourceEntity> sources);

    void saveModel(ModelDto model);

    void editModel(Long id);

    void downloadModel(Long id);

    void deleteModel(Long id);

}

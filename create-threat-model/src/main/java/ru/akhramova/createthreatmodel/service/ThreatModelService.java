package ru.akhramova.createthreatmodel.service;

import lombok.RequiredArgsConstructor;
import ru.akhramova.createthreatmodel.entity.ModelEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.akhramova.createthreatmodel.entity.SourceEntity;
import ru.akhramova.createthreatmodel.entity.TargetEntity;
import ru.akhramova.createthreatmodel.entity.ThreatNodeEntity;
import ru.akhramova.createthreatmodel.entity.mapper.SourceMapper;
import ru.akhramova.createthreatmodel.repository.ModelRepository;
import ru.akhramova.createthreatmodel.repository.SourceRepository;
import ru.akhramova.createthreatmodel.repository.TargetRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ThreatModelService {

    private final ModelRepository modelRepository;
    private final TargetRepository targetRepository;
    private final SourceRepository sourceRepository;
    private final SourceMapper sourceMapper;

    public List<ModelEntity> getAllModels() {
        return modelRepository.findAll();
    }

    public List<TargetEntity> getAllTargets() {
        return targetRepository.findAll();
    }

    public List<SourceEntity> getAllSources() {
        return sourceRepository.findAll();
    }

    public List<TargetEntity> getTargetsByIds(List<Long> ids) {
        return targetRepository.findAllById(ids);
    }

    public List<SourceEntity> getSourcesByIds(List<Long> ids) {
        return sourceRepository.findAllById(ids);
    }

    public List<ThreatNodeEntity> getNodes(List<TargetEntity> targets, List<SourceEntity> sources) {
        List<ThreatNodeEntity> nodes = new ArrayList<>();
        sources.stream().map(sourceMapper::toDto).toList();
        return nodes;
    }

    public void saveModel(ModelEntity model) {
        modelRepository.save(model);
    }

    public void editModel(Long id) {

    }

    public void downloadModel(Long id) {

    }

    public void deleteModel(Long id) {

    }


}

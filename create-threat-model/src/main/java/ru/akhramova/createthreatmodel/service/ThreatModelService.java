package ru.akhramova.createthreatmodel.service;

import dto.ThreatNodeDto;
import lombok.RequiredArgsConstructor;
import ru.akhramova.createthreatmodel.entity.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.akhramova.createthreatmodel.entity.mapper.MethodMapper;
import ru.akhramova.createthreatmodel.entity.mapper.SourceMapper;
import ru.akhramova.createthreatmodel.entity.mapper.SourceMapperContext;
import ru.akhramova.createthreatmodel.entity.mapper.ThreatMapper;
import ru.akhramova.createthreatmodel.repository.ModelRepository;
import ru.akhramova.createthreatmodel.repository.SourceRepository;
import ru.akhramova.createthreatmodel.repository.TargetRepository;
import ru.akhramova.createthreatmodel.repository.ThreatRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ThreatModelService {

    private final ModelRepository modelRepository;
    private final ThreatRepository threatRepository;
    private final TargetRepository targetRepository;
    private final SourceRepository sourceRepository;
    private final ThreatMapper threatMapper;
    private final SourceMapper sourceMapper;
    private final SourceMapperContext sourceMapperContext;
    private final MethodMapper methodMapper;

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

    public List<ThreatNodeDto> getNodes(List<TargetEntity> targets, List<SourceEntity> sources) {
        List<ThreatNodeDto> nodes = new ArrayList<>();
        List<ThreatEntity> threats = threatRepository.findAll();
        Long count = 0L;
        for (SourceEntity source : sources) {
            for (TargetEntity target : targets) {
                for (ThreatEntity threat : threats) {
                    if (threat.getSources().contains(source) && threat.getTargets().contains(target)) {
                        List<String> props = new ArrayList<>();
                        if (threat.getConfidentiality()) props.add("конфиденциальность");
                        if (threat.getAccessibility()) props.add("доступность");
                        if (threat.getIntegrity()) props.add("целостность");
                        for (String prop : props) {
                            ThreatNodeDto node = new ThreatNodeDto();
                            node.setNodeNumber(count++);
                            node.setThreat(threatMapper.toDto(threat));
                            node.setSource(sourceMapper.toDto(source, sourceMapperContext));
                            node.setMethod(methodMapper.toDto(source.getMethods().get(0)));
                            nodes.add(node);
                        }
                    }
                }
            }
        }
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

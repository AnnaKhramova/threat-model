package ru.akhramova.createthreatmodel.service;

import dto.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.akhramova.createthreatmodel.entity.*;
import org.springframework.stereotype.Service;
import ru.akhramova.createthreatmodel.entity.mapper.MethodMapper;
import ru.akhramova.createthreatmodel.entity.mapper.SourceMapper;
import ru.akhramova.createthreatmodel.entity.mapper.SourceMapperContext;
import ru.akhramova.createthreatmodel.entity.mapper.ThreatMapper;
import ru.akhramova.createthreatmodel.repository.ModelRepository;
import ru.akhramova.createthreatmodel.repository.SourceRepository;
import ru.akhramova.createthreatmodel.repository.TargetRepository;
import ru.akhramova.createthreatmodel.repository.ThreatRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ThreatModelServiceImpl implements ThreatModelService {

    @Autowired
    private final ModelRepository modelRepository;

    @Autowired
    private final ThreatRepository threatRepository;

    @Autowired
    private final TargetRepository targetRepository;

    @Autowired
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
                            node.setProperty(prop);
                            if (!threat.getMethods().isEmpty()) {
                                node.setMethod(methodMapper.toDto(threat.getMethods().get(0)));
                            } else {
                                node.setMethod(new MethodDto().setName(""));
                            }
                            node.setProbabilityOfImplementation("0.0");
                            node.setDanger("0.2");
                            nodes.add(node);
                        }
                    }
                }
            }
        }
        nodes.sort(Comparator.comparing(ThreatNodeDto::getThreat, (Comparator.comparing(ThreatDto::getId))));
        return nodes;
    }

    @Transactional
    public void saveModel(ModelDto model) {
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setName(model.getName());
        List<ThreatNodeDto> nodes = model.getNodes();
        Set<ThreatNodeEntity> nodeEntities = new HashSet<>();
        Long count = 1L;
        for (ThreatNodeDto dto : nodes) {
            ThreatNodeEntity entity = new ThreatNodeEntity();
            entity.setModel(modelEntity);
            entity.setNodeId(count++);
            entity.setThreat(threatMapper.toEntity(dto.getThreat()));
            entity.setSource(sourceMapper.toEntity(dto.getSource()));
            entity.setMethod(dto.getMethod() != null ? methodMapper.toEntity(dto.getMethod()) : null);
            entity.setProbabilityOfImplementation(Double.parseDouble(dto.getProbabilityOfImplementation()));
            entity.setDanger(Double.parseDouble(dto.getDanger()));

        }
        modelEntity.setNodes(nodeEntities);
        modelRepository.saveAndFlush(modelEntity);
    }

    public void editModel(Long id) {

    }

    public void downloadModel(Long id) {

    }

    public void deleteModel(Long id) {
        modelRepository.deleteById(id);
    }


}

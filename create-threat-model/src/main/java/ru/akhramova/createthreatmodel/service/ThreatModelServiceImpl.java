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

    public void createModel(ModelDto model) {
        for (ThreatNodeDto node : model.getNodes()) {
            if ((Double.parseDouble(node.getProbabilityOfImplementation()) + Double.parseDouble(node.getDanger())) / 2 > 0.3) {
                node.setActuality(true);
            } else {
                node.setActuality(false);
            }
        }
    }

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
            entity.setActuality(dto.getActuality());
            nodeEntities.add(entity);
        }
        modelEntity.setNodes(nodeEntities);
        modelRepository.saveAndFlush(modelEntity);
    }

    public ModelEntity getModel(Long id) {
        ModelEntity modelEntity = modelRepository.getById(id);
        ModelDto modelDto = new ModelDto();
        modelDto.setName(modelEntity.getName());
        modelDto.setNodes(getNodeDtos(modelEntity));
        return modelEntity;
    }

    private List<ThreatNodeDto> getNodeDtos(ModelEntity modelEntity) {
        List<ThreatNodeEntity> nodeEntities = modelEntity.getNodes().stream()
                .sorted(Comparator.comparing(ThreatNodeEntity::getNodeId)).toList();
        List<ThreatNodeDto> nodes = new ArrayList<>();
        Long count = 1L;
        for (ThreatNodeEntity entity : nodeEntities) {
            ThreatNodeDto dto = new ThreatNodeDto();
            dto.setModelId(modelEntity.getId());
            dto.setNodeNumber(count++);
            dto.setThreat(threatMapper.toDto(entity.getThreat()));
            dto.setSource(sourceMapper.toDto(entity.getSource()));
            dto.setMethod(entity.getMethod() != null ? methodMapper.toDto(entity.getMethod()) : null);
            dto.setProbabilityOfImplementation(entity.getProbabilityOfImplementation().toString());
            dto.setDanger(entity.getDanger().toString());
            dto.setActuality(entity.getActuality());
            nodes.add(dto);
        }
        return nodes;
    }

    public List<SourceEntity> getSources(ModelEntity model) {
        List<ThreatNodeDto> nodes = getNodeDtos(model);
        List<SourceEntity> sources = new ArrayList<>();
        for (ThreatNodeDto node : nodes) {
            Optional<SourceEntity> source = sourceRepository.findById(node.getSource().getId());
            source.ifPresent(sources::add);
        }
        return sources;
    }

    public void editModel(Long id) {

    }

    public void downloadModel(Long id) {

    }

    public void deleteModel(Long id) {
        modelRepository.deleteById(id);
    }


}

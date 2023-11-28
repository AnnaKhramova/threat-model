package ru.akhramova.createthreatmodel.service;

import ru.akhramova.createthreatmodel.entity.ModelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.akhramova.createthreatmodel.repository.ModelRepository;

import java.util.List;

@Service
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ThreatModelService {

    @Autowired
    private ModelRepository modelRepository;

    public List<ModelEntity> getAllModels() {
        return modelRepository.findAll();
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

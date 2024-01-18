package ru.akhramova.createthreatmodel.repository;

import org.springframework.data.jpa.repository.Query;
import ru.akhramova.createthreatmodel.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {

    List<ModelEntity> findAll();

    ModelEntity getById(Long id);

    void deleteById(Long id);

    @Query(value = "SELECT * FROM models " +
            "WHERE id = ( " +
            "   SELECT MAX (id) " +
            "   FROM models " +
            ")", nativeQuery = true)
    ModelEntity getLast();

}

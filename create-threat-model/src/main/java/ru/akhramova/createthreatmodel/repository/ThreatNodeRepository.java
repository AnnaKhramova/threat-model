package ru.akhramova.createthreatmodel.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.akhramova.createthreatmodel.entity.ThreatNodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreatNodeRepository extends JpaRepository<ThreatNodeEntity, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM public.threat_nodes " +
            "WHERE model_id = :id", nativeQuery = true)
    void deleteNodesByModelId(Long id);

}

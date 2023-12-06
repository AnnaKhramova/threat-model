package ru.akhramova.createthreatmodel.repository;

import ru.akhramova.createthreatmodel.entity.ThreatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreatRepository extends JpaRepository<ThreatEntity, Long> {
}

package ru.akhramova.createthreatmodel.repository;

import ru.akhramova.createthreatmodel.entity.SourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<SourceEntity, Long> {
}

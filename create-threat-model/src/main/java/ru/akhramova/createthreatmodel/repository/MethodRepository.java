package ru.akhramova.createthreatmodel.repository;

import ru.akhramova.createthreatmodel.entity.MethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MethodRepository extends JpaRepository<MethodEntity, Long> {
}

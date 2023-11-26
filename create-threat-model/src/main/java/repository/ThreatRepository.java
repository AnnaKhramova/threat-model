package repository;

import entity.ThreatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreatRepository extends JpaRepository<ThreatEntity, Long> {

    List<ThreatEntity> findAll();

}

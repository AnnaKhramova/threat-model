package repository;

import entity.ThreatNodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreatNodeRepository extends JpaRepository<ThreatNodeEntity, Long> {

}

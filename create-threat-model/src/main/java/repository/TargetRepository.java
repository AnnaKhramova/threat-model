package repository;

import entity.TargetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TargetRepository extends JpaRepository<TargetEntity, Long> {

    List<TargetEntity> findAll();

}

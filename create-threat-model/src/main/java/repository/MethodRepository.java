package repository;

import entity.MethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MethodRepository extends JpaRepository<MethodEntity, Long> {

    List<MethodEntity> findAll();

}

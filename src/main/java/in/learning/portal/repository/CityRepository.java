package in.learning.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.learning.portal.entity.CityEntity;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Integer>{
	public List<CityEntity> findByStateId(Integer stateId);
}

package in.learning.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.learning.portal.entity.StateEntity;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Integer>{
public List<StateEntity> findByCountryId(Integer countryId);

}

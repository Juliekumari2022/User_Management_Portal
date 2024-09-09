package in.learning.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.learning.portal.entity.CountryEntity;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Integer>{

}

package in.learning.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.learning.portal.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
public UserEntity findByEmail(String email);
public UserEntity findByEmailAndPassword(String email,String password);
}

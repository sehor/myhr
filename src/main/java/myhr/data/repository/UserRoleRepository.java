package myhr.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import myhr.data.domain.user.Role;
import myhr.data.domain.user.UserRole;

@Repository
@RepositoryRestResource
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{

	List<Role> findByUid(Integer id);

}

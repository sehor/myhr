package myhr.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import myhr.data.domain.user.Role;

@Repository
@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	@Query(value="select * from Role as r ,UserRole as ur  where r.id=ur.rid and ur.uid=:userId"
			,nativeQuery=true)
	List<Role> findRolesByUserId(@Param("userId") Integer userId);

	@Query(value = "select * from MenuRole as mr,Role as r where r.id=mr.rid and mr.mid=:menuId",nativeQuery = true)
    List<Role> findByMid(@Param("menuId") Integer menuId);
}

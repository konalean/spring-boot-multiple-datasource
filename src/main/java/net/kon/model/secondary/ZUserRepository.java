package net.kon.model.secondary;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface ZUserRepository extends CrudRepository<ZUser, Integer> { 
	
	public ZUser findByAdmId(int admId);

}

package net.kon.model.primary;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZTestRepository extends CrudRepository<ZTest, String> {
	public ZTest findByUuid(String uuid);
}

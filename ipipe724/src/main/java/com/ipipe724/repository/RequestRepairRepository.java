package com.ipipe724.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ipipe724.model.RequestRepair;


@Repository("RequestRepairRepository")
public interface RequestRepairRepository extends PagingAndSortingRepository<RequestRepair, Long>{

	Optional<RequestRepair> findById(Long id);
	
	Page<RequestRepair> findAll(Pageable pageable);
	
	@Query(value = "SELECT * FROM testrepair p where userid = ?1", nativeQuery = true)
	Page<RequestRepair> findAllByUser(@Param("userid") int userid, Pageable pageable);
	
	
}

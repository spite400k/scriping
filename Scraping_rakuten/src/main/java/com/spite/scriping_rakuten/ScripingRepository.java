package com.spite.scriping_rakuten;

import org.springframework.data.repository.CrudRepository;

public interface ScripingRepository extends CrudRepository <DumpEntry, Long> {
	
	//Page<Configurations> findAll(Pageable pageable);
	
	Iterable<DumpEntry> findByRef(String ref);
 
}
package com.spite.scriping_yahoo;

import org.springframework.data.repository.CrudRepository;

public interface ScripingRepository extends CrudRepository <DumpEntry, Long> {
	
	//Page<Configurations> findAll(Pageable pageable);
	
	Iterable<DumpEntry> findByItemCode(String itemCode);
 
}
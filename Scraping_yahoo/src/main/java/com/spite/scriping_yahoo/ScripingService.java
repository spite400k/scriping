package com.spite.scriping_yahoo;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ScripingService {
	
	@Autowired
	private ScripingRepository repository;
	
//    public List<entity> get() {
//        return dao.findAll();
//    }

	public Map<String, Iterable<DumpEntry>> insert(List<DumpEntry> payload) {
		Iterable<DumpEntry> result = repository.saveAll(payload);
	    return Collections.singletonMap("payload", result);
	    
		//return (List<EntityWiki>) repository.findAll();
	}
}

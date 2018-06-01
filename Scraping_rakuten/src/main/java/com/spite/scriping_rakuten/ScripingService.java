package com.spite.scriping_rakuten;

import java.util.Collections;
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

	public Map<String, DumpEntry> insert(DumpEntry payload) {
		DumpEntry result = repository.save(payload);
	    return Collections.singletonMap("payload", result);
	    
		//return (List<EntityWiki>) repository.findAll();
	}
}

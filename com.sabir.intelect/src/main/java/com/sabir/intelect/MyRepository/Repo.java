package com.sabir.intelect.MyRepository;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sabir.intelect.entity.UserEntity;

//@Repository
public interface Repo /*extends CrudRepository<UserEntity,Long>*/{
	
	public UserEntity findByEmail(String email);
	
	
}

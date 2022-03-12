package dre.org.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dre.org.entities.Student;

@Repository
public interface StudentRepository extends BetreuerRepository{


	

	
}

package com.esprit.bankPi.chat;








import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.esprit.bankPi.model.Reclamation;
//public interface chatRepository extends PagingAndSortingRepository<Reclamation, Long>{
	//@Query("SELECT r FROM reclamation r WHERE CONCAT(r.id, ' ',r.Name, ' ', r.solution, ' ', r.Date) LIKE %?1%")
	
	//public List<Reclamation> search(String keyword);
	
	
	

public interface chatRepository extends JpaRepository<Reclamation, Long>{
	
//	 @Query("SELECT r FROM reclamation r WHERE r.Name LIKE %?1%"
//	            + " OR r.id LIKE %?1%"
//	            + " OR r.solution LIKE %?1%"
//	            + " OR p.Date LIKE %?1%"
//	           )
	//public List<Reclamation> search(String keyword);
	public List<Reclamation>  findByName(String name);
	


}

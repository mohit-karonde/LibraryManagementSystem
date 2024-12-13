package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Lending;

@Repository
public interface LendingRepository extends JpaRepository<Lending, Long> {
	
	
	// @Query(value = "SELECT l FROM Lending l WHERE l.book.id = :bookId AND l.user.id = :userId ORDER BY l.id DESC")
	 @Query(value = "SELECT l FROM Lending l WHERE l.id = (SELECT MAX(l2.id) FROM Lending l2 WHERE l2.book.id = :bookId AND l2.user.id = :userId)")
	 Optional<Lending> getByBookIdAndUserId(@Param("bookId") Long bookId, @Param("userId") Long userId);

	
	@Query("SELECT l FROM Lending l WHERE l.book.id = :bookId")
    List<Lending> findByBook_Id(Long bookId);
	
	@Query("SELECT l FROM Lending l LEFT JOIN FETCH l.book LEFT JOIN FETCH l.user WHERE l.user.id = :userId AND l.book.id = :bookId AND l.returnDate IS NULL ORDER BY l.id DESC")
	List<Lending> findLatestLendingRecord(@Param("userId") Long userId, @Param("bookId") Long bookId);

}
 





package com.example.demo.serviceImplement;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.entity.Lending;
import com.example.demo.entity.User;
import com.example.demo.payload.ApiResponse;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LendingRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LendingService;

@Service
public class LendingServiceImplement implements LendingService {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@Autowired 
	LendingRepository lendingRepository;
	

	@Override
	public ResponseEntity<ApiResponse<String>> LendBook(Long bookId, Long userId) {
	  
	    Book book = bookRepository.findById(bookId).orElse(null);
	    User user = userRepository.findById(userId).orElse(null);

	   
	    if (book == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new ApiResponse<>("Not Found", "Book not found", "failure", "Book with ID " + bookId + " does not exist"));
	    }

	    if (user == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new ApiResponse<>("Not Found", "User not found", "failure", "User with ID " + userId + " does not exist"));
	    }
         
	    
	    // Check if the book is available
	    if (!book.getIsAvailable()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(new ApiResponse<>("Not Found", "Book is not available", "failure", "Unable to lend book"));
	    }

	    // Create a new Lending record
	    Lending lendBook = new Lending();
	    lendBook.setBook(book);
	    lendBook.setUser(user);
	    lendBook.setLoanDate(LocalDate.now());  // Set loan date to today
	    lendBook.setDueDate(LocalDate.now().plusDays(14)); // Set due date to 14 days from today

	    // Save the lending record
	    lendingRepository.save(lendBook);
	    
	    if(book.getStock()==1) {
	    	
	    	book.setStock(book.getStock()-1);
	    	book.setIsAvailable(false);
	    	bookRepository.save(book);
	    }

	    // Update the book's availability
	    if (book.getStock() > 1) {
	        book.setStock(book.getStock() - 1);
	    //    book.setIsAvailable(book.getStock() > 0); // Update availability
	        bookRepository.save(book);
	        
	        
	    }
	    bookRepository.save(book);

	    // Return success response
	    return ResponseEntity.status(HttpStatus.OK)
	            .body(new ApiResponse<>("Success", "Lending successful", "success", "Book lent successfully"));
	}


	@Override
	public ResponseEntity<ApiResponse<String>> ReturnBook(Long bookId, Long userId) {
		//Optional<Lending> lendedBook = lendingRepository.getByBookIdAndUserId(bookId, userId);
		Book book = bookRepository.findById(bookId).orElse(null);
		//Lending lending = lendedBook.get();
		
		// List<Lending> latestLending = lendingRepository.findLatestLendingRecord(userId, bookId); 
		
		List<Lending> latestLendings = lendingRepository.findLatestLendingRecord(userId, bookId);
		System.out.print(latestLendings);
		
		Lending lending = latestLendings.isEmpty() ? null : latestLendings.get(0);
		 
		System.out.print(lending);
		
		if(lending == null) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Not Found", "Book is already returned", "Book is returned",
					"Book is returened alrady " + bookId));
			
		}
		
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Not Found", "Book not found", "failure",
					"Cannot find the book with ID " + bookId));
		}
		
		
		
//		if (lendedBook.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Not Found", "Lending record not found", "failure",
//					"Cannot find the lending record for book ID " + bookId + " and user ID " + userId));
//		}
		
		// Retrieve Lending record
		
		
		//  return date
		lending.setReturnDate(LocalDate.now());
		
	
		LocalDate dueDate = lending.getDueDate();
		if (LocalDate.now().isAfter(dueDate)) {
			long daysLate = LocalDate.now().toEpochDay() - dueDate.toEpochDay();
			lending.setFineAmount(daysLate * 1.0); 
		}
	    System.out.println("saving lending Record");
		lendingRepository.save(lending);
		
		System.out.println("now making book available");
		
		if(book.getStock()==0) {
			
			book.setIsAvailable(true);
			
		}
		
		book.setStock(book.getStock()+1);
		
		bookRepository.save(book);
		System.out.println("saving available book");
		
		
		return ResponseEntity.ok(new ApiResponse<>("Success", "Book returned successfully", "success", 
				"The book has been returned successfully. Fine amount: " + lending.getFineAmount()));
	      
	
		
	}


	@Override
	public ResponseEntity<ApiResponse<List<Lending>>> getByBook_Id(Long bookId) {
		
		
		return ResponseEntity.ok(new ApiResponse<>("Success", "Book returned successfully", "success", lendingRepository.findByBook_Id(bookId)));
	      
	}
	

}




package br.com.devires.repository;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import br.com.devires.entity.Book;

@Component
public class BookRepository {

	private static final String COLLECTION = "books"; 
	
	@Autowired
	private Firestore firestore;
	
	public WriteResult save(Book book) throws InterruptedException, ExecutionException {
		return firestore.collection(COLLECTION).document(book.getId().toString()).set(book).get();
	}
	
	public Optional<Book> findById(Long id) throws InterruptedException, ExecutionException {
		ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = firestore.collection(COLLECTION).document(id.toString()).get();
		Book book = documentSnapshotApiFuture.get().toObject(Book.class);
		return Optional.of(book);
	}
	
}
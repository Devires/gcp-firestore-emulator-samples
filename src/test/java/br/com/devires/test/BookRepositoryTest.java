package br.com.devires.test;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.cloud.firestore.WriteResult;

import br.com.devires.GcpFirestoreEmulatorApplication;
import br.com.devires.entity.Book;
import br.com.devires.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { GcpFirestoreEmulatorApplication.class })
public class BookRepositoryTest {

	@Autowired
	private BookRepository repository;

	@Test
	public void saveBook() throws InterruptedException, ExecutionException {
		Book book = new Book(1L, "Devires Book", "Gilberto Holms");
		WriteResult result = repository.save(book);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void findBook() throws InterruptedException, ExecutionException {
		Optional<Book> book = repository.findById(1L);
		Assert.assertTrue(book.isPresent());
		log.info(book.get().toString());
	}

}
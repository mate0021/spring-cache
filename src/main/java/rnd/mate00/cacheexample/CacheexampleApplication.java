package rnd.mate00.cacheexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@Slf4j
@EnableCaching
public class CacheexampleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CacheexampleApplication.class, args);
	}

	@Autowired
	private BookSlowRepository repository;

	@Autowired
    private BookCachedRepository cachedRepository;

	@Override
	public void run(String... args) throws Exception {
		log.info("Querying for Margin Call");
		log.info("Got {}", repository.getBookByTitle("Margin Call"));

		log.info("Querying for Toy Story");
		log.info("Got {}", repository.getBookByTitle("Toy Story"));


		log.info("Querying for Margin Call"); // <-- call for the same object, calls a slow service again
		log.info("Got {}", repository.getBookByTitle("Margin Call"));

		// ---

		log.info("Querying and caching Margin Call");
		log.info("Got {}", cachedRepository.getBookByTitle("Margin Call"));

		log.info("Querying and caching for Toy Story");
		log.info("Got cached {}", cachedRepository.getBookByTitle("Toy Story"));

		log.info("Querying and caching Margin Call"); // <-- these ones are taken from cache
		log.info("Got cached {}", cachedRepository.getBookByTitle("Margin Call"));

		log.info("Querying and caching Toy Story");
		log.info("Get cached {}", cachedRepository.getBookByTitle("Toy Story"));

		log.info("Querying complete");
	}
}

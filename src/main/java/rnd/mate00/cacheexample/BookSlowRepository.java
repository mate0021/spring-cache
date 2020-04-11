package rnd.mate00.cacheexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BookSlowRepository implements BookRepository {

    @Value("${repository.book.by.title.query}")
    private String bookByTitleQuery;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Book getBookByTitle(String title) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("title", title);

        Book book = jdbcTemplate.queryForObject(bookByTitleQuery, paramMap, new BeanPropertyRowMapper<>(Book.class));

        verySlowProcessingMethod(book);

        return book;
    }

    private void verySlowProcessingMethod(Book book) {
        char[] chars = book.getAuthor().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

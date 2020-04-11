package rnd.mate00.cacheexample;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {

    private Integer id;
    private String author;
    private String title;
    private Integer themeId;
}

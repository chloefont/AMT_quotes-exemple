package ch.heig.quotes.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "Author")
@Table(name = "authors")
public class AuthorEntity {
    @Getter
    @TableGenerator(name = "genAuthors",
            table = "idAuthors",
            pkColumnName = "name",
            valueColumnName = "val",
            initialValue = 5,
            allocationSize = 100)
    @Id // @GeneratedValue
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genQuotes")
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @OneToMany(mappedBy = "author")
    private List<QuoteEntity> quotes;

    public AuthorEntity() {}

    public AuthorEntity(int id, String name, List<QuoteEntity> quotes) {
        this.id = id;
        this.name = name;
        this.quotes = quotes;
    }
}

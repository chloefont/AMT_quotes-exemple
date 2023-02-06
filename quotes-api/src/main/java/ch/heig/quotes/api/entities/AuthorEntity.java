package ch.heig.quotes.api.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Author")
@Table(name = "authors")
public class AuthorEntity {
    @TableGenerator(name = "genAuthors",
            table = "idAuthors",
            pkColumnName = "name",
            valueColumnName = "val",
            initialValue = 5,
            allocationSize = 100)
    @Id // @GeneratedValue
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genQuotes")
    private int id;
    private String name;
    @OneToMany(mappedBy = "author")
    private List<QuoteEntity> quotes;

    public AuthorEntity() {}

    public AuthorEntity(int id, String name, List<QuoteEntity> quotes) {
        this.id = id;
        this.name = name;
        this.quotes = quotes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuoteEntity> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<QuoteEntity> quotes) {
        this.quotes = quotes;
    }
}

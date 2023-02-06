package ch.heig.quotes.api.entities;


import jakarta.persistence.*;

@Entity(name = "Quote")
@Table(name = "quotes")
public class QuoteEntity {
    @TableGenerator(name = "genQuotes",
            table = "idQuotes",
            pkColumnName = "name",
            valueColumnName = "val",
            initialValue = 3,
            allocationSize = 100)
    @Id // @GeneratedValue
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genQuotes")
    private int id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;
    private String citation;

    public QuoteEntity() {}

    public QuoteEntity(int id, AuthorEntity author, String citation) {
        this.id = id;
        this.author = author;
        this.citation = citation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }
}

package ch.heig.quotes.api.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Quote")
@Table(name = "quotes")
public class QuoteEntity {
    @Getter
    @TableGenerator(name = "genQuotes",
            table = "idQuotes",
            pkColumnName = "name",
            valueColumnName = "val",
            initialValue = 3,
            allocationSize = 100)
    @Id // @GeneratedValue
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genQuotes")
    private int id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @Getter
    @Setter
    private String citation;

    public QuoteEntity() {}

    public QuoteEntity(int id, AuthorEntity author, String citation) {
        this.id = id;
        this.author = author;
        this.citation = citation;
    }
}

package ch.heig.quotes.api.mappers;

import ch.heig.quotes.api.entities.AuthorEntity;
import org.openapitools.model.Author;

public class AuthorMapper {
    static public Author mapToDTO(AuthorEntity authorEntity) {
        Author author = new Author();
        author.setId(authorEntity.getId());
        author.setName(authorEntity.getName());
        author.setQuotes(authorEntity.getQuotes().stream().map(QuoteMapper::mapToDTO).toList());
        return author;
    }

    static public AuthorEntity mapToModel(Author author) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName(author.getName());
        authorEntity.setQuotes(author.getQuotes().stream().map(QuoteMapper::mapToModel).toList());
        return authorEntity;
    }
}

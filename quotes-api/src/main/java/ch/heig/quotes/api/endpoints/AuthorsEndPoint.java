package ch.heig.quotes.api.endpoints;

import ch.heig.quotes.api.repositories.AuthorRepository;
import org.openapitools.api.AuthorsApi;
import org.openapitools.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorsEndPoint implements AuthorsApi {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorsEndPoint(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public ResponseEntity<List<Author>> getAuthors() {
        return AuthorsApi.super.getAuthors();
    }
}

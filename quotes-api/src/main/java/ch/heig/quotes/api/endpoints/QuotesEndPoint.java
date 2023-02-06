package ch.heig.quotes.api.endpoints;

import ch.heig.quotes.api.entities.AuthorEntity;
import ch.heig.quotes.api.exceptions.AuthorNotFoundException;
import ch.heig.quotes.api.repositories.AuthorRepository;
import org.openapitools.api.QuotesApi;
import ch.heig.quotes.api.exceptions.QuoteNotFoundException;
import org.openapitools.model.Quote;
import ch.heig.quotes.api.entities.QuoteEntity;
import ch.heig.quotes.api.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class QuotesEndPoint implements QuotesApi {

    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public ResponseEntity<List<Quote>> getQuotes() {
        List<QuoteEntity> quoteEntities= quoteRepository.findAll();
        List<Quote> quotes  = new ArrayList<>();
        for (QuoteEntity quoteEntity : quoteEntities) {
            Quote quote = new Quote();
            quote.setId(quoteEntity.getId());
            quote.setAuthorId(quoteEntity.getAuthor().getId());
            quote.setCitation(quoteEntity.getCitation());
            quotes.add(quote);
        }
        return new ResponseEntity<List<Quote>>(quotes,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addQuote(@RequestBody Quote quote) {
        QuoteEntity quoteAdded = createAndSaveQuote(quote);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(quoteAdded.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<Quote> getQuote(Integer id) {
        Optional<QuoteEntity> opt = quoteRepository.findById(id);
        if (opt.isPresent()) {
            QuoteEntity quoteEntity = opt.get();
            Quote quote = new Quote();
            quote.setId(quoteEntity.getId());
            quote.setAuthorId(quoteEntity.getAuthor().getId());
            quote.setCitation(quoteEntity.getCitation());
            return new ResponseEntity<Quote>(quote, HttpStatus.OK);
        } else {
//            return ResponseEntity.notFound().build();
            throw new QuoteNotFoundException(id);
        }
    }


    @Override
    public ResponseEntity<Quote> putQuote(Integer id, Quote quote) {
        Optional<QuoteEntity> opt = quoteRepository.findById(id);
        if (opt.isPresent()) {
            QuoteEntity quoteEntity = opt.get();
            Optional<AuthorEntity> authorOpt = authorRepository.findById(quote.getAuthorId());
            if (authorOpt.isPresent()) {
                quoteEntity.setAuthor(authorOpt.get());
            } else {
                throw new QuoteNotFoundException(id);
            }

            quoteEntity.setCitation(quote.getCitation());
            return new ResponseEntity<Quote>(quote, HttpStatus.OK);
        }

        QuoteEntity quoteAdded = createAndSaveQuote(quote);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(quoteAdded.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    private QuoteEntity createAndSaveQuote(Quote quote) {
        QuoteEntity quoteEntity = new QuoteEntity();
        Optional<AuthorEntity> authorOpt = authorRepository.findById(quote.getAuthorId());
        if (authorOpt.isEmpty()) {
            throw new AuthorNotFoundException(quote.getAuthorId());
        }
        quoteEntity.setAuthor(authorOpt.get());
        quoteEntity.setCitation(quote.getCitation());
        return quoteRepository.save(quoteEntity);
    }
}

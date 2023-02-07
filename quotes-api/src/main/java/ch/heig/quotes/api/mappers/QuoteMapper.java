package ch.heig.quotes.api.mappers;

import ch.heig.quotes.api.entities.QuoteEntity;
import org.openapitools.model.Quote;

public class QuoteMapper {
    static public Quote mapToDTO(QuoteEntity quoteEntity) {
        Quote quote = new Quote();
        quote.setId(quoteEntity.getId());
        quote.setCitation(quoteEntity.getCitation());
        quote.setAuthorId(quoteEntity.getAuthor().getId());
        return quote;
    }

    static public QuoteEntity mapToModel(Quote quote) {
        QuoteEntity quoteEntity = new QuoteEntity();
        quoteEntity.setCitation(quote.getCitation());
        return quoteEntity;
    }
}

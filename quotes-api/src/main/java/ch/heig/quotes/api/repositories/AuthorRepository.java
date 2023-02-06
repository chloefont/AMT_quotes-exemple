package ch.heig.quotes.api.repositories;

import ch.heig.quotes.api.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {
    AuthorEntity findById(int id);
}

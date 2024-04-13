package se.iths.java23.petterlundqvist.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.java23.petterlundqvist.webshop.model.Category;
import se.iths.java23.petterlundqvist.webshop.model.Record;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
    List<Record> findByCategory(Category c);
    List<Record> findByNameContainingOrArtistContaining(String s1, String s2);

    boolean existsByNameAndArtist(String s1, String s2);
}

package com.bookstore.bookstore_backend.repository;
import com.bookstore.bookstore_backend.entity.Tag;

import org.hibernate.graph.GraphNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import java.util.Optional;
import java.util.List;
public interface TagRepository extends Neo4jRepository<Tag, Long> {

    @Query("MATCH (givenSubgenre:Subgenre)-[:SUB_GENRE_OF]->(:Genre)<-[:SUB_GENRE_OF]-(relatedSubgenre:Subgenre) " +
           "WHERE givenSubgenre.name = $subgenreName " +
           "RETURN DISTINCT relatedSubgenre.name")
    List<String> findRelatedSubgenres(String subgenreName);

     @Query("MATCH (n:Genre) WHERE n.name = $name RETURN n")
    List<Tag> findGenresByName(String name);

    @Query("MATCH (n:Subgenre) WHERE n.name = $name RETURN n")
    List<Tag> findSubgenresByName(String name);
}
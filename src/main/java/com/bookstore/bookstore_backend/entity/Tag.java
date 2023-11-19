package com.bookstore.bookstore_backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Relationship;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Node
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship(type = "SUB_GENRE_OF", direction = Relationship.Direction.OUTGOING)
    private Set<Tag> subGenres = new HashSet<>();

    public void addSubGenre(Tag tag) {
        this.subGenres.add(tag);
    }
}
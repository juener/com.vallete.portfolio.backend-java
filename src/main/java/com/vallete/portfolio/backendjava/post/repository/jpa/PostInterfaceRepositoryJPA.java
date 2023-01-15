package com.vallete.portfolio.backendjava.post.repository.jpa;

import com.vallete.portfolio.backendjava.post.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostInterfaceRepositoryJPA extends JpaRepository<PostModel, UUID> {

    PostModel getPostById(UUID id);
    PostModel save(PostModel postModel);
    void deleteById(UUID id);
}

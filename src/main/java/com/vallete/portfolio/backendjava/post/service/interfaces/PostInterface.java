package com.vallete.portfolio.backendjava.post.service.interfaces;

import com.vallete.portfolio.backendjava.post.model.PostModel;

import java.util.List;
import java.util.UUID;

public interface PostInterface {

    List<PostModel> findAll();

    PostModel save(PostModel postModel);

//	Post update(Post post);

    PostModel getPostById(UUID idPost);

    void deleteByPostId(UUID idPost);
}

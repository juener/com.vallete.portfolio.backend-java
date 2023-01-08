package com.vallete.portfolio.backendjava.post.service;

import com.vallete.portfolio.backendjava.post.model.PostModel;
import com.vallete.portfolio.backendjava.post.repository.PostRepository;
import com.vallete.portfolio.backendjava.post.service.interfaces.PostInterface;
import com.vallete.portfolio.backendjava.shared.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService implements PostInterface {
    private final PostRepository postRepository;

    @Override
    public List<PostModel> findAll() {
        return postRepository.findAll();
    }

    @Override
    public PostModel save(PostModel post) {
        validate(post);

        return postRepository.save(post);
    }

    private void validate(PostModel postModel) {
        if (postModel.getTitle() == null || postModel.getTitle().trim() == "") {
            throw new BusinessException("Some fields are required: Title, Body, User");
            // add more filters
        }
    }

    @Override
    public PostModel getPostById(UUID idPost) {
        return postRepository.getPostById(idPost);
    }

//	@Override
//	public Post update(Post post) {
//		// TODO Auto-generated method stub
//		return null;
//	}

    @Override
    public void deleteByPostId(UUID idPost) {
        postRepository.deleteById(idPost);
    }

}

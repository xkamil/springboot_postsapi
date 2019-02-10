package com.canx.postapp.repository;

import com.canx.postapp.dto.PostDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostDTO, Long> {
}

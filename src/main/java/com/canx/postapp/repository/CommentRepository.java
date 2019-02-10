package com.canx.postapp.repository;

import com.canx.postapp.dto.CommentDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<CommentDTO, Long> {
}

package com.myblog.repository;

import com.myblog.entites.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Postrepository extends JpaRepository<Post,Long> {
}

package com.fastcamp.projectboard.repository;

import com.fastcamp.projectboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<Article, Long> {
}

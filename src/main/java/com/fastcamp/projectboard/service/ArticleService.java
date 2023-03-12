package com.fastcamp.projectboard.service;

import com.fastcamp.projectboard.domain.type.SearchType;
import com.fastcamp.projectboard.dto.ArticleDto;
import com.fastcamp.projectboard.dto.ArticleWithCommentsDto;
import com.fastcamp.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType title, String search_keyword, Pageable pageable) {
        return Page.empty(); // TODO: 반환값 수정 필요
    }

    public void saveArticle(ArticleDto dto) {
    }

    public void updateArticle(ArticleDto dto) {
    }

    public void deleteArticle(long articleId) {
    }

    public ArticleWithCommentsDto getArticle(Long articleId) {
        return null;
    }
}

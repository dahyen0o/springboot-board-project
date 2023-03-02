package com.fastcamp.projectboard.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;

import com.fastcamp.projectboard.domain.Article;
import com.fastcamp.projectboard.domain.type.SearchType;
import com.fastcamp.projectboard.dto.ArticleDto;
import com.fastcamp.projectboard.dto.ArticleUpdateDto;
import com.fastcamp.projectboard.repository.ArticleRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut;
    @Mock private ArticleRepository articleRepository;

    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
        // Given

        // When
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword");

        // Then
        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글을 조회하면, 게시글을 반환한다.")
    @Test
    void givenId_whenSearchingArticle_thenReturnsArticle() {
        // Given

        // When
        ArticleDto article = sut.searchArticles(1L);

        // Then
        assertThat(article).isNotNull();
    }

    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다.")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle() {
        // Given
        ArticleDto dto = ArticleDto.of(LocalDateTime.now(), "Sen", "title", "content", "#hashtag");
        given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.saveArticle(dto);

        // Then
        // articleRepository는 Article 객체를 저장해야 한다.
        // '어떤' Article 객체인지 내용까지는 확인하지 않음. (이 단계는 유닛 테스트라기엔 깊음)
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글의 ID와 수정 정보를 입력하면, 게시글을 수정한다.")
    @Test
    void givenArticleIdAndModifiedInfo_whenUpdatingArticle_thenUpdatesArticle() {
        // Given
        ArticleUpdateDto dto = ArticleUpdateDto.of("title", "content", "#hashtag");
        given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.updateArticle(1L, dto);

        // Then
        // articleRepository는 Article 객체를 저장해야 한다.
        // '어떤' Article 객체인지 내용까지는 확인하지 않음. (이 단계는 유닛 테스트라기엔 깊음)
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글의 ID를 입력하면, 게시글을 삭제한다.")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeletesArticle() {
        // Given
        ArticleUpdateDto dto = ArticleUpdateDto.of("title", "content", "#hashtag");
        willDoNothing().given(articleRepository).delete(any(Article.class));

        // When
        sut.deleteArticle(1L);

        // Then
        // articleRepository는 Article 객체를 저장해야 한다.
        // '어떤' Article 객체인지 내용까지는 확인하지 않음. (이 단계는 유닛 테스트라기엔 깊음)
        then(articleRepository).should().delete(any(Article.class));
    }
}
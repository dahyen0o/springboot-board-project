package com.fastcamp.projectboard.repository;

//import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fastcamp.projectboard.config.JpaConfig;
import com.fastcamp.projectboard.domain.Article;
import com.fastcamp.projectboard.domain.ArticleComment;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class) // 직접 생성한 Config 파일의 EnableJpaAuditing 옵션 반영하도록
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
//@SpringBootTest
//@Transactional
class JpaRepositoryTest {

    private ArticleRepository articleRepository;
    private ArticleCommentRepository articleCommentRepository;

    // JUnit5부터 테스트 코드에도 생성자 주입 가능
    public JpaRepositoryTest(@Autowired ArticleRepository articleRepository,
                             @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        // given
        // when
        List<Article> articles = articleRepository.findAll();
        // then
        assertThat(articles).isNotNull().hasSize(100);
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        // given
        long previousCount = articleRepository.count();
        Article article = Article.of("new article", "new content", "#spring");
        // when
        articleRepository.save(article);
        // then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
    }
}
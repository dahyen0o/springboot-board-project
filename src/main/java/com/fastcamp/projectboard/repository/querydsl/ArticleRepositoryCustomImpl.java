package com.fastcamp.projectboard.repository.querydsl;

import com.fastcamp.projectboard.domain.Article;
import com.fastcamp.projectboard.domain.QArticle;
import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ArticleRepositoryCustomImpl extends QuerydslRepositorySupport implements ArticleRepositoryCustom {

    public ArticleRepositoryCustomImpl() {
        super(Article.class);
    }

    @Override
    public List<String> findAllDistinctHashtags() {
        QArticle article = QArticle.article;

        return from(article)
                .distinct().select(article.hashtag)
                .where(article.hashtag.isNotNull())
                .fetch();
    }
}

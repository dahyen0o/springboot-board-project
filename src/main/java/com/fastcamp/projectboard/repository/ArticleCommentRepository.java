package com.fastcamp.projectboard.repository;

import com.fastcamp.projectboard.domain.ArticleComment;
import com.fastcamp.projectboard.domain.QArticle;
import com.fastcamp.projectboard.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment> {

    // '테이블_컬럼' 형식
    // = Article의 id로 ArticleComment들을 찾는다
    List<ArticleComment> findByArticle_Id(Long articleId);

    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) {
        // listing 하지 않은 property(column)은 검색에서 제외
        bindings.excludeUnlistedProperties(true);
        // 검색 listing에 추가
        bindings.including(root.content,root.createdAt, root.createdBy);
        // title을 검색할 때 하나의 단어로만(first) & 대소문자 구분없이 검색
        // + StringExpression::likeIgnoreCase = like ''
        //   StringExpression::containsIgnoreCase = like '%%'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}

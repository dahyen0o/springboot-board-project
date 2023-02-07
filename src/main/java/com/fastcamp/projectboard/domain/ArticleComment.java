package com.fastcamp.projectboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@ToString
@Table(indexes = { // 인덱스로 검색 가능
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ArticleComment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter @ManyToOne(optional = false)
    private Article article;
    @Setter @Column(nullable = false, length = 500)
    private String content;

    @CreatedDate private LocalDateTime createdAt;
    @CreatedBy private String createdBy;
    @LastModifiedDate private LocalDateTime modifiedAt;
    @LastModifiedBy private String modifiedBy;

    protected ArticleComment() {
    }

    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }

    public static ArticleComment of(Article article, String content) {
        return new ArticleComment(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleComment that = (ArticleComment) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

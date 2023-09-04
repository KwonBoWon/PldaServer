package me.bowon.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.bowon.springbootdeveloper.domain.Article;
import me.bowon.springbootdeveloper.dto.AddArticleRequest;
import me.bowon.springbootdeveloper.dto.UpdateArticleRequest;
import me.bowon.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    public Article findById(long id) {
        return blogRepository.findById(id) // JPA findById()이용해서 ID를 받아 엔티티를 조회하고 IllegalArgumentException예외발생
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

    }
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public void delete(long id){
        blogRepository.deleteById(id);
    }

    @Transactional // 트랜잭션 메서드 매칭한 메서드를 하나의 트랜잭션으로 묶음
    public Article update(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no found: " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}

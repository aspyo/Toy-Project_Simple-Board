package hello.board.domain.post.application;

import hello.board.domain.category.domain.Category;
import hello.board.domain.post.domain.Post;
import hello.board.domain.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 게시글 생성
    @Transactional
    public Long createPost(Post post) {
        Post saved = postRepository.save(post);
        return saved.getId();
    }

    // 모든 게시글 조회
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    // 특정 게시글 조회
    public Post findPost(Long id) {
        Optional<Post> findPost = postRepository.findById(id);
        return findPost.orElseThrow(() -> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다."));
    }

    // 게시글 삭제
    @Transactional
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    // 게시글 수정
    @Transactional
    public void editPost(Long postId, String title, String content) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다."));
        post.changeTitle(title);
        post.changeContent(content);
    }

    // 메인페이지 게시글 최대 20개 조회
    public List<Post> findMainPagePost() {
        return postRepository.findMainPost();
    }

    // 메인페이지 게시글 최대 20개 조회 2
    public List<Post> findMainPagePost2() {
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "createdAt"));
        return postRepository.findMainPage2(pageRequest);
    }

    // 게시글 페이징 조회
    public Page<Post> findPostsByCategory(Category category, int page) {
        PageRequest pageRequest = PageRequest.of(page-1, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        return postRepository.findByCategory(category, pageRequest);
    }


}

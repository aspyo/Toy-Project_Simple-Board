package hello.board.domain.post.dto;

import lombok.Data;

@Data
public class PostForm {
    private String title;
    private String content;
}

package hello.board.domain.category.domain;

import hello.board.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Category extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    private String description;

    protected Category() {

    }

    @Builder
    public Category(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    public void changeCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

}

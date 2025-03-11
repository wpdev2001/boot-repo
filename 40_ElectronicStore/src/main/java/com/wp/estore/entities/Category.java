package com.wp.estore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    private String categoryId;
    @Column(name = "category_title",length = 60)
    private String title;
    private String description;
    private String coverImage;

}

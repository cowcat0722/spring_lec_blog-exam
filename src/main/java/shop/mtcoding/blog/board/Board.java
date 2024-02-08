package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "board_tb")
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private String username;
}


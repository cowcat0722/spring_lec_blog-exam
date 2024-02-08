package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;

public class BoardRequest {

    @AllArgsConstructor
    @Data
    public static class SaveOrUpdateDTO{
        private String author;
        private String title;
        private String content;
    }

}

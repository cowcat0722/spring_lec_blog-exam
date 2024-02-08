package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public List<Board> findAll() {
        Query query = em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        List<Board> boardList = query.getResultList();
        return boardList;
    }

    @Transactional
    public void save(BoardRequest.SaveDTO saveDTO) {
        Query query = em.createNativeQuery("insert into board_tb(title, content, author) values(?,?,?)");
        query.setParameter(1,saveDTO.getTitle());
        query.setParameter(2,saveDTO.getContent());
        query.setParameter(3,saveDTO.getAuthor());

        query.executeUpdate();
    }
}

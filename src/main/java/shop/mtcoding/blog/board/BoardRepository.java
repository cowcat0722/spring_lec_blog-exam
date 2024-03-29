package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.Constant;
import shop.mtcoding.blog._core.PagingUtil;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public Board findById(int id){
        Query query = em.createNativeQuery("select * from board_tb where id = ?",Board.class);
        query.setParameter(1,id);

        Board board = (Board) query.getSingleResult();

        return board;
    }

    public List<Board> findAll(int page) {
        int value = (page-1) * Constant.PAGING_COUNT;
        Query query = em.createNativeQuery("select * from board_tb order by id desc limit ?,?", Board.class);

        query.setParameter(1,value);
        query.setParameter(2,Constant.PAGING_COUNT);

        List<Board> boardList = query.getResultList();
        return boardList;
    }

    @Transactional
    public void save(BoardRequest.SaveOrUpdateDTO saveDTO) {
        Query query = em.createNativeQuery("insert into board_tb(title, content, author) values(?,?,?)");
        query.setParameter(1,saveDTO.getTitle());
        query.setParameter(2,saveDTO.getContent());
        query.setParameter(3,saveDTO.getAuthor());

        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        Query query = em.createNativeQuery("delete from board_tb where id = ?");
        query.setParameter(1,id);

        query.executeUpdate();
    }

    @Transactional
    public void update(int id, BoardRequest.SaveOrUpdateDTO updateDTO) {
        Query query = em.createNativeQuery("update board_tb set author=?,title=?,content=? where id = ?");
        query.setParameter(1,updateDTO.getAuthor());
        query.setParameter(2,updateDTO.getTitle());
        query.setParameter(3,updateDTO.getContent());
        query.setParameter(4,id);

        query.executeUpdate();
    }

    public int count() {
        Query query = em.createNativeQuery("select count(*) from board_tb");

        int totalCount = ((Number) query.getSingleResult()).intValue();

        return totalCount;
    }
}

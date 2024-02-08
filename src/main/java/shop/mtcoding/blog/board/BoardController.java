package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;
    private final BoardRepository boardRepository;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("boardList",boardList);
        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, HttpServletRequest request) {
        Board board = boardRepository.findById(id);
        request.setAttribute("board",board);

        return "board/updateForm";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveOrUpdateDTO saveDTO, HttpServletRequest request){

        if(saveDTO.getTitle().length()>20){
            request.setAttribute("status",400);
            request.setAttribute("msg","title의 길이가 20자를 초과할 수 없습니다.");
            return "error/40x";
        }
        if(saveDTO.getContent().length()>20){
            request.setAttribute("status",400);
            request.setAttribute("msg","Content의 길이가 20자를 초과할 수 없습니다.");
            return "error/40x";
        }

        boardRepository.save(saveDTO);
        return "redirect:/";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable int id,BoardRequest.SaveOrUpdateDTO updateDTO){
        boardRepository.update(id,updateDTO);
        return "redirect:/";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id){
        boardRepository.delete(id);
        return "redirect:/";
    }
}

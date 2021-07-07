package kr.ac.kopo.ctc.kopo19.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.ctc.kopo19.domain.Board;
import kr.ac.kopo.ctc.kopo19.repository.BoardRepository;

@Controller
public class BoardController {

    @Autowired
    private BoardRepository repo;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView mainPage(ModelAndView mav) {
        List<Board> list = repo.findAll();
        mav.setViewName("index");
        mav.addObject("boardList", list);
        return mav;
    }
    
    @RequestMapping(value = "/board/{selectBoardId}/delete", method = RequestMethod.GET)
    public ModelAndView deleteBoard(ModelAndView mav,
            @PathVariable(name = "selectBoardId") int boardId) {
        Board board = repo.findById(boardId).get();
        repo.delete(board);
        mav.setViewName("deleteBoard");
        return mav;
    }

}

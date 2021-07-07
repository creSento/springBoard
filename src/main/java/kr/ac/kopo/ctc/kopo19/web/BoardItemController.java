package kr.ac.kopo.ctc.kopo19.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.kopo.ctc.kopo19.common.Pagination;
import kr.ac.kopo.ctc.kopo19.domain.Board;
import kr.ac.kopo.ctc.kopo19.domain.BoardItem;
import kr.ac.kopo.ctc.kopo19.repository.BoardItemRepository;
import kr.ac.kopo.ctc.kopo19.repository.BoardRepository;

@Controller
public class BoardItemController {

    @Autowired
    private BoardItemRepository itemRepo;
    
    @Autowired
    private BoardRepository boardRepo;
    

    @RequestMapping(value = "/{boardName}/{curPage}", method = RequestMethod.GET)
    public ModelAndView boardItemList(ModelAndView mav,
            @PathVariable(name = "boardName", required = false) String boardName,
            @PathVariable(name = "curPage", required = false) int curPage) {
        PageRequest page = PageRequest.of((curPage - 1) * 10, 10, Sort.by(Direction.DESC, "id"));
        Board board = boardRepo.findByName(boardName).get();
        Page<BoardItem> boardItemList = itemRepo.findAllByBoardIdAndParentIsNull(board.getId(), page);
        Pagination pagination = new Pagination(curPage, itemRepo.countByBoardIdAndParentIsNull(board.getId()));
        mav.setViewName("list");
        mav.addObject("boardName", boardName);
        mav.addObject("boardItemList", boardItemList.getContent());
        mav.addObject("pagination", pagination);
        return mav;
    }

    @RequestMapping(value = "/{boardName}/{curPage}", method = RequestMethod.POST)
    public ModelAndView boardItemSearchList(ModelAndView mav, @RequestParam(value = "keyWord") String keyWord,
            @PathVariable(name = "boardName", required = false) String boardName,
            @PathVariable(name = "curPage", required = false) int curPage) {
        Board board = boardRepo.findByName(boardName).get();
        PageRequest page = PageRequest.of((curPage - 1) * 10, 10, Sort.by(Direction.DESC, "id"));
        Page<BoardItem> boardItemList = itemRepo.findAllByTitleContainingAndParentIsNull(keyWord, page);
        Pagination pagination = new Pagination(curPage, itemRepo.countByTitleContainingAndBoardIdAndParentIsNull(keyWord, board.getId()));
        mav.setViewName("list");
        mav.addObject("boardName", boardName);
        mav.addObject("boardItemList", boardItemList.getContent());
        mav.addObject("pagination", pagination);
        mav.addObject("keyWord", keyWord);
        return mav;
    }

    @RequestMapping(value = "/{boardName}/{curPage}/{id}", method = RequestMethod.GET)
    public ModelAndView boardItem(ModelAndView mav,
            @PathVariable(name = "boardName", required = false) String boardName,
            @PathVariable(name = "curPage", required = false) int curPage,
            @PathVariable(name = "id") int id) {
        // count hit
        BoardItem item = itemRepo.findById(id).get();
        item.setHit(item.getHit() + 1);
        itemRepo.save(item);
        mav.setViewName("view");
        mav.addObject("board", itemRepo.findById(id).get());
        mav.addObject("commentList", itemRepo.findAllByParent(id));
        mav.addObject("boardName", boardName);
        mav.addObject("curPage", curPage);
        return mav;
    }
    
    @RequestMapping(value = "/{boardName}/writeForm")
    public ModelAndView writeForm(ModelAndView mav, 
            @PathVariable(name = "boardName", required = false) String boardName) {
        mav.setViewName("writeForm");
        mav.addObject("board", boardRepo.findByName(boardName).get());
        return mav;
    }
    
    @RequestMapping(value = "/{boardName}/write", method = RequestMethod.POST)
    public ModelAndView write(ModelAndView mav,
            @PathVariable(name = "boardName", required = false) String boardName,
            @ModelAttribute BoardItem item) {
        Board board = boardRepo.findByName(boardName).get();
        board.addBoardItem(item);
        itemRepo.save(item);
        mav.setViewName("write");
        mav.addObject("boardName", boardName);
        return mav;
    }
    
    @RequestMapping(value = "/{boardName}/{curPage}/{id}/updateForm")
    public ModelAndView updateForm(ModelAndView mav,
            @PathVariable(name = "boardName", required = false) String boardName,
            @PathVariable(name = "curPage", required = false) int curPage,
            @PathVariable(name = "id") int id) {
        mav.setViewName("updateForm");
        mav.addObject("board", itemRepo.findById(id).get());
        mav.addObject("boardName", boardName);
        mav.addObject("curPage", curPage);
        return mav;
    }
    
    @RequestMapping(value = "/{boardName}/{curPage}/{id}/update", method = RequestMethod.POST)
    public ModelAndView update(ModelAndView mav,
            @PathVariable(name = "boardName", required = false) String boardName,
            @PathVariable(name = "curPage", required = false) int curPage,
            @PathVariable(name = "id") int id,
            @ModelAttribute BoardItem item) {
        BoardItem selected = itemRepo.findById(item.getId()).get();
        selected.setTitle(item.getTitle());
        selected.setContent(item.getContent());
        itemRepo.save(selected);
        mav.setViewName("update");
        mav.addObject("board", item);
        mav.addObject("boardName", boardName);
        mav.addObject("curPage", curPage);
        return mav;
    }
    
    @RequestMapping(value = "/{boardName}/{id}/delete")
    public ModelAndView delete(ModelAndView mav, 
            @PathVariable(name = "boardName", required = false) String boardName,
            @PathVariable(name = "id") int id) {
        itemRepo.delete(itemRepo.findById(id).get());
        mav.setViewName("delete");
        mav.addObject("boardName", boardName);
        return mav;
    }
    
    @RequestMapping(value = "/{boardName}/{curPage}/{parentId}/writeComment", method = RequestMethod.POST)
    public ModelAndView writeComment(ModelAndView mav,
            @PathVariable(name = "boardName", required = false) String boardName,
            @PathVariable(name = "curPage", required = false) int curPage,
            @PathVariable(name = "parentId") int id,
            @ModelAttribute BoardItem item) {
        Board board = boardRepo.findByName(boardName).get();
        board.addBoardItem(item);
        itemRepo.save(item);
        mav.setViewName("redirect:/" + boardName + "/" + curPage + "/" + id);
        return mav;
    }
    
    @RequestMapping(value = "/{boardName}/{curPage}/{parentId}/updateComment", method = RequestMethod.POST)
    @ResponseBody
    public String updateComment(
            @PathVariable(name = "boardName", required = false) String boardName,
            @PathVariable(name = "curPage", required = false) int curPage,
            @PathVariable(name = "parentId") int id,
            @RequestBody BoardItem item) {
        Board board = boardRepo.findByName(boardName).get();
        board.addBoardItem(item);
        itemRepo.save(item);
        return "update succeess" + item.toString();
    }
    
    @RequestMapping(value = "/{boardName}/{curPage}/{parentId}/deleteComment", method = RequestMethod.POST)
    @ResponseBody
    public String deleteComment(
            @PathVariable(name = "boardName", required = false) String boardName,
            @PathVariable(name = "curPage", required = false) int curPage,
            @PathVariable(name = "parentId") int id,
            @RequestBody BoardItem item) {
        Board board = boardRepo.findByName(boardName).get();
        board.removeBoardItem(item);
        itemRepo.delete(item);
        return "delete succeess" + item.toString();
    }
}

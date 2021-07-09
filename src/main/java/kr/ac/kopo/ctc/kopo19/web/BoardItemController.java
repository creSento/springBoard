package kr.ac.kopo.ctc.kopo19.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
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

//    @RequestMapping(value = "/{boardName}/{curPage}")
    @RequestMapping(value = "/{boardName}")
    public ModelAndView boardItemSearchList(ModelAndView mav, 
            @RequestParam(value = "keyWord", required = false, defaultValue = "") String keyWord,
            @PathVariable(name = "boardName", required = false) String boardName,
//            @PathVariable(name = "curPage", required = false) int curPage,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) final Pageable pageable) {
        Board board = boardRepo.findByName(boardName).get();
        Page<BoardItem> boardItemList = itemRepo.getListOptionalKeyWord(keyWord, board.getId(), pageable);
        Pagination pagination = new Pagination(boardItemList.getPageable().getPageNumber(), boardItemList.getTotalPages());
        System.out.println(pagination.toString());
        System.out.println(boardItemList.getNumber());
        mav.setViewName("list");
        mav.addObject("boardName", boardName);
        mav.addObject("boardItemList", boardItemList);
        mav.addObject("pagination", pagination);
        mav.addObject("keyWord", keyWord);
        return mav;
    }
    

//    @RequestMapping(value = "/{boardName}/{curPage}/{id}", method = RequestMethod.GET)
    @RequestMapping(value = "/{boardName}/{id}", method = RequestMethod.GET)
    public ModelAndView boardItem(ModelAndView mav,
            @PathVariable(name = "boardName", required = false) String boardName,
//            @PathVariable(name = "curPage", required = false) int curPage,
            @PathVariable(name = "id") int id) {
        // count hit
        BoardItem item = itemRepo.findById(id).get();
        item.setHit(item.getHit() + 1);
        itemRepo.save(item);
        mav.setViewName("view");
        mav.addObject("board", itemRepo.findById(id).get());
        mav.addObject("commentList", itemRepo.findAllByParent(id));
        mav.addObject("boardName", boardName);
//        mav.addObject("curPage", curPage);
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
    
//    @RequestMapping(value = "/{boardName}/{curPage}/{id}/updateForm")
    @RequestMapping(value = "/{boardName}/{id}/updateForm")
    public ModelAndView updateForm(ModelAndView mav,
            @PathVariable(name = "boardName", required = false) String boardName,
//            @PathVariable(name = "curPage", required = false) int curPage,
            @PathVariable(name = "id") int id) {
        mav.setViewName("updateForm");
        mav.addObject("board", itemRepo.findById(id).get());
        mav.addObject("boardName", boardName);
//        mav.addObject("curPage", curPage);
        return mav;
    }
    
//    @RequestMapping(value = "/{boardName}/{curPage}/{id}/update", method = RequestMethod.POST)
    @RequestMapping(value = "/{boardName}/{id}/update", method = RequestMethod.POST)
    public ModelAndView update(ModelAndView mav,
            @PathVariable(name = "boardName", required = false) String boardName,
//            @PathVariable(name = "curPage", required = false) int curPage,
            @PathVariable(name = "id") int id,
            @ModelAttribute BoardItem item) {
        BoardItem selected = itemRepo.findById(item.getId()).get();
        selected.setTitle(item.getTitle());
        selected.setContent(item.getContent());
        itemRepo.save(selected);
        mav.setViewName("update");
        mav.addObject("board", item);
        mav.addObject("boardName", boardName);
//        mav.addObject("curPage", curPage);
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
    
//    @RequestMapping(value = "/{boardName}/{curPage}/{parentId}/writeComment", method = RequestMethod.POST)
    @RequestMapping(value = "/{boardName}/{parentId}/writeComment", method = RequestMethod.POST)
    public ModelAndView writeComment(ModelAndView mav,
            @PathVariable(name = "boardName", required = false) String boardName,
//            @PathVariable(name = "curPage", required = false) int curPage,
            @PathVariable(name = "parentId") int id,
            @ModelAttribute BoardItem item) {
        Board board = boardRepo.findByName(boardName).get();
        board.addBoardItem(item);
        itemRepo.save(item);
//        mav.setViewName("redirect:/" + boardName + "/" + curPage + "/" + id);
        mav.setViewName("redirect:/" + boardName + "/" + id);
        return mav;
    }
    
//    @RequestMapping(value = "/{boardName}/{curPage}/{parentId}/updateComment", method = RequestMethod.POST)
    @RequestMapping(value = "/{boardName}/{parentId}/updateComment", method = RequestMethod.POST)
    @ResponseBody
    public String updateComment(
            @PathVariable(name = "boardName", required = false) String boardName,
//            @PathVariable(name = "curPage", required = false) int curPage,
            @PathVariable(name = "parentId") int id,
            @RequestBody BoardItem item) {
        Board board = boardRepo.findByName(boardName).get();
        board.addBoardItem(item);
        itemRepo.save(item);
        return "update succeess" + item.toString();
    }
    
//    @RequestMapping(value = "/{boardName}/{curPage}/{parentId}/deleteComment", method = RequestMethod.POST)
    @RequestMapping(value = "/{boardName}/{parentId}/deleteComment", method = RequestMethod.POST)
    @ResponseBody
    public String deleteComment(
            @PathVariable(name = "boardName", required = false) String boardName,
//            @PathVariable(name = "curPage", required = false) int curPage,
            @PathVariable(name = "parentId") int id,
            @RequestBody BoardItem item) {
        Board board = boardRepo.findByName(boardName).get();
        board.removeBoardItem(item);
        itemRepo.delete(item);
        return "delete succeess" + item.toString();
    }
}

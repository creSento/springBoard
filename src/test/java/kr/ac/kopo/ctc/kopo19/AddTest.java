package kr.ac.kopo.ctc.kopo19;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import kr.ac.kopo.ctc.kopo19.domain.Board;
import kr.ac.kopo.ctc.kopo19.domain.BoardItem;
import kr.ac.kopo.ctc.kopo19.repository.BoardItemRepository;
import kr.ac.kopo.ctc.kopo19.repository.BoardRepository;

@SpringBootTest
public class AddTest {

    @Autowired
    BoardRepository boardRepo;
    @Autowired
    BoardItemRepository itemRepo;
    
//    @Test
//    @Transactional
    void testA() {
//        BoardItem item = new BoardItem("title", new Date(System.currentTimeMillis()), "content", null, null, 0, null);
        BoardItem item = new BoardItem();
        item.setTitle("title");
        item.setContent("content");
        item.setDate(new Date(System.currentTimeMillis()));
        Board board = boardRepo.findById(3).get();
        item.setBoard(board);
//        board.addBoardItem(item);
//        board.S(item);
        System.out.println("**" + item.toString());
        itemRepo.save(item);
        System.out.println(item.getBoard().toString());
        Board find = boardRepo.findById(3).get();
        System.out.println("**" + find.toString());
        List<BoardItem> list = find.getBoardItemList();
        for (BoardItem b : list) {
            System.out.println(b.toString());
        }
    }
    
    
    @Test
    void testB() {
        PageRequest page = PageRequest.of(0, 10);
        Page<BoardItem> postPage = itemRepo.findAll(page);
        postPage.getTotalElements();
    }
}

package kr.ac.kopo.ctc.kopo19;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import kr.ac.kopo.ctc.kopo19.common.Pagination;
import kr.ac.kopo.ctc.kopo19.domain.Board;
import kr.ac.kopo.ctc.kopo19.domain.BoardItem;
import kr.ac.kopo.ctc.kopo19.repository.BoardItemRepository;
import kr.ac.kopo.ctc.kopo19.repository.BoardRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringBoardPracticeApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(SpringBoardPracticeApplicationTests.class);

    @Autowired
    BoardItemRepository repo;

    @Autowired
    BoardRepository boardRepo;

    @Test
    @Order(1)
    void findChildBoard() {
     // Board findById - child
        Board childBoard = boardRepo.findById(4).get();
        logger.info(childBoard.toString());
    }
    
    @Test
    @Order(2)
    void findNOChildBoard() {
     // Board findById - no child
        Board noChildBoard = boardRepo.findById(3).get();
        logger.info(noChildBoard.toString());
    }

    @Test
    @Order(3)
    void findBoardItems() {
        // BoardItem findById
//        List<BoardItem> itemList = repo.findAllByBoardId(4);
//        for (BoardItem i : itemList) {
//            logger.info(i.toString());
//        }
    }

    @Test
    @Order(4)
    void deleteBoard() {
        // Board deleteById
        boardRepo.deleteById(4);
        List<Board> list = boardRepo.findAll();
        for (Board i : list) {
            logger.info(i.toString());
        }
    }
}

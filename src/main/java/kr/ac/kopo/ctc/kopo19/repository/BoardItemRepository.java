package kr.ac.kopo.ctc.kopo19.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.ac.kopo.ctc.kopo19.domain.BoardItem;

public interface BoardItemRepository extends JpaRepository<BoardItem, Integer> {
    
    @Query("select b from BoardItem b where (title like concat('%', :searchString, '%')"
            + "or content like concat('%', :searchString, '%')) and b_id = :boardId and parent is null")
    Page<BoardItem> getListOptionalKeyWord(@Param("searchString") String searchString, @Param("boardId") int boardId, Pageable pageable);
    
    @Query("select count(b) from BoardItem b where (title like concat('%', :searchString, '%')"
            + "or content like concat('%', :searchString, '%')) and b_id = :boardId and parent is null")
    long getCountOptionalKeyWord(@Param("searchString") String searchString, @Param("boardId") int boardId);
    
    List<BoardItem> findAllByParent(int parent);
    
    Optional<BoardItem> findOneById(int id);
    
}

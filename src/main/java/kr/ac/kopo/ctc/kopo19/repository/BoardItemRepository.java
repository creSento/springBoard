package kr.ac.kopo.ctc.kopo19.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.ac.kopo.ctc.kopo19.domain.BoardItem;

public interface BoardItemRepository extends JpaRepository<BoardItem, Integer> {

    Page<BoardItem> findAllByTitleContaining(String title, Pageable pageable);

    Page<BoardItem> findAllByTitleContainingAndParentIsNull(String title, Pageable pageable);

    Page<BoardItem> findAllByBoardIdAndParentIsNull(int id, Pageable pageable);
    
    List<BoardItem> findAllByBoardId(int id);
    
    Page<BoardItem> findAllByParentIsNull(Pageable pageable);
    
    List<BoardItem> findAllByParent(int parent);
    
    Optional<BoardItem> findOneById(int id);
    
    long countByBoardIdAndParentIsNull(int id);
    
    long countByTitleContainingAndBoardIdAndParentIsNull(String title, int id);
    
}

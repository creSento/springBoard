package kr.ac.kopo.ctc.kopo19.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "b_id")
    int id;
    @Column(name = "b_name")
    String name;
    
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "board", fetch = FetchType.LAZY)
    List<BoardItem> boardItemList = new ArrayList<BoardItem>();
    
    public void addBoardItem(BoardItem item) {
        List<BoardItem> list = getBoardItemList();
        item.setBoard(this);
        item.setDate(new Date(System.currentTimeMillis()));
        list.add(item);
    }
    
    public void removeBoardItem(BoardItem item) {
        boardItemList.remove(item);
        item.setBoard(null);
    }
    
    public Board() {
    }
    
    public Board(String name) {
        this.name = name;
    }

    public Board(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BoardItem> getBoardItemList() {
        return boardItemList;
    }

    public void setBoardItemList(List<BoardItem> boardItemList) {
        this.boardItemList = boardItemList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Board other = (Board) obj;
        if (id != other.id)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Board [id=" + id + ", name=" + name + "]";
    }

}

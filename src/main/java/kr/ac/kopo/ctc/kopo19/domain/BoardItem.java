package kr.ac.kopo.ctc.kopo19.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "board_item")
public class BoardItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    int id;
    @Column(nullable = true)
    String title;
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    Date date;
    @Column(nullable = true)
    String content;
    @Column(nullable = true)
    Integer parent;
    @Column(nullable = true)
    String comment;
    @Column(nullable = true)
    @ColumnDefault("0")
    int hit;
    @Formula("(select count(1) from board_item as bc where bc.parent = id)")
    int commentSize;
    @ManyToOne(optional = false)
    @JoinColumn(name = "b_id")
    Board board;

    // optional : nulluble or not(false = nullable)
    public BoardItem() {
    }

    // all data
    public BoardItem(String title, Date date, String content, Integer parent, String comment, int hit, Board board) {
        this.title = title;
        this.date = date;
        this.content = content;
        this.parent = parent;
        this.comment = comment;
        this.hit = hit;
        this.board = board;
    }

    // insert normal boardItem
    public BoardItem(String title, Date date, String content, Board board) {
        this.title = title;
        this.date = date;
        this.content = content;
        this.board = board;
    }

    // insert comment boardItem
    public BoardItem(Date date, Integer parent, String comment, Board board) {
        this.date = date;
        this.parent = parent;
        this.comment = comment;
    }

    // select by id
    public BoardItem(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(int commentSize) {
        this.commentSize = commentSize;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
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
        BoardItem other = (BoardItem) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BoardItem [id=" + id + ", title=" + title + ", date=" + date + ", content="
                + content + ", parent=" + parent + ", comment=" + comment + ", hit=" + hit + ", commentSize="
                + commentSize + "]";
    }

}

package kr.ac.kopo.ctc.kopo19.common;

import java.io.Serializable;

public class Pagination implements Serializable {

    private static final long serialVersionUID = 1L;
    int pagePerBlock = 10;
    int startPage;
    int endPage;

    public Pagination(int currentPage, int totalPage) {
        startPage = currentPage - (currentPage - 1) % pagePerBlock;
        endPage = startPage + (pagePerBlock - 1);
        if (endPage > totalPage) {
            endPage = totalPage;
        }
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    @Override
    public String toString() {
        return "Pagination [pagePerBlock=" + pagePerBlock + ", startPage=" + startPage + ", endPage=" + endPage + "]";
    }

}

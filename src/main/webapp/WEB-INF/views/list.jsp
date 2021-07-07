<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
  src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
  <div class="container">
    <div class="col-md-9">
      <div class="row">
        <table class="table table-hover">
          <thead>
            <tr>
              <td>No</td>
              <td>Subject</td>
              <td>Date</td>
              <td>Hit</td>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="board" items="${boardItemList }">
              <tr>
                <td>${board.id }</td>
                <td style="width: 60%;">
                  <span style="font-size:medium;">
                  <a href="${pageContext.request.contextPath}/${boardName}/${pagination.currentPage }/${board.id }">
                  ${board.title}</a>
                  </span>
                  <c:if test="${board.commentSize > 0}">
                  <span style="font-size:small;">
                    &nbsp;&nbsp;[${board.commentSize }]
                  </span>
                  </c:if>
                </td>
                <td>${board.date }</td>
                <td>${board.hit }</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
      <div class="row form-group">
        <div class="col-md-9">
          <button class="btn btn-default"
            onclick="location.href='${pageContext.request.contextPath}/${boardName}/writeForm'">Write</button>
          <button class="btn btn-default"
            onclick="location.href='${pageContext.request.contextPath}/${boardName}/${pagination.currentPage }'">List</button>
          <button class="btn btn-default"
            onclick="location.href='${pageContext.request.contextPath}/main'">Main</button>
        </div>
      </div>
      <p>
      <div class="row form-group" align="center">
        <form action="${pageContext.request.contextPath}/${boardName}/${pagination.currentPage }" method="post">
          <div class="col-xs-9">
            <input class="form-control" type="text" name="keyWord">
          </div>
          <div class="col-xs-3">
            <button class="btn btn-default">Search</button>
          </div>
        </form>
      </div>
      <div class="row" align="center">
        <div class="col-md-9 center-block">
          <div class="pagination">
            <input type="hidden" name="page"
              value="${pagination.currentPage}">
            <c:if test="${pagination.startPage > pagination.pagePerBlock }">
              <button
                onclick="location.href='${pageContext.request.contextPath}/${boardName}/${pagination.startPage}'"
                class="btn btn-default btn-sm">&laquo;</button>
            </c:if>
            <c:forEach var="i" begin="${pagination.startPage }"
              end="${pagination.endPage }">
              <c:if test="${pagination.currentPage == i }">
                <button
                  onclick="location.href='${pageContext.request.contextPath}/${boardName}/${i}"
                  class="btn btn-primary btn-sm" disabled="disabled">${i }</button>
              </c:if>
              <c:if test="${pagination.currentPage != i }">
                <button
                  onclick="location.href='${pageContext.request.contextPath}/${boardName}/${i}'"
                  class="btn btn-default btn-sm">${i }</button>
              </c:if>
            </c:forEach>
            <c:if test="${pagination.endPage < pagination.totalPage }">
              <button
                onclick="location.href='${pageContext.request.contextPath}/${boardName}/${pagination.endPage}'"
                class="btn btn-default btn-sm">&raquo;</button>
            </c:if>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
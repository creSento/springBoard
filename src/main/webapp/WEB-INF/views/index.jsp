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
<script type="text/javascript">
  function confirmDelete(id) {
    console.log(id);
    console.log('${pageContext.request.contextPath}/board/'+id+'/delete');
    if (confirm("삭제하시겠습니까? 게시판에 등록된 게시글도 삭제됩니다.")) {
      location.href='${pageContext.request.contextPath}/board/'+id+'/delete';
    }
  }
</script>
</head>
<body>
  <div class="container">
    <div class="col-md-9">
      <div class="row">
      </div>
      <div class="row form-group">
        <div class="col-md-9" style="display: inline-block;">
          <c:forEach var="board" items="${boardList }">
          <div class="row" style="margin: 2em auto; padding: 1em;">
          <button class="btn btn-default"
            onclick="location.href='${pageContext.request.contextPath}/${board.name}'">${board.name }</button>
          <button class="btn btn-warning" onclick="confirmDelete(${board.id})">Delete</button>
          </div>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
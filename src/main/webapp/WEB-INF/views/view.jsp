<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
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
    if (confirm("삭제하시겠습니까?")) {
      location.href='/SpringBoardPractice/${boardName}/${board.id }/delete';
    }
  }
</script>
</head>
<body>
  <div class="container">
    <div class="col-md-9">
      <div class="row">
        <table class="table .table-bordered .table-hover">
          <tbody>
            <tr>
              <td>No</td>
              <td>${board.id }</td>
              <td>Date</td>
              <td>${board.date }</td>
            </tr>
            <tr>
              <td>Title</td>
              <td colspan="3" style="">${board.title}</td>
            </tr>
            <tr>
              <td>Content</td>
              <td colspan="3" style="white-space: pre;">${board.content }</td>
            </tr>
          </tbody>
        </table>
      </div>
      <script type="text/javascript">
      	const parentId = ${board.id};
      	console.log(parentId);
      	
      	function chageMode(id) {
      	  var commId = parseInt(id);
          var htmlSrc = '';
          htmlSrc += '<tr><td style=\"white-space: pre; width: 80%; height: 150px;\">';
          htmlSrc += '<textarea class=\"form-control\" name=\"editComment\" id=\"editComment\" rows=\"4\">';
          htmlSrc += $('#comm'+commId).text().trim();
          htmlSrc += '</textarea></td>';
          htmlSrc += '<td>';
          htmlSrc += '<input class=\"btn btn-sm btn-default\" type=\"button\" value=\"Modify\"';
          htmlSrc += 'onclick=\"modifyComment(' + commId + ')\">';
          htmlSrc += '<input class=\"btn btn-sm btn-default\" type=\"button\" value=\"Cancel\"';
          htmlSrc += 'onclick=\"reloadPage()\">';
          htmlSrc += '</td></tr>';
          $('#comm' + commId).replaceWith(htmlSrc);
        }
      
      	function reloadPage() {
      	  location.reload();
        }
      	
      	function modifyComment(id) {
      	  var commId = parseInt(id);
      	  var newComment = $('#editComment').val();
      	  var paramData = JSON.stringify({
      	    'id': commId,
      	    'parent': parentId,
      	    'comment': newComment,
      	  });
      	  console.log(paramData);
      	  console.log('${pageContext.request.contextPath}/${boardName}/${board.id}/updateComment');
      	  $.ajax({
      	    url: '${pageContext.request.contextPath}/${boardName}/${board.id}/updateComment',
      	    data: paramData,
      	    type: 'POST',
      	    dataType: 'text',
      	    contentType: 'application/json',
      	    success: function(result) {
      	      setTimeout(reloadPage(), 1000);
      	    },
      	    error: function(error) {
      	      console.log(error)
      	    }
      	  })
      	}
      	function confirmDeleteComment(id) {
      	  var commId = parseInt(id);
      	  if (confirm("댓글을 삭제합니다")) {
      	    deleteComment(commId);
      	  } else {
      	    return;
      	  }
      	}
      	
      	function deleteComment(id) {
      	  var commId = parseInt(id);
      	  var paramData = JSON.stringify({
      	    'id': commId
      	  });
      	  $.ajax({
      	    url: '${pageContext.request.contextPath}/${boardName}/${board.id}/deleteComment',
      	    data: paramData,
      	    type: 'POST',
      	    dataType: 'text',
      	    contentType: 'application/json',
      	    success: function(result) {
      	      setTimeout(reloadPage(), 1000);
      	    },
      	    error: function(error) {
      	      console.log(error)
      	    }
      	  })
      	}
      </script>
      <div class="row">
        <table class="table .table-bordered .table-hover">
          <c:forEach var="comm" items="${commentList }">
            <tr id="comm${comm.id }">
              <td style="white-space: pre; width: 80%;">${comm.comment }</td>
              <td><input class="btn btn-sm btn-default"
                type="button" value="Modify"
                onclick="chageMode(${comm.id })">
                <input class="btn btn-sm btn-default" type="button"
                value="Delete"
                onclick="confirmDeleteComment(${comm.id})">
              </td>
            </tr>
          </c:forEach>
        </table>
        <!-- <form name="commentForm"> -->
        <form action="${pageContext.request.contextPath}/${boardName}/${board.id}/writeComment" method="post">
          <input type="hidden" name="parent" value="${board.id }">
          <div class="col-xs-10">
            <textarea class="form-control" name="comment" rows="4"></textarea>
          </div>
          <div class="col-xs-2">
            <button class="btn btn-sm btn-default">Comment</button>
          </div>
        </form>
      </div>
      <p>
      <div class="row">
        <button class="btn btn-default" type="button"
          onclick="location.href='${pageContext.request.contextPath}/${boardName}'">List</button>
        <button class="btn btn-default" type="button"
          onclick="location.href='${pageContext.request.contextPath}/${boardName}/${board.id }/updateForm'">Modify</button>
        <button class="btn btn-default" type="button"
          onclick="confirmDelete(${board.id})">Delete</button>
      </div>
    </div>
  </div>
</body>
</html>
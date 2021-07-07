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
</head>
<body>
  <div class="container">
    <div class="col-md-9">
      <form action="/SpringBoardPractice/${boardName}/${curPage }/${board.id }/update" method="post">
        <div class="row">
          <table class="table .table-bordered .table-hover">
            <tbody>
              <tr>
                <td>No</td>
                <td><input class="form-control" type="number"
                  name="id" value="${board.id}"
                  readonly="readonly"></td>
              </tr>
              <tr>
                <td>Title</td>
                <td><input class="form-control" type="text"
                  name="title" value="${board.title}"></td>
              </tr>
              <tr>
                <td>Content</td>
                <td><textarea class="form-control" name="content"
                    rows="10">${board.content}</textarea></td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="row">
          <button class="btn btn-default"
            onclick="location.href='/SpringBoardPractice/${boardName}/${curPage }'">Cancel</button>
          <button class="btn btn-default" type="submit">Modify</button>
        </div>
      </form>
    </div>
  </div>
</body>
</html>
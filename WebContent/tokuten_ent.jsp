<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="my.css" content="text/css">
<title>得点入力</title>
</head>
<body>
<h2>工事中</h2>

<form action="Tokuten_ent" method="POST" >
試験名<select name="tnamae">
<option value="12年度1学期中間試験">12年度1学期中間試験</option>
<option value="12年度1学期期末試験">12年度1学期期末試験</option>
</select>
<br>教科<select name="ka">
<option value="1">国語</option>
<option value="2">数学</option>
</select>
<br>学年<select name="nen">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
</select>
<br>組<select name="kyu">
<option value="A">A</option>
<option value="B">B</option>
<option value="C">C</option>
<br>
<input type="submit" value="決定">
</select>
</form>


<a href=index.html>トップメニューに戻る</a>
</body>
</html>
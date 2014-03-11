<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="my.css" content="text/css">
<title>成績学級選択</title>
</head>
<body>
<h2>工事中</h2>
<p>表示したい生徒の学年とクラスを選択してください</p>
<form action="Ranking2" method="POST" >
<br>学年<select name="nen">
<option value="A">1</option>
<option value="B">2</option>
</select>
<br>組<select name="kyu">
<option value="A">A</option>
<option value="B">B</option>
<br>
<input type="submit" value="決定">
</select>
</form>

<table>
<tr><th  rowspan=2>試験名</th><th colspan=2>総合</th><th colspan=2>国語</th><tr>
<tr><th>中間試験</th><th>得点</th><th>順位</th><th>得点</th><th>順位</th></tr>
</table>

</body>
</html>
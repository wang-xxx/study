<%--
  Created by IntelliJ IDEA.
  User: wangxing
  Date: 2024/4/7
  Time: 下午4:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>

<form action="file/upload" method="post" enctype="multipart/form-data">
    昵称：<input type="text" name="nickName" value="龙猫"/><br/>
    头像：<input type="file" name="headPicture"/><br/>
    背景：<input type="file" name="backgroundPicture"/><br/>
    <button type="submit">提交</button>
</form>

</body>
</html>

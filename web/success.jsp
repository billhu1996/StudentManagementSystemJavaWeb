<%@ page import="bean.SMSStudentArray" %>
<%@ page import="bean.SMSStudent" %><%--
  Created by IntelliJ IDEA.
  bean.User: bill
  Date: 16/9/6
  Time: 上午8:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<style>
    body {
        text-align:center;
        margin-left:auto;
        margin-right:auto;
        width: 70%;
    }
</style>

<body>
<table>
    <tr>
        <td>姓名</td>
        <td>学号</td>
        <td>性别</td>
        <td>班级</td>
        <td>宿舍</td>
        <td>政治面貌</td>
        <td>修改</td>
        <td>删除</td>
    </tr>
        <%
            SMSStudentArray studentArray = (SMSStudentArray)(request.getSession().getAttribute("students"));
            for (int i = 0; i < studentArray.studentListCount(); i++) {
                SMSStudent student = studentArray.getSingleStudent(i);
                String id = student.getStuNumber();
                String name = student.getStuName();
                String classString = student.getStuClass();
                String dorm = student.getStuDorm();
                String sex = student.getStuSex();
                String joinccp = student.getJoinCCP();
        %>
    <tr>
        <td><%=name%></td>
        <td><%=id%></td>
        <td><%=sex%></td>
        <td><%=classString%></td>
        <td><%=dorm%></td>
        <td><%=joinccp%></td>
        <td></td>
        <td></td>
    </tr>
        <%
            }
        %>
</table>

</body>
</html>

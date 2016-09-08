<%@ page import="bean.UserBean" %><%--
  Created by IntelliJ IDEA.
  bean.User: bill
  Date: 16/8/30
  Time: 上午11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
</head>

<script type="text/javascript">
    var registerFlag=false
    var changePasswordFlag=false
    var deleteFlag=false

    function login() {
        if (form1.usage.value == 0) {//login
            if (form1.id.value == "") {
                alert("请输入用户名!")
                form1.id.focus()
                return
            }
            if (form1.password.value == "") {//判断密码是否为空
                alert("请输入密码！")
                form1.password.focus()
                return
            }
            if (form1.verfyCode.value == "") {//判断验证码是否为空
                alert("请输入验证码!")
                form1.verfyCode.focus()
                return
            }
            form1.submit()
        } else if (form1.usage.value == 1) {//changePassword
            if (form1.id.value == "") {
                alert("请输入用户名!")
                form1.id.focus()
                return
            }
            if (form1.newPassword.value == "") {//判断密码是否为空
                alert("请输入新密码！")
                form1.newPassword.focus()
                return
            }
            if (form1.verfyPassword.value != form1.newPassword.value) {//判断密码是否为空
                alert("两次输入的密码不一致！")
                form1.verfyPassword.focus()
                return
            }
            if (form1.password.value == "") {//判断密码是否为空
                alert("请输入密码！")
                form1.password.focus()
                return
            }
            if (form1.verfyCode.value == "") {//判断验证码是否为空
                alert("请输入验证码!")
                form1.verfyCode.focus()
                return
            }
            form1.submit()
        } else if (form1.usage.value == 2) {//register
            if (form1.id.value == "") {
                alert("请输入用户名!")
                form1.id.focus()
                return
            }
            if (form1.verfyPassword.value != form1.password.value) {//判断密码是否为空
                alert("两次输入的密码不一致！")
                form1.verfyPassword.focus()
                return
            }
            if (form1.password.value == "") {//判断密码是否为空
                alert("请输入密码！")
                form1.password.focus()
                return
            }
            if (form1.verfyCode.value == "") {//判断验证码是否为空
                alert("请输入验证码!")
                form1.verfyCode.focus()
                return
            }
            form1.submit()
        } else if (form1.usage.value == 3) {//delete
            if (form1.id.value == "") {
                alert("请输入用户名!")
                form1.id.focus()
                return
            }
            if (form1.password.value == "") {//判断密码是否为空
                alert("请输入密码！")
                form1.password.focus()
                return
            }
            if (form1.verfyCode.value == "") {//判断验证码是否为空
                alert("请输入验证码!")
                form1.verfyCode.focus()
                return
            }
            form1.submit()
        }
    }

    function changePassword() {
        if (changePasswordFlag == true) {
            changePasswordFlag=false
            form1.usage.value = 0 //changePassword
            document.getElementById("newPassword").type = "hidden"
            document.getElementById("verfyPassword").type = "hidden"
        } else {
            changePasswordFlag=true
            form1.usage.value = 1
            document.getElementById("newPassword").type = "password"
            document.getElementById("verfyPassword").type = "password"
        }
    }

    function register() {
        if (registerFlag == true) {
            registerFlag = false
            form1.usage.value = 0
            document.getElementById("newPassword").type = "hidden"
            document.getElementById("verfyPassword").type = "hidden"
        } else {
            registerFlag = true
            form1.usage.value = 2//register
            document.getElementById("newPassword").type = "hidden"
            document.getElementById("verfyPassword").type = "password"
        }
    }

    function deleteFunc() {
        if (deleteFlag == true) {
            deleteFlag = false
            form1.usage.value = 0
            document.getElementById("newPassword").type = "hidden"
            document.getElementById("verfyPassword").type = "hidden"
        } else {
            deleteFlag = true
            form1.usage.value = 3
            document.getElementById("newPassword").type = "hidden"
            document.getElementById("verfyPassword").type = "hidden"
        }
    }
    function changeImg(){
        document.getElementById("validateCodeImg").src="/drawImage.servlet?"+Math.random();
    }
    form1.usage.value = 0
</script>

<style>
    body {
        text-align:center;
        margin-left:auto;
        margin-right:auto;
        width: 70%;
    }
</style>

<body>
    <form class="form1" name="form1" method="post" action="user">
        <input name="id" type="text" placeholder="用户名"><br><br>
        <input name="password" type="password" placeholder="密码"><br>
        <input type="hidden" id="newPassword" name="newPassword" placeholder="新密码"><br>
        <input type="hidden" id="verfyPassword" name="verfyPassword" placeholder="确认密码"><br>
        <input name="verfyCode" type="text" onkeydown="if (envent.keyCode==13) {form1.Submit.focus();}" size="8" placeholder="验证码" style="height: 15px; width: 60px;">
        <input type="hidden" name="usage" value="0">
        <img src="/drawImage.servlet" id="validateCodeImg" style="height: 15px; width: 60px;" onclick="changeImg()">
        <br><br>
        <input name="Submit" type="button" class="submit1" value="确定" onclick="login()">
    </form>
    <a name="Submit2" class="submit1" href="#" onclick="register()">注册</a>
    <a name="Submit3" href="#" onclick="changePassword()">修改密码</a>
</body>
</html>
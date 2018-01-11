<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>欢迎来到我的博客</title>
    </head>

    <body>
        <p>大家好! 这是我的博客，欢迎常来转转</p>
        <form id="formid"  name= "myform" method = 'post'  action = 'BlogCommitServlet' onsubmit = "return checkUser();" >
            <big width="60" height="40" align="right">标题</big>

            <br>
            <input style="width:500px;height:20px" type="text" value="" class="text2" name = "title" id = "titleid"/>
            <br/>
            <br>
            <big width="60" height="40" align="bottom">内容</big>
            <br/>
            
            <textarea style="width:500px;height: 500px" name="content" clos="100" rows="5" warp="virtual"></textarea>
            
            <br>
            <input style="margin-top: 20px" type="submit" value="提交" class="btn2"  />
            </br>

        </form>
    </body>
</html>

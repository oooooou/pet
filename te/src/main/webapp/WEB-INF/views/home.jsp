<%--
  Created by IntelliJ IDEA.
  User: Insea
  Date: 2017/11/11
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>


    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js">
                function changeRoom() {

                    $.ajax({
                        type : 'POST',
                        contentType : 'application/json',
                        url : 'http://localhost:8080/test/test2',

                        dataType : 'json',
                        data : mydata,
                        success : function(data) {
                            alert("sta =" + data.finalResult  );
                        },
                        error : function() {
                            alert('Err...');
                        }
                    });

                alert('请求已发送，请等待响应...');
        </script>

             ;



</head>
<body>

<h2>Hello !</h2>
<form name="proRegi" action="spitter">
    <input type="submit" value="sub">
</form>
</body>
</html>


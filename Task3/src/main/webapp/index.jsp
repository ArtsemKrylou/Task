<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 28.05.2019
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
    <title>Title</title>
</head>
<body>
<%@page import="models.Subject" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>

<% ArrayList<Subject> subjectsList = (ArrayList) request.getAttribute("subjects"); %>
<h2>QWE</h2>
<%--<p>--%>
<%--${subject.name} ${subject.mark}--%>
<%--<input id="name_${subject.id}" type="text" value="${subject.name}"/>--%>
<%--<input id="mark_${subject.id}" type="text" value="${subject.mark}"/>--%>
<%--<input type="button" value="change" onclick="update(${subject.id})"/>--%>
<%--<input type="button" value="remove" onclick="remove(${subject.id})"/>--%>
<%--</p>--%>

<table cellspacing="2" cellpadding="2">


    <tr>
        <th>Name</th>
        <th>Mark</th>
    </tr>
    <%

        if (request.getAttribute("subjects") != null)
        {
            Iterator<Subject> iterator = subjectsList.iterator();

            while (iterator.hasNext())
            {
                Subject subject = iterator.next();
    %>
    <tr>
        <td><input type="text" id="name_<%=subject.getId()%>" value="<%=subject.getName()%>"></td>

        <td><input type="text" id="mark_<%=subject.getId()%>" value="<%=subject.getMark()%>"></td>

        <td><input type="button" value="change" onclick="update(<%=subject.getId()%>)"/></td>
        <td><input type="button" value="remove" onclick="remove(<%=subject.getId()%>)"/></td>
    </tr>
    <%
            }
        }
    %>
</table>
<input type="text" name="name" id="new_name"/>
<input type="text" name="mark" id="new_mark"/>
<input type="button" value="submit" onclick="create()">

<script>
    function create() {

        var xhr = new XMLHttpRequest();

        xhr.open('post', 'subjects', false);
        var name = document.getElementById("new_name").value;
        var mark = document.getElementById("new_mark").value;
        var body = {
            name: name,
            mark: mark
        };
        if (validate(name, mark)) {
            xhr.send(JSON.stringify(body));

            if (xhr.status != 200) {
                console.log(xhr.status + ': ' + xhr.statusText);
            } else {
                console.log(xhr.responseText);
                location.reload();
            }
        }
    }

    function update(id) {
        var name = document.getElementById('name_' + id).value;
        var mark = document.getElementById('mark_' + id).value;
        var xhr = new XMLHttpRequest();

        xhr.open('put', 'subjects', false);
        var body = {
            name: name,
            mark: mark,
            id: id
        };
        if (validate(name, mark)) {
            xhr.send(JSON.stringify(body));

            if (xhr.status != 200) {
                console.log(xhr.status + ': ' + xhr.statusText);
            } else {
                console.log(xhr.responseText);
                location.reload();
            }
        }


    }

    function remove(id) {
        var xhr = new XMLHttpRequest();
        xhr.open('delete', 'subjects', false);

        var body = {
            id: id
        };

        xhr.send(JSON.stringify(body));

        if (xhr.status != 200) {
            console.log(xhr.status + ': ' + xhr.statusText);
        } else {
            console.log(xhr.responseText);
            location.reload();

        }
    }

    function validate(name, mark) {
        if (name.toString() === ""){
            alert("name not null");
            return false
        }
        if (mark < 1 || mark > 10 ){
            alert("mark should be from 1 to 10");
            return false
        }

        return true
    }
</script>
</body>
</html>

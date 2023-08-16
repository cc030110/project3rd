<%--
  Created by IntelliJ IDEA.
  User: ho625
  Date: 2023-08-14
  Time: 오후 5:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>Title</title>
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <form>
        <label for="id">ID:</label>
        <input type="text" id="id" name="id" value="${user.id}" readonly><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${user.name}" readonly><br>

        <label for="gender">Gender:</label>
        <input type="radio" id="male" name="gender" value="남">
        <label for="male">Male</label>
        <input type="radio" id="female" name="gender" value="여">
        <label for="female">Female</label><br>

        <label for="age">Age:</label>
        <input type="number" id="age" name="age" value="${user.age}" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${user.email}" readonly><br>

        <label for="liveCountry">Living Country:</label>
        <input type="text" id="liveCountry" name="liveCountry" value="${user.liveCountry}" required><br>

        <label for="liveCity">Living City:</label>
        <input type="text" id="liveCity" name="liveCity" value="${user.liveCity}" required><br>

        <label for="profileImg">Profile Image:</label>
        <input type="file" id="profileImg" name="profileImg"><br>



        <input type="submit" value="update" onclick="checkValue(form)">
    </form>
    <script src="/script/user-update.js"></script>
    <c:import url="footer.jsp"/>

</div>
</body>
</html>
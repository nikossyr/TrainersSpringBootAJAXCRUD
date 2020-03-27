<%--
  Created by IntelliJ IDEA.
  User: Nikos.Syrios
  Date: 25/03/2020
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<table class="table" id="trainersAllTable">
    <thead class="thead-dark">
    <tr>
        <th scope="col"></th>
        <th scope="col">First Name</th>
        <th scope="col">Last Name</th>
        <th scope="col">Subject</th>
        <th scope="col">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${trainers}" var="trainer" varStatus="loop">
        <tr>
            <th scope="row"><c:out value="${loop.index + 1}"/></th>
            <td>${trainer.firstName}</td>
            <td>${trainer.lastName}</td>
            <td>${trainer.subject}</td>
            <td>
                <button type="submit" class="btn btn-sm" onclick="editTrainer(${trainer.trainerId})">
                    <a class="text-secondary">
                        <i class="far fa-edit fa-lg"></i>
                    </a>
                </button>

                <button type="button" class="btn btn-sm" onclick="deleteTrainer(${trainer.trainerId})">
                    <a class="text-secondary">
                        <i class="far fa-trash-alt fa-lg"></i>
                    </a>
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

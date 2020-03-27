<%--
  Created by IntelliJ IDEA.
  User: Nikos.Syrios
  Date: 24/03/2020
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Trainers</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/attributestylesheet.css">
</head>
<body>
<main role="main" class="col-md-9 mx-auto col-lg-10">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">Trainers</h1>
        <div>
            <button type="button" class="btn btn-sm" onclick="refreshTable()">
                <a class="text-secondary">
                    <i id="refreshIcon" class="fas fa-sync-alt fa-lg"></i>
                </a>
            </button>
        </div>
    </div>
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
                <td class="trainerFirstName">${trainer.firstName}</td>
                <td class="trainerLastName">${trainer.lastName}</td>
                <td class="trainerSubject">${trainer.subject}</td>
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
</main>

<button type="button" class="btn btn-secondary btn-circle btn-lg bottom-right" data-toggle="modal"
        data-target="#addNewAttributeModal">
    <i class="fas fa-plus"></i>
</button>

<!-- Add New Trainer Modal -->
<div class="modal fade" id="addNewAttributeModal" tabindex="-1" role="dialog"
     aria-labelledby="insertTrainerModalTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="insertTrainerModalTitle">Add New Trainer</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form:form id="addForm" method="POST" action="insertTrainer" modelAttribute="emptyTrainer"
                >
                    <div class="form-group">
                        <form:label path="firstName">First Name</form:label>
                        <form:input path="firstName" type="text" cssClass="form-control"
                                    id="insertTrainerFirstNameInput"
                        />
                        <ul id="insertTrainerFirstNameInputErrors" class="text-danger list-unstyled"></ul>
                    </div>
                    <div class="form-group">
                        <form:label path="lastName">Last Name</form:label>
                        <form:input path="lastName" type="text" cssClass="form-control" id="insertTrainerLastNameInput"
                        />
                        <ul id="insertTrainerLastNameInputErrors" class="text-danger list-unstyled"></ul>
                    </div>
                    <div class="form-group">
                        <form:label path="subject">Subject</form:label>
                        <form:input path="subject" type="text" cssClass="form-control" id="insertTrainerSubjectInput"
                        />
                        <ul id="insertTrainerSubjectInputErrors" class="text-danger list-unstyled"></ul>
                    </div>
                    <input type="hidden" id="insertTrainerId" name="trainerId" value="-1">
                    <div class="modal-footer">
                        <span id="insertTrainerMessage" class="text-success"></span>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Discard</button>
                        <button class="btn btn-primary" type="submit">Submit</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<!--Error modal-->
<div id="errorModalReload" class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="errorModal"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="errorModalTitle">Error</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p id="errorBodyText">Error</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- Delete Affirmation Modal -->
<div class="modal fade" id="deleteAttributeModal" tabindex="-1" role="dialog"
     aria-labelledby="deleteTrainerModal"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteTrainerScrollableTitle">Delete Trainer</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="deleteBodyText">
                Are you sure you want to permanently DELETE this Trainer?
            </div>
            <div class="modal-footer">
                <form method="POST" action="deleteTrainer">
                    <input type="hidden" id="deleteTrainerId" name="trainerId" value="-1">
                    <input type="hidden" id="deleteTrainerFirstName" name="firstName">
                    <input type="hidden" id="deleteTrainerLastName" name="lastName">
                    <input type="hidden" id="deleteTrainerSubject" name="subject">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button id="deleteBtn" type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>

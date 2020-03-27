"use strict";

// Gets the app base url from browser
function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}

// Fetches all trainers from DB and refreshes the Trainers table
function refreshTable() {
    $("#refreshIcon").addClass("fa-spin");
    let url = getContextPath() + "/getAllTrainers";
    $("#trainersAllTable").load(url, function (response, status, xhr) {
        if (status == "success") {
            setTimeout(function () {
                $("#refreshIcon").removeClass("fa-spin")
            }, 1000);
        } else if (status == "error") {
            $("#refreshIcon").removeClass("fa-spin");
            $("#errorBodyText").text("Connection failure. Please try again later.");
            $("#errorModalReload").modal();
        }
    });
}

// Form Validation upon clicking Submit
$(document).ready(function () {
    $("#addForm").submit(function (e) {
        e.preventDefault(); // avoid to execute the actual submit of the form.
        $("#insertTrainerFirstNameInputErrors").empty();
        $("#insertTrainerLastNameInputErrors").empty();
        $("#insertTrainerSubjectInputErrors").empty();
        let form = $(this);
        let url = form.attr('action');

        $.ajax({
            type: "POST",
            url: url,
            timeout: 3000,
            data: form.serialize(), // serializes the form's elements.
            success: function (response) {
                if (response.validated === false) {
                    $("#insertTrainerMessage").text("Please use valid values").addClass("text-danger");
                    let firstNameErrors = response.errorMessages.firstName;
                    if (firstNameErrors !== undefined) {
                        $("#insertTrainerFirstNameInput").addClass('is-invalid');
                        for (let i = 0; i < firstNameErrors.length; i++) {
                            if (firstNameErrors[i] != null) {
                                $("#insertTrainerFirstNameInputErrors").append("<li>" + firstNameErrors[i] + '</li>');
                            }
                        }
                    } else {
                        $("#insertTrainerFirstNameInput").removeClass('is-invalid').addClass('is-valid');
                    }
                    let lastNameErrors = response.errorMessages.lastName;
                    if (lastNameErrors !== undefined) {
                        $("#insertTrainerLastNameInput").addClass('is-invalid');
                        for (let i = 0; i < lastNameErrors.length; i++) {
                            if (lastNameErrors[i] != null) {
                                $("#insertTrainerLastNameInputErrors").append("<li>" + lastNameErrors[i] + '</li>');
                            }
                        }
                    } else {
                        $("#insertTrainerLastNameInput").removeClass('is-invalid').addClass('is-valid');
                    }
                    let subjectErrors = response.errorMessages.subject;
                    if (subjectErrors !== undefined) {
                        for (let i = 0; i < subjectErrors.length; i++) {
                            $("#insertTrainerSubjectInput").addClass('is-invalid');
                            if (subjectErrors[i] != null) {
                                $("#insertTrainerSubjectInputErrors").append("<li>" + subjectErrors[i] + '</li>');
                            }
                        }
                    } else {
                        $("#insertTrainerSubjectInput").removeClass('is-invalid').addClass('is-valid');
                    }
                } else {
                    $("#insertTrainerFirstNameInput").removeClass('is-invalid').removeClass("is-valid");
                    $("#insertTrainerLastNameInput").removeClass('is-invalid').removeClass("is-valid");
                    $("#insertTrainerSubjectInput").removeClass('is-invalid').removeClass("is-valid");
                    $("#insertTrainerMessage").text("Success!").removeClass("text-danger").addClass("text-success");
                    setTimeout(function () {
                        $("#addNewAttributeModal").modal('toggle');
                        form.find("input[type=text], textarea")
                            .val("")
                            .prop('checked', false)
                            .prop('selected', false)
                            .removeClass('is-invalid');
                        $("#insertTrainerId").val(0);
                    }, 100);
                    refreshTable();

                }
            },
            error: function (response) {
                $("#insertTrainerMessage").text("Unable to connect. Try again later.").addClass("text-danger");
            }
        });

    });
});

// Asks the backend for a specific Trainer and fills the form with the Trainers info
function editTrainer(id) {
    $("#insertTrainerModalTitle").text('Edit Trainer');
    $.ajax({
        url: getContextPath() + "/getTrainer",
        type: "post",
        timeout: 1000,
        data: {
            trainerId: id
        },
        success: function (response) {
            if (response.statusCode === 1) {
                $("#addNewAttributeModal").modal();
                $("#insertTrainerFirstNameInput").val(response.trainer.firstName);
                $("#insertTrainerLastNameInput").val(response.trainer.lastName);
                $("#insertTrainerSubjectInput").val(response.trainer.subject);
                $("#insertTrainerId").val(response.trainer.trainerId);
            } else if (response.statusCode === -1) {
                refreshTable();
                $("#insertTrainerMessage").text(response.message);
                $("#errorModalReload").modal();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#errorBodyText").text("Connection failure. Please try again later.");
            $("#errorModalReload").modal();
        }
    })
}

// Asks the backend for a specific Trainer to verify that it exists and opens dialogue to verify deletion
function deleteTrainer(id) {
    $.ajax({
        url: getContextPath() + "/getTrainer",
        type: "post",
        timeout: 1000,
        data: {
            trainerId: id
        },
        success: function (response) {
            if (response.statusCode === 1) {
                $("#deleteAttributeModal").modal();
                $("#deleteBodyText").text(
                    "Are you sure you want to permanently DELETE " + response.trainer.firstName
                    + " " + response.trainer.lastName + "?"
                );
                $("#deleteTrainerFirstName").val(response.trainer.firstName);
                $("#deleteTrainerLastName").val(response.trainer.lastName);
                $("#deleteTrainerSubject").val(response.trainer.subject);
                $("#deleteTrainerId").val(response.trainer.trainerId);
            } else if (response.statusCode === -1) {
                refreshTable();
                $("#errorBodyText").text(response.message);
                $("#errorModalReload").modal();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $("#errorBodyText").text("Connection failure. Please try again later.");
            $("#errorModalReload").modal();
        }
    })
}

// Handles form cleanup after the modal is closed
$(document).ready(function () {
    $('#addNewAttributeModal').on('hidden.bs.modal', function (e) {
        $(this).find("input[type=text], textarea")
            .val("")
            .prop('checked', false)
            .prop('selected', false)
            .removeClass('is-invalid');
        $("#insertTrainerId").val(0);
        $("#insertTrainerFirstNameInputErrors").empty();
        $("#insertTrainerFirstNameInput").removeClass('is-invalid').removeClass("is-valid");
        $("#insertTrainerLastNameInputErrors").empty();
        $("#insertTrainerLastNameInput").removeClass('is-invalid').removeClass("is-valid");
        $("#insertTrainerSubjectInputErrors").empty();
        $("#insertTrainerSubjectInput").removeClass('is-invalid').removeClass("is-valid");
        $("#insertTrainerMessage").text("").removeClass("text-danger");
        $("#insertTrainerModalTitle").text('Add New Trainer');
    })
});
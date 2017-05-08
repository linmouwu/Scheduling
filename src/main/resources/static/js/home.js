function changeToUserProfile() {
    $('#edit_group_schedule').fadeOut();
    $('#group_schedule').fadeOut();
    $('#user_profile').delay(350).fadeIn();
    $('#pending_request').fadeOut();
    $('#calendar').fadeOut();
    $('#group_calendar').fadeOut();
    $('#schedule').fadeOut();
    $('#staff_management').fadeOut();
    $('#editEvent').fadeOut();
    $('#time_cycle_div').fadeOut();
    $('#permission_group').fadeOut();
    $('#shift_type_management').fadeOut();
    $('#holiday_management').fadeOut();
    $('#assign_holiday').fadeOut();
}
function changeToRequest() {
    $('#edit_group_schedule').fadeOut();
    $('#group_schedule').fadeOut();
    $('#pending_request').delay(350).fadeIn();
    $('#calendar').fadeOut();
    $('#group_calendar').fadeOut();
    $('#schedule').fadeOut();
    $('#staff_management').fadeOut();
    $('#user_profile').fadeOut();
    $('#editEvent').fadeOut();
    $('#time_cycle_div').fadeOut();
    $('#permission_group').fadeOut();
    $('#holiday_management').fadeOut();
    $('#assign_holiday').fadeOut();
    $('#shift_type_management').fadeOut();

}
function changeToCalendar() {
    $('#edit_group_schedule').fadeOut();
    $('#group_schedule').fadeOut();
    $('#pending_request').fadeOut();
    $('#calendar').delay(350).fadeIn();
    $('#group_calendar').fadeOut();
    $('#schedule').fadeOut();
    $('#staff_management').fadeOut();
    $('#user_profile').fadeOut();
    $('#editEvent').fadeOut();
    $('#time_cycle_div').fadeOut();
    $('#permission_group').fadeOut();
    $('#holiday_management').fadeOut();
    $('#assign_holiday').fadeOut();
    $('#shift_type_management').fadeOut();
}
function changeToGroupCalendar() {
    $('#edit_group_schedule').fadeOut();
    $('#group_schedule').fadeOut();
    $('#pending_request').fadeOut();
    $('#calendar').fadeOut();
    $('#group_calendar').delay(350).fadeIn();
    $('#schedule').fadeOut();
    $('#staff_management').fadeOut();
    $('#user_profile').fadeOut();
    $('#editEvent').fadeOut();
    $('#time_cycle_div').fadeOut();
    $('#permission_group').fadeOut();
    $('#shift_type_management').fadeOut();
    $('#holiday_management').fadeOut();
    $('#assign_holiday').fadeOut();
}
function changeToStaff() {
    $('#edit_group_schedule').fadeOut();
    $('#group_schedule').fadeOut();
    $('#pending_request').fadeOut();
    $('#calendar').fadeOut();
    $('#group_calendar').fadeOut();
    $('#schedule').fadeOut();
    $('#staff_management').delay(350).fadeIn();
    $('#editEvent').fadeOut();
    $('#user_profile').fadeOut();
    $('#time_cycle_div').fadeOut();
    $('#permission_group').fadeOut();
    $('#holiday_management').fadeOut();
    $('#assign_holiday').fadeOut();
    $('#shift_type_management').fadeOut();
}
function changeToAssignHoliday() {
    $('#pending_request').fadeOut();
    $('#calendar').fadeOut();
    $('#group_calendar').fadeOut();
    $('#schedule').fadeOut();
    $('#staff_management').fadeOut();
    $('#user_profile').fadeOut();
    $('#editEvent').fadeOut();
    $('#time_cycle_div').fadeOut();
    $('#permission_group').fadeOut();
    $('#holiday_management').fadeOut();
    $('#assign_holiday').delay(350).fadeIn();
    $('#shift_type_management').fadeOut();
}

function changeToShiftType() {
    $('#group_schedule').fadeOut();
    $('#pending_request').fadeOut();
    $('#calendar').fadeOut();
    $('#group_calendar').fadeOut();
    $('#schedule').fadeOut();
    $('#staff_management').fadeOut();
    $('#shift_type_management').delay(350).fadeIn();
    $('#editEvent').fadeOut();
    $('#user_profile').fadeOut();
    $('#time_cycle_div').fadeOut();
    $('#permission_group').fadeOut();
}

function newUserForm() {
    $('#add_user_div_id').slideToggle();
}

function newShiftType() {
    $('#add_shift_type_div_id').slideToggle();
}

function newAssignHoliday(id) {
    $('#assign_holiday_union_div_id').slideToggle();
    $('#current_assign_union_id').val(id);
    document.getElementById("assign_union_id_div").innerHTML = "Edit Union: " + id;
}
function changeToCreateRequest() {
    $('#edit_group_schedule').fadeOut();
    $('#group_schedule').fadeOut();
    $('#pending_request').fadeOut();
    $('#calendar').fadeOut();
    $('#group_calendar').fadeOut();
    $('#schedule').delay(350).fadeIn();
    $('#staff_management').fadeOut();
    $('#user_profile').fadeOut();
    $('#editEvent').fadeOut();
    $('#time_cycle_div').fadeOut();
    $('#permission_group').fadeOut();
    $('#holiday_management').fadeOut();
    $('#assign_holiday').fadeOut();
    $('#shift_type_management').fadeOut();

}

//
// function changeToSchedule() {
//     $('#schedule').delay(350).fadeIn();
//     $('#pending_request').fadeOut();
//     $('#calendar').fadeOut();
//     $('#group_calendar').fadeOut();
//     $('#staff_management').fadeOut();
//     $('#editEvent').fadeOut();
//     $('#user_profile').fadeOut();
//     $('#time_cycle_div').fadeOut();
//     $('#permission_group').fadeOut();
// }
function changeToCreateGroupRequest() {
    $("#group_message p").text("");
    $("#edit_group_message p").text("");
    $('#edit_group_schedule').fadeOut();
    $('#pending_request').fadeOut();
    $('#calendar').fadeOut();
    $('#group_calendar').fadeOut();

    $('#group_schedule').delay(350).fadeIn();
    $('#staff_management').fadeOut();
    $('#user_profile').fadeOut();
    $('#editEvent').fadeOut();
    $('#schedule').fadeOut();
    $('#time_cycle_div').fadeOut();
    $('#permission_group').fadeOut();
    $('#shift_type_management').fadeOut();
    // $.get('allGroupSchedule').done(function(data){
    //     $('')
    // });

}
function changeToTCC() {
    $('#edit_group_schedule').fadeOut();
    $('#group_schedule').fadeOut();
    $('#time_cycle_div').delay(350).fadeIn();
    $('#permission_group').fadeOut();
    $('#user_profile').fadeOut();
    $('#schedule').fadeOut();
    $('#pending_request').fadeOut();
    $('#calendar').fadeOut();
    $('#group_calendar').fadeOut();
    $('#staff_management').fadeOut();
    $('#editEvent').fadeOut();
    $('#holiday_management').fadeOut();
    $('#assign_holiday').fadeOut();
}
function changeToHolidayManagement() {
    $('#holiday_management').delay(350).fadeIn();
    $('#time_cycle_div').fadeOut();
    $('#permission_group').fadeOut();
    $('#user_profile').fadeOut();
    $('#schedule').fadeOut();
    $('#pending_request').fadeOut();
    $('#calendar').fadeOut();
    $('#group_calendar').fadeOut();
    $('#staff_management').fadeOut();
    $('#editEvent').fadeOut();
    $('#assign_holiday').fadeOut();
    $('#shift_type_management').fadeOut();

}

function changeToPGM() {
    $('#edit_group_schedule').fadeOut();
    $('#group_schedule').fadeOut();
    $('#time_cycle_div').fadeOut();
    $('#permission_group').delay(350).fadeIn();
    $('#user_profile').fadeOut();
    $('#schedule').fadeOut();
    $('#pending_request').fadeOut();
    $('#calendar').fadeOut();
    $('#group_calendar').fadeOut();
    $('#staff_management').fadeOut();
    $('#editEvent').fadeOut();
    $('#shift_type_management').fadeOut();
    $('#holiday_management').fadeOut();
    $('#assign_holiday').fadeOut();
}

function changeToEditEvent(editRequest_id) {
    $('#group_schedule').fadeOut();
    $('#edit_group_schedule').fadeOut();
    $('#editEvent').delay(350).fadeIn();
    $('#time_cycle_div').fadeOut();
    $('#permission_group').fadeOut();
    $('#user_profile').fadeOut();
    $('#schedule').fadeOut();
    $('#pending_request').fadeOut();
    $('#calendar').fadeOut();
    $('#group_calendar').fadeOut();
    $('#staff_management').fadeOut();
    $('#shift_type_management').fadeOut();
    $('#holiday_management').fadeOut();
    $('#assign_holiday').fadeOut();

    $.get("getEditEvent", {
        'id': editRequest_id
    }).done(function (data) {

        var pGId = $('#create_event_permission_group').val();

        var formattedStart = getFormattedDate(new Date(data.startTime));
        var formattedEnd = getFormattedDate(new Date(data.endTime));

        $('#edit_StartTime_ID').val(formattedStart);
        $('#edit_EndTime_ID').val(formattedEnd);
        $('#selected_request_type').val(data.eventType);
        $('#selected_request_type').text(data.eventType);
        $('#edit_shift_off_ID').val(data.shiftType.shiftName);
        $('#edit_shift_off_ID').text(data.shiftType.shiftName);
        $('#edit_event_description').val(data.description);
        $('#currentEditId').val(data.id);
        $('#currentEditId').text(data.id);

    })
}
function updateGroupSchedule() {
    var editRequest_id = $('#edit_id').val();
    // var recruit_ID = $('#recrui_ID').val();
    var startTime_id = $('#edit_start_time_group').val();
    var endTime_id = $('#edit_end_time_group').val();
    var description = $('#edit_group_schedule_description').val();
    var event_type = $('#edit_shift_type').val();
    var selected_officers = [];

    if ($("#edit_select_all input").is(':checked')) {

        $("#edit_group_officers input").each(function () {
            selected_officers.push($(this).val());

        });
    } else {
        $("#edit_group_officers input:checked").each(function () {
            selected_officers.push($(this).val());

        });

    }
    //if the input is not valid.
    var markup = "";

    if (!event_type) {
        markup += "You didn't select your shift type.";
    }
    if (startTime_id == "") {
        markup += "You didn't select any start time.";
    }
    if (endTime_id == "") {
        markup += "You didn't select any end time.";
    }
    if (selected_officers.length == 0) {
        markup += "You didn't select any officers.";
    }
    if (markup.length == 0) {

        $.post("update_group_schedule", {
            'scheduleId': editRequest_id,
            'startTime': startTime_id,
            'endTime': endTime_id,
            'description': description,
            'shiftType': event_type,
            'selectedOfficers[]': selected_officers
        }).done(function (data) {

            alert("Event updated. When re-login you could see the change.");
            //location.reload();
            changeToCreateGroupRequest();

        });
    }
    else {
        $("#edit_group_message p").text(markup);
    }

}
function changeToEditGroupSchedule(schedule_id) {
    $("#group_message p").text("");
    $("#edit_group_message p").text("");
    $('#group_schedule').fadeOut();
    $('#edit_group_schedule').delay(350).fadeIn();
    $('#time_cycle_div').fadeOut();
    $('#permission_group').fadeOut();
    $('#user_profile').fadeOut();
    $('#schedule').fadeOut();
    $('#pending_request').fadeOut();
    $('#calendar').fadeOut();
    $('#group_calendar').fadeOut();
    $('#staff_management').fadeOut();
    $('#editEvent').fadeOut();
    $('#shift_type_management').fadeOut();

    $.get("getEditSchedule", {
        'id': schedule_id
    }).done(function (data) {

        var formattedStart = getFormattedDate(new Date(data.startTime));
        var formattedEnd = getFormattedDate(new Date(data.endTime));
        $('#edit_id').val(schedule_id);
        $('#edit_shift_type').val(data.shiftType.shiftName).prop('selected', true);
        $('#edit_start_time_group').val(formattedStart);
        $('#edit_end_time_group').val(formattedEnd);
        $('#edit_group_schedule_description').val(data.description);
        // $('#currentEditId').val(data.id);

    });
}


function getFormattedDate(date) {
    var year = date.getFullYear();
    var month = (1 + date.getMonth()).toString();
    month = month.length > 1 ? month : '0' + month;
    var day = date.getDate().toString();
    day = day.length > 1 ? day : '0' + day;
    return month + '/' + day + '/' + year;
}

function updatePG(uid) {

    var selectId = '#pageSelect' + uid;

    var updatePG = $(selectId).val();

    $.post('/update_permission_group', {
        'uid': uid,
        'permissionGroup': updatePG
    }).done(function () {

        alert("Permission Group Updated: " + uid + ", when re-login you could see the change.");

    });

}

function updateHolidayDate() {
    var newHolidayDate = [];
    var holidayTable = document.getElementById("holiday_list_table");

    for (var i = 1; i < holidayTable.rows.length; i++) {
        var newDate = holidayTable.rows[i].cells[2].getElementsByTagName('input')[0].value;
        if (newDate !== null && newDate !== undefined && newDate !== "") {
            newHolidayDate.push(newDate);
        } else {
            var tempDate = new Date(holidayTable.rows[i].cells[1].innerHTML);
            var format = moment(tempDate).format('YYYY-MM-DD');
            newHolidayDate.push(format);
        }
    }
    $.post("updateHolidayDate", {
        'dateList[]': newHolidayDate,
    }).done(function (data) {
        var newTable = document.getElementById("holiday_list_table");
        for (var i = 1; i < holidayTable.rows.length; i++) {
            var date = new Date(data[i - 1].date);
            var format = moment(date).format('MM/DD/YYYY');
            newTable.rows[i].cells[1].innerHTML = format;
        }
    });


}
function addUser() {

    var uid_ID = $('#uid_ID').val();
    var lastName_ID = $('#lastName_ID').val();
    var firstName_ID = $('#firstName_ID').val();
    var badgeNum_ID = $('#badgeNum_ID').val();
    var title_ID = $('#title_ID').val();
    var gender_ID = $('#gender_ID').val();
    var permissionGroup_ID = $('#permissionGroup_ID').val();
    var union_ID = $('#unio_ID').val();
    var contractEmployee_id = $('#contractEmployee_id').val();
    var hireDate_ID = $('#hireDate_ID').val();
    var promoteDate_ID = $('#promoteDate_ID').val();
    var trainerID_ID = $('#trainerID_ID').val();
    var seniority = (promoteDate_ID == '' || hireDate_ID == '') ? 0
        : new Date(promoteDate_ID).getFullYear() - new Date(hireDate_ID).getFullYear();

    if (uid_ID === "" || lastName_ID === "" || firstName_ID === "" || badgeNum_ID === ""
        || title_ID === "" || gender_ID === "" || permissionGroup_ID === "" || union_ID === ""
        || contractEmployee_id === "" || hireDate_ID === "" || promoteDate_ID === ""
    ) {
        $('#add_user_div_id').prepend("<p style='color: red'>Please input all values.</p>");
        return;
    }

    $.post("add_user", {
        'uid': uid_ID,
        'lastName': lastName_ID,
        'firstName': firstName_ID,
        'badgeNum': badgeNum_ID,
        'title': title_ID,
        'gender': gender_ID,
        'seniority': seniority,
        'permissionGroup': permissionGroup_ID,
        'union': union_ID,
        'contractEmployee': contractEmployee_id,
        'hireDate': hireDate_ID,
        'promoteDate': promoteDate_ID,
        'trainer': trainerID_ID
    }).done(function (data) {

        if (data == "Already existed") {
            $('#staff_management').prepend("<p style='color: red'>UID already taken.</p>");
        }
        else {

            var markup =
                "<tr><td>" + data.badgeNum +
                "</td><td>" + data.lastName +
                "</td><td>" + data.firstName +
                "</td><td>" + data.title +
                "</td><td>" + data.gender +
                "</td><td>" + data.seniority +
                "</td><td>" + data.permissionGroup.role +
                "</td><td>" + data.union.name +
                "</td><td>" + hireDate_ID +
                "</td><td>" + promoteDate_ID +
                "</td><td><input type=\"button\" value=\"Edit\" onclick='newEditUserForm(" + data.id + ");'/>" +
                "</td></tr>";
            $('#user_list_table > tbody').append(markup).hide().slideDown();
        }

        cancelAddUser();
    })

}

function addShiftType() {

    var shiftTypeName = $('#shiftTypeName_ID').val();
    var startTime = $('#startTimeShift_ID').val();
    var endTime = $('#endTimeShift_ID').val();
    var description = $('#notes_ID').val();

    $.post("add_shift_type", {
        'shiftTypeName': shiftTypeName,
        'startTime': startTime,
        'endTime': endTime,
        'description': description
    }).done(function (data) {

        if (data == "Already existed") {
            $('#shift_type_management').append(data);
        }
        else {
            var markup =
                "<tr id=\"shiftType" + data.id + "\"><td>" + data.shiftName +
                "</td><td>" + data.startTime +
                "</td><td>" + data.endTime +
                "</td><td>" + data.description +
                ad + "\)' class=\"btn btn-danger\">Remove</button></td></tr>";
            $('#shift_type_list_table > tbody').append(markup).hide().slideDown();
        }

        cancelShiftType();
    })

}

function addGroupEvent(start_times, end_times) {

    var startTime_id = $('#start_time_group').val();
    var endTime_id = $('#end_time_group').val();
    if (startTime_id != '' && endTime_id != '') {
        start_times.push(startTime_id);
        end_times.push(endTime_id);
    }
    var description = $('#group_schedule_description').val();
    var shift_type = $('#shift_type').val();
    var selected_officers = [];
    if ($("#select_all input").is(':checked')) {

        $("#group_officers input").each(function () {
            selected_officers.push($(this).val());

        });
    } else {

        $("#group_officers input:checked").each(function () {
            selected_officers.push($(this).val());

        });
    }

    var markup = "";

    if (!shift_type) {
        markup += "You didn't select your shift type.";
    }
    if (start_times.length == 0) {
        markup += "You didn't select any start time.";
    }
    if (end_times.length == 0) {
        markup += "You didn't select any end time.";
    }
    if (selected_officers.length == 0) {
        markup += "You didn't select any officers.";
    }
    if (markup.length == 0) {

        // var totalDays = (startTime_id == '' || endTime_id == '') ? 0
        //     : new Date(startTime_id).getDate() - new Date(endTime_id).getDate();
        $.post("createGroupEvent", {
            'selectedOfficers[]': selected_officers,
            'startTime[]': start_times,
            'endTime[]': end_times,
            'description': description,
            'shift_type': shift_type,
        }).done(function (data) {
            if (data == "Remain Day is not enough") {
                // $('#staff_management').append(data);
            }
            else {
                for (var i = 0; i < data.length; i++) {

                    var mark =
                        "<tr><td>" + data[i].id +
                        "</td><td>" + getFormattedDate(new Date(data[i].startTime)) +
                        "</td><td>" + getFormattedDate(new Date(data[i].endTime)) +
                        "</td><td>" + shift_type +
                        "</td><td>" + selected_officers.length +
                        "</td><td>" + description;
                    mark += "</td><td><a href='javascript:void(0);' onclick='changeToEditGroupSchedule(" + data[i].id + ")' class='btn btn-xs btn-default'>Edit</a><a href='javascript:void(0);' onclick='deleteGroupSchedule(" + data[i].id + ");removeRow(this);' class='btn btn-xs btn-default'>Delete</a></td></tr>";
                    $('#group_schedule_list_table > tbody').prepend(mark).hide().slideDown();
                }

            }
            //location.reload();
            clearGroupSchedule();
            $('#selected-time-ranges').empty();
            changeToCreateGroupRequest();

        });
    } else {

        $("#group_message p").text(markup);
    }

}
function removeRow(element) {
    $(element).closest('tr').remove();
}

function deleteGroupSchedule(id) {
    $.post("deleteGroupSchedule", {
        'id': id,
    }).done(function (data) {
        $(this).closest('tr').remove();
    });

}

function newEditUserForm(edit_user_id) {
    $('#edit_user_div_id').slideUp();
    $('#edit_user_div_id').slideDown();
    $('#current_edit_user').val(edit_user_id);
    $.get("getEditUser", {
        'id': edit_user_id
    }).done(function (data) {

        $('#current_edit_user_uid').text(data.uid);
        $('#current_edit_user_fname').text(data.firstName);
        $('#current_edit_user_lname').text(data.lastName);
        $('#edit_badgeNum_ID').text(data.badgeNum);
        $('#edit_title_ID').text(data.title);
        $('#edit_hireDate_ID').text(data.hireDate);
        $('#edit_promoteDate_ID').text(data.promotionDate);
        $('#edit_trainerid_id').text(data.trainer.id);

    })
}
function cancelEditUser() {


    $('#edit_badgeNum_ID').val("");
    $('#edit_title_ID').val("");
    $('#edit_gender_ID').val("");
    $('#edit_permissionGroup_ID').val("");
    $('#edit_union_ID').val("");
    $('#edit_recruit_ID').val("1");
    $('#edit_contractEmployee_id').val("");
    $('#edit_hireDate_ID').val("");
    $('#edit_promoteDate_ID').val("");
    $('#edit_trainerID_ID').val("");

}
function addEvent() {

    var startTime_id = $('#startTime_ID').val();
    var endTime_id = $('#endTime_ID').val();
    var description = $('#event_description').val();
    var event_type = $('#individualRequestType').val();
    var shift_type = $('#shift_off_ID').val();

    var totalDays = (startTime_id == '' || endTime_id == '') ? 0
        : new Date(startTime_id).getDate() - new Date(endTime_id).getDate();
    $.post("create_Event", {
        'startTime': startTime_id,
        'endTime': endTime_id,
        'shift_type': shift_type,
        'description': description,
        'event_type': event_type,
        'total': totalDays
    }).done(function (data) {

        if (data == "Remain Day is not enough") {
            // $('#staff_management').append(data);
        }
        else {

            var pGId = $('#create_event_permission_group').val();

            if (pGId == 7 || pGId == 8) {
                var markup =
                    "<tr><td><a href=\"javascript:void(0);\" onclick=\"changeToEditEvent(" + data.id + ");\">" + data.id + "</a>" +
                    "</td><td>" + startTime_id +
                    "</td><td>" + endTime_id +
                    "</td><td>" + event_type +
                    "</td></tr>";
                $('#pendinglisttable > tbody').append(markup).hide().slideDown();

            } else {
                var markup =
                    "<tr><td>" + data.id +
                    "</td><td>" + startTime_id +
                    "</td><td>" + endTime_id +
                    "</td><td>" + event_type +
                    "</td><td>approved" +
                    "</td></tr>";
                $('#previouslisttable > tbody').append(markup).hide().slideDown();

            }
            alert("Special event created.");
        }
        //location.reload();
        cancelAddEvent();
    })

}
function editUser() {

    var uid_ID = $('#current_edit_user_uid').text();
    var lastName_ID = $('#current_edit_user_lname').text();
    var firstName_ID = $('#current_edit_user_fname').text();
    var badgeNum_ID = $('#edit_badgeNum_ID').val();
    var title_ID = $('#edit_title_ID').val();
    var gender_ID = $('#edit_gender_ID').val();
    var permissionGroup_ID = $('#edit_permissionGroup_ID').val();
    var union_ID = $('#edit_unio_ID').val();
    var contractEmployee_id = $('#edit_contractEmployee_id').val();
    var hireDate_ID = $('#edit_hireDate_ID').val();
    var promoteDate_ID = $('#edit_promoteDate_ID').val();
    var trainerID_ID = $('#edit_trainerID_ID').val();
    var seniority = (promoteDate_ID == '' || hireDate_ID == '') ? 0
        : new Date(promoteDate_ID).getFullYear() - new Date(hireDate_ID).getFullYear();

    if (uid_ID === "" || lastName_ID === "" || firstName_ID === "" || badgeNum_ID === ""
        || title_ID === "" || gender_ID === "" || permissionGroup_ID === "" || union_ID === ""
        || contractEmployee_id === "" || hireDate_ID === "" || promoteDate_ID === ""
    ) {
        $('#edit_user_div_id').prepend("<p style='color: red'>Please input all values.</p>");
        return;
    }

    $.post("edit_user", {
        'uid': uid_ID,
        'lastName': lastName_ID,
        'firstName': firstName_ID,
        'badgeNum': badgeNum_ID,
        'title': title_ID,
        'gender': gender_ID,
        'seniority': seniority,
        'permissionGroup': permissionGroup_ID,
        'union': union_ID,
        'contractEmployee': contractEmployee_id,
        'hireDate': hireDate_ID,
        'promoteDate': promoteDate_ID,
        'trainer': trainerID_ID
    }).done(function (data) {

        if (data == "User does not exist.") {
            $('#edit_user_div_id').prepend("<p style='color: red'>User does not exist.</p>");
        }

        $('#edit_user_div_id').slideUp();

        alert("User updated. When re-login you could see the change.");

        cancelEditUser();
    })

}

function assignHoliday() {
    var unionid = $('#current_assign_union_id').val();
    var selectedHoliday = [];
    $("#assign_holiday_list input:checked").each(function () {
        selectedHoliday.push($(this).val());
    });
    $.post("assignHoliday", {
        'unionId': unionid,
        'selectedHoliday[]': selectedHoliday
    }).done(function (data) {
        $('#assign_holiday_union_div_id').slideToggle;
        cancelAssignHoliday();
        document.getElementById("assign_union_id_div").innerHTML = "";
    });
}
function updateEvent() {

    var editRequest_id = $('#currentEditId').val();
    var startTime_id = $('#edit_StartTime_ID').val();
    var endTime_id = $('#edit_EndTime_ID').val();
    var description = $('#edit_event_description').val();
    var event_type = $('#selected_request_type').val();
    var event_status = $('#editEventStatus').val() === undefined ? 'pending' : $('#editEventStatus').val();
    var totalDays = new Date(startTime_id).getDate() - new Date(endTime_id).getDate();

    $.post("update_Event", {
        'edit_id': editRequest_id,
        'startTime': startTime_id,
        'endTime': endTime_id,
        'description': description,
        'event_type': event_type,
        'event_status': event_status,
        'total': totalDays
    }).done(function (data) {

        var pGId = $('#create_event_permission_group').val();

        if (data == "Remain Day is not enough") {
        }
        else {

            if (pGId <= 6) {

                alert("Request processed.");
                var markup =
                    "<tr><td>" + data.id +
                    "</td><td>" + startTime_id +
                    "</td><td>" + endTime_id +
                    "</td><td>" + event_type +
                    "</td></tr>";
                $('#pendinglisttable > tbody').append(markup).hide().slideDown();

            } else {

                alert("Request updated.");

            }
        }

        alert("Event updated. When re-login you could see the change.");

        changeToRequest();
    });

}

function cancelAddUser() {
    $('#uid_ID').val("");
    $('#lastName_ID').val("");
    $('#firstNam_ID').val("");
    $('#badgeNum_ID').val("");
    $('#title_ID').val("");
    $('#gender_ID').val("");
    $('#permissionGroup_ID').val("");
    $('#union_ID').val("");
    $('#contractEmployee_id').val("");
    $('#hireDate_ID').val("");
    $('#promoteDate_ID').val("");
    $('#trainerID_ID').val("");
}

function cancelShiftType() {
    $('#shiftTypeName_ID').val("");
    $('#startTimeShift_ID').val("");
    $('#endTimeShift_ID').val("");
    $('#notes_ID').val("");
}
function clearGroupSchedule() {
    $('input:checkbox').prop("checked", false);
    $('#start_time_group').val("");
    $('#end_time_group').val("");
    $('#group_schedule_description').val("");
    $('#shift_type').val("");
    $('#edit_group_message p').text("");
    $('#group_message p').text("");
}
function cancelAddEvent() {
    $('#startTime_ID').val("");
    $('#endTime_ID').val("");
    $('#event_description').val("");
    $('#individualRequestType').val("");
    changeToRequest();
}
function cancelAssignHoliday() {
    $('input:checkbox').prop("checked", false);
}

function deactivate_time_cycle(timeCycleId) {

    $.get('/deactivate_event', {'timeCycleId': timeCycleId})
        .done(function () {

            alert("Time Cycle Deactivated");

            //location.reload();

        });

}

function remove_shift_type(shiftTypeId) {
    $.get('/remove_shift_type', {'shiftTypeId': shiftTypeId})
        .done(function () {

            alert("Shift Type Removed");

            var shiftType = "shiftType" + shiftTypeId;
            document.getElementById(shiftType).classList.toggle("hide");
            // window.location.reload();

        });
}

function configure_time_cycle() {

    $('#configure_time_cycle_div').slideToggle();

}

function add_time_cycle() {

    var startDate = $('#startDate').val();
    var endDate = $('#endDate').val();

    $.post('/add_time_cycle', {"startDate": startDate, "endDate": endDate})
        .done(function () {

            alert("Time Cycle Configured Successfully.");

            //location.reload();
        });


}

function updateEventByShift() {
    var shift = $("#shiftTypeMenu option:selected").text();
    var shiftId = $("#shiftTypeMenu option:selected").attr('id');

    var previousShiftId = $('#previousShiftId').val();
    var previousUrl = '/allShiftTypeEvent?shiftType=' + previousShiftId;
    var eventsUrl = '/allShiftTypeEvent?shiftType=' + shiftId;

    $('#group_calendar').fullCalendar('removeEventSource', previousUrl);
    $('#group_calendar').fullCalendar('addEventSource', eventsUrl);

    $('#group_calendar').fullCalendar('refetchEvents');
    var previousShiftId = $('#previousShiftId').val(shiftId);
    var shiftType = $('#currentShiftType').val(shiftId);
}

$(document)
    .ready(function () {
        var start_times = [];
        var end_times = [];
        $("#select_all input").change(function () {  //"select all" change
            $(".checkbox").prop('checked', $(this).prop("checked")); //change all ".checkbox" checked status
        });

//".checkbox" change
        $('#group_officers .checkbox').change(function () {
            //uncheck "select all", if one of the listed checkbox item is unchecked
            if (false == $(this).prop("checked")) { //if this item is unchecked
                $("#select_all input").prop('checked', false); //change "select all" checked status to false
            }
            //check "select all" if all checkbox items are checked
            if ($('#group_officers  .checkbox:checked').length == $('.checkbox').length) {
                $("#select_all input").prop('checked', true);
            }
        });
        $("#edit_select_all input").change(function () {  //"select all" change
            $("#edit_group_officers .checkbox").prop('checked', $(this).prop("checked")); //change all ".checkbox" checked status
        });

//".checkbox" change
        $('#edit_group_officers .checkbox').change(function () {
            //uncheck "select all", if one of the listed checkbox item is unchecked
            if (false == $(this).prop("checked")) { //if this item is unchecked
                $("#edit_select_all input").prop('checked', false); //change "select all" checked status to false
            }
            //check "select all" if all checkbox items are checked
            if ($('#edit_group_officers .checkbox:checked').length == $('.checkbox').length) {
                $("#edit_select_all input").prop('checked', true);
            }
        });
        $('#add_group_time').click(function () {
            var start = $('#start_time_group').val();
            var end = $('#end_time_group').val();
            if (start != '' && end != '') {
                start_times.push($('#start_time_group').val());
                end_times.push($('#end_time_group').val());
                var markup = "<div class='col-sm-3'><mark>" + $('#start_time_group').val() + "-" + $('#end_time_group').val() + "</mark></div>";
                $('#selected-time-ranges').append(markup).hide().slideDown();
                $('#start_time_group').val('');
                $('#end_time_group').val('');
            }
        });
        $('#re_submit_group_event').click(updateGroupSchedule);
        $('#re_cancel_group_event').click(changeToCreateGroupRequest);
        $('#submit_group_event').click(function () {
            addGroupEvent(start_times, end_times);
            var error = $('#group_message').text();
            if (!error) {
                start_times = [];
                end_times = [];
            }
        });
        $('#cancel_group_event').click(function () {
            clearGroupSchedule();
            start_times = [];
            end_times = [];
        });
        $('#submit_button').click(addUser);
        $('#cancel_button').click(cancelAddUser);
        $('#submit_Event').click(addEvent);
        $('#cancel_Event').click(cancelAddEvent);
        $('#submit_Edit_Event').click(updateEvent);
        $('#cancel_Edit_Event').click(changeToRequest);
        $('#update_holiday_button').click(updateHolidayDate);
        $('#submit_holiday_assign').click(assignHoliday);
        $('#cancel_holiday_assign').click(cancelAssignHoliday);
        $('#submit_shift_type').click(addShiftType);
        $('#cancel_shift_type').click(cancelShiftType);
        $('#submit_edit_user_button').click(editUser);
        $('#cancel_edit_user_button').click(cancelEditUser);
        var union_ID = $('#currentUnionId').val();

        var eventsUrl = '/allShiftTypeEvent?shiftType=' + 1;
        var unionEventsUrl = '/allUnionEvent?union_id=' + union_ID;
        var holidaysUrl = '/allHoliday?union_id=' + union_ID;

        $('#group_calendar').fullCalendar({

            header: {
                left: 'prev,next today invertButton getOffNumberButton',
                center: 'title',
                right: 'month,basicDay'
            },
            eventSources: [
                {
                    url: holidaysUrl, // use the `url` property
                    color: 'orange',    // an option!
                    textColor: 'black',  // an option!
                    allDayDefault: true
                },
                {
                    url: eventsUrl, // use the `url` property
                }

            ],
            navLinks: true, // can click day/week names to navigate views
            editable: true,
            eventLimit: true, // allow "more" link when too many events
            selectable: true,
            color: '#378006',
            defaultView: 'month',
            dayClick: function (date, jsEvent, view) {
                var shiftType = $('#currentShiftType').val();
                var date1 = new Date(date);

                getOffNumbers(date1, union_ID, shiftType);

            }

        })

        $('#calendar').fullCalendar({

            header: {
                left: 'prev,next today ',
                center: 'title',
                right: 'month,basicDay'
            },
            navLinks: true, // can click day/week names to navigate views
            editable: true,
            eventLimit: true, // allow "more" link when too many events
            selectable: true,
            eventSources: [
                {
                    url: unionEventsUrl, // use the `url` property
                    color: 'orange',    // an option!
                    textColor: 'black'  // an option!
                },
                {
                    url: holidaysUrl, // use the `url` property
                    color: '#F0FFFF',    // an option!
                    textColor: 'black',  // an option!
                    allDayDefault: true
                }

            ],
            defaultView: 'month',
            fixedWeekCount: 6

        })

        function getOffNumbers(date, union_ID, shiftType) {
            $.get("getOfficerNumber", {
                'date': date,
                'union_id': union_ID,
                'shiftType': shiftType
            }).done(function (data) {
                alert(data);
            })
        }

        function getCookie(name) {
            var cookieValue = null;
            if (document.cookie && document.cookie != '') {
                var cookies = document.cookie.split(';');
                for (var i = 0; i < cookies.length; i++) {
                    var cookie = jQuery.trim(cookies[i]);
                    if (cookie.substring(0, name.length + 1) == (name + '=')) {
                        cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                        break;
                    }
                }
            }
            return cookieValue;
        }

        var csrftoken = getCookie('csrftoken');
        $.ajaxSetup({
            beforeSend: function (xhr, settings) {
                xhr.setRequestHeader("X-CSRFToken", csrftoken);
            }
        });
    });
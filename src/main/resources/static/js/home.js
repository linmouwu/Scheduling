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

    $.get("getEditEvent", {
        'id': editRequest_id
    }).done(function (data) {

        var formattedStart = getFormattedDate(new Date(data.startTime));

        var formattedEnd = getFormattedDate(new Date(data.endTime));

        $('#edit_StartTime_ID').val(formattedStart);
        $('#edit_EndTime_ID').val(formattedEnd);
        $('#edit_event_description').val(data.description);
        $('#currentEditId').val(data.id);

    })
}
function updateGroupSchedule(){
    var editRequest_id = $('#edit_id').val();
    // var recruit_ID = $('#recrui_ID').val();
    var startTime_id = $('#edit_start_time_group').val();
    var endTime_id = $('#edit_end_time_group').val();
    var description = $('#edit_group_schedule_description').val();
    var event_type = $('#edit_shift_type').val();
    var selected_officers = []
    console.log(1);
    $("#edit_group_officers input:checked").each(function() {
        selected_officers.push($(this).val());
        console.log(selected_officers);

    });
    $.post("update_group_schedule", {
        'scheduleId': editRequest_id,
        'startTime': startTime_id,
        'endTime': endTime_id,
        'description': description,
        'shiftType': event_type,
        'selectedOfficers[]': selected_officers
    }).done(function (data) {

        console.log(data);
        console.log("guess what happened");
        location.reload();
        changeToCreateGroupRequest();

    });
}
function changeToEditGroupSchedule(schedule_id) {
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

    console.log(1);
    $.get("getEditSchedule", {
        'id': schedule_id
    }).done(function (data) {

        var formattedStart = getFormattedDate(new Date(data.startTime));
        console.log(formattedStart);
        var formattedEnd = getFormattedDate(new Date(data.endTime));
        $('#edit_id').val(schedule_id);
        $('#edit_shift_type').val(data.shiftType);
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

function addUser() {

    var uid_ID = $('#uid_ID').val();
    var lastName_ID = $('#lastName_ID').val();
    var firstName_ID = $('#firstName_ID').val();
    var badgeNum_ID = $('#badgeNum_ID').val();
    var title_ID = $('#title_ID').val();
    var gender_ID = $('#gender_ID').val();
    var permissionGroup_ID = $('#permissionGroup_ID').val();
    var union_ID = $('#unio_ID').val();
    var recruit_ID = $('#recrui_ID').val();
    var contractEmployee_id = $('#contractEmployee_id').val();
    var hireDate_ID = $('#hireDate_ID').val();
    var promoteDate_ID = $('#promoteDate_ID').val();
    var trainerID_ID = $('#trainerID_ID').val();
    console.log(recruit_ID);
    var seniority = (promoteDate_ID == '' || hireDate_ID == '') ? 0
        : new Date(promoteDate_ID).getFullYear() - new Date(hireDate_ID).getFullYear();
    console.log(promoteDate_ID);
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
        'recruit': recruit_ID,
        'contractEmployee': contractEmployee_id,
        'hireDate': hireDate_ID,
        'promoteDate': promoteDate_ID,
        'trainer': trainerID_ID
    }).done(function (data) {

        console.log(data);
        console.log("guess what happened");
        if (data == "Already existed") {
            $('#staff_management').append(data);
        }
        else {
            var markup =
                "<tr><td>" + data.uid +
                "</td><td>" + data.badgeNum +
                "</td><td>" + data.lastName +
                "</td><td>" + data.firstName +
                "</td><td>" + data.title +
                "</td><td>" + data.gender +
                "</td><td>" + data.seniority +
                "</td><td>" + data.permissionGroup +
                "</td><td>" + data.union.name +
                "</td><td>" + hireDate_ID +
                "</td><td>" + promoteDate_ID +
                (data.trainer != null ? "</td><td>" + data.trainer.uid : "</td><td>") +
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
                "<tr><td>" + data.shiftName +
                "</td><td>" + data.startTime +
                "</td><td>" + data.endTime +
                "</td><td>" + data.description +
                "</td><td><button id=\"remove_shift_type\" type=\"button\" onclick='remove_shift_type\(" + data.id
                + "\)' class=\"btn btn-danger\">Remove</button></td></tr>";
            $('#shift_type_list_table > tbody').append(markup).hide().slideDown();
        }

        cancelShiftType();
    })

}

function addGroupEvent() {


    // var recruit_ID = $('#recrui_ID').val();
    var startTime_id = $('#start_time_group').val();
    var endTime_id = $('#end_time_group').val();
    var description = $('#group_schedule_description').val();
    var shift_type = $('#shift_type').val();
    var selected_officers = []
    console.log(1);
    $("#group_officers input:checked").each(function() {
        selected_officers.push($(this).val());
        console.log(selected_officers)

    });

    // var totalDays = (startTime_id == '' || endTime_id == '') ? 0
    //     : new Date(startTime_id).getDate() - new Date(endTime_id).getDate();
    $.post("createGroupEvent", {
        'selectedOfficers[]': selected_officers,
        'startTime': startTime_id,
        'endTime': endTime_id,
        'description': description,
        'shift_type': shift_type,
    }).done(function (data) {

        console.log(data);
        console.log("guess what happened");
        if (data == "Remain Day is not enough") {
            // $('#staff_management').append(data);
        }
        else {
            var markup =
                "<tr><td>" + data.id +
                "</td><td>" + startTime_id +
                "</td><td>" + endTime_id +
                "</td><td>" + shift_type +
                "</td><td>" + selected_officers.length +
                "</td><td>" + description +
                "</td><td>" + data.status +
                "<td><a href='javascript:void(0);' onclick='changeToEditGroupSchedule("+ data.id +")' class='btn btn-xs btn-default'>Edit</a></td></tr>";
            $('#group_schedule_list_table > tbody').append(markup).hide().slideDown();
        }
        //location.reload();
        clearGroupSchedule();
    })


}

function addEvent() {


    // var recruit_ID = $('#recrui_ID').val();
    var startTime_id = $('#startTime_ID').val();
    var endTime_id = $('#endTime_ID').val();
    var description = $('#event_description').val();
    var event_type = $('#individualRequestType').val();
    var seniority = new Date(promoteDate_ID).getFullYear() - new Date(hireDate_ID).getFullYear();
    // console.log(promoteDate_ID);
    var totalDays = (startTime_id == '' || endTime_id == '') ? 0
        : new Date(startTime_id).getDate() - new Date(endTime_id).getDate();
    $.post("create_Event", {
        'startTime': startTime_id,
        'endTime': endTime_id,
        'description': description,
        'event_type': event_type,
        'total': totalDays
    }).done(function (data) {

        console.log(data);
        console.log("guess what happened");
        if (data == "Remain Day is not enough") {
            // $('#staff_management').append(data);
        }
        else {
            var markup =
                "<tr><td>" + data.id +
                "</td><td>" + startTime_id +
                "</td><td>" + endTime_id +
                "</td><td>" + event_type +
                "</td></tr>";
            $('#pendinglisttable > tbody').append(markup).hide().slideDown();
        }
        //location.reload();
        cancelAddEvent();
    })

}
function updateEvent() {

    var editRequest_id = $('#currentEditId').val();
    // var recruit_ID = $('#recrui_ID').val();
    var startTime_id = $('#edit_StartTime_ID').val();
    var endTime_id = $('#edit_EndTime_ID').val();
    var description = $('#edit_event_description').val();
    var event_type = $('#edit_individualRequestType').val();
    var event_status = $('#editEventStatus').val();
    var seniority = new Date(promoteDate_ID).getFullYear() - new Date(hireDate_ID).getFullYear();
    console.log("the id is " + editRequest_id);
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

        console.log(data);
        console.log("guess what happened");
        if (data == "Remain Day is not enough") {
            // $('#staff_management').append(data);
        }
        else {
            var markup =
                "<tr><td>" + data.id +
                "</td><td>" + startTime_id +
                "</td><td>" + endTime_id +
                "</td><td>" + event_type +
                "</td></tr>";
            $('#pendinglisttable > tbody').append(markup).hide().slideDown();
        }
        location.reload();
        cancelAddEvent();
    })

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
    $('#recruit_ID').val("1");
    $('#contractEmployee_id').val("");
    $('#hireDate_ID').val("");
    $('#promoteDate_ID').val("");
    $('#trainerID_ID').val("");
}

function cancelShiftType() {
    $('#shiftTypeName_ID').val("");
    $('#startTime_ID').val("");
    $('#endTime_ID').val("");
    $('#notes_ID').val("");
}
function clearGroupSchedule() {
    $('input:checkbox').prop("checked", false);
    $('#start_time_group').val("");
    $('#end_time_group').val("");
    $('#group_schedule_description').val("");
    $('#shift_type').val("");
    $('#status').val("");
}
function cancelAddEvent() {
    $('#startTime_ID').val("");
    $('#endTime_ID').val("");
    $('#event_description').val("");
    $('#individualRequestType').val("");
}


function deactivate_time_cycle(timeCycleId) {

    $.get('/deactivate_event', {'timeCycleId': timeCycleId})
        .done(function () {

            alert("Time Cycle Deactivated");

            location.reload();
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

            location.reload();
        });


}

// function createGroupSchedule(adminId) {
//
//     var startDate = $('#startTime').val();
//     var endDate = $('#endTime').val();
//     var shiftType = $('#shiftType').val();
//
//     var userList = [];
//
//     $('input[name="users"]:checked').each(function () {
//         userList.push($(this).value());
//     });
//
//     $.post("/add_group_schedule", {
//         "startDate": startDate,
//         "endDate": endDate,
//         "shiftType": shiftType,
//         "userList": userList
//     }).done(function () {
//
//     });
//
// }

$(document)
    .ready(function () {
        $('#re_submit_group_event').click(updateGroupSchedule);
        $('#re_cancel_group_event').click(changeToCreateGroupRequest);
        $('#submit_group_event').click(addGroupEvent);
        $('#cancel_group_event').click(clearGroupSchedule);
        $('#submit_button').click(addUser);
        $('#cancel_button').click(cancelAddUser);
        $('#submit_Event').click(addEvent);
        $('#cancel_Event').click(cancelAddUser);
        $('#submit_shift_type').click(addShiftType);
        $('#cancel_shift_type').click(cancelShiftType);
        $('#submit_Edit_Event').click(updateEvent);
        var union_ID = $('#currentUnionId').val();
        var groupEventsUrl = '/allGroupSchedule';
        var eventsUrl = '/allEvent?union_id=' + union_ID;
        $('#group_calendar').fullCalendar({
            customButtons: {
                invertButton: {
                    text: $('#currentShiftType').val(),
                    id: "123",
                    click: function () {
                        var self = this.innerHTML;
                        //
                        // var shiftType = $('#currentShiftType').val();

                        if (self === "Day") {
                            $('#currentShiftType').val("Night");
                            this.innerHTML = "Night";
                        } else {
                            $('#currentShiftType').val("Day");
                            this.innerHTML = "Day";
                        }
                        console.log($('#currentShiftType').val());
                    }
                },
                getOffNumberButton: {
                    text: "OffNumber",
                    click: function () {
                        3
                        var moment = $('#group_calendar').fullCalendar('getDate');
                        var shiftType = $('#currentShiftType').val();
                        var date = new Date(moment._d),
                            d = date.getDate(),
                            m = date.getMonth(),
                            y = date.getFullYear();
                        getOffNumbers(date, union_ID, shiftType);
                    }
                }
            }, //invert button
            header: {
                left: 'prev,next today invertButton getOffNumberButton',
                center: 'title',
                right: 'basicWeek,basicDay'
            },
            navLinks: true, // can click day/week names to navigate views
            editable: true,
            eventLimit: true, // allow "more" link when too many events
            selectable: true,
            // eventSources: [
            //     eventsUrl
            // ],

            events: [
                {
                    title: 'New Years Day',
                    start: '2017-01-01',
                },
                {
                    title: 'Martin Luther King Jr.Day',
                    start: '2017-02-15',
                },
                {
                    title: 'Presidents Day',
                    start: '2017-02-20',
                },
                {
                    title: 'Good Friday',
                    start: '2017-04-14',
                },
                {
                    title: 'Memorial Day',
                    start: '2017-04-14',
                },
                {
                    title: 'Labor Day',
                    start: '2017-09-04',
                },
                {
                    title: 'Independence Day',
                    start: '2017-07-04',
                },

                {
                    title: 'Veterans Day',
                    start: '2017-11-11',
                },
                {
                    title: 'Christmas Day',
                    start: '2017-12-25',
                }
            ],
            defaultView: 'basicWeek',
            duration: {days: 5}

        })

        $('#calendar').fullCalendar({

            header: {
                left: 'prev,next today ',
                center: 'title',
                right: 'basicWeek,basicDay'
            },
            navLinks: true, // can click day/week names to navigate views
            editable: true,
            eventLimit: true, // allow "more" link when too many events
            selectable: true,
            // eventSources: [
            //     eventsUrl
            // ],

            events: [
                {
                    title: 'New Years Day',
                    start: '2017-01-01',
                },
                {
                    title: 'Martin Luther King Jr.Day',
                    start: '2017-02-15',
                },
                {
                    title: 'Presidents Day',
                    start: '2017-02-20',
                },
                {
                    title: 'Good Friday',
                    start: '2017-04-14',
                },
                {
                    title: 'Memorial Day',
                    start: '2017-04-14',
                },
                {
                    title: 'Labor Day',
                    start: '2017-09-04',
                },
                {
                    title: 'Independence Day',
                    start: '2017-07-04',
                },

                {
                    title: 'Veterans Day',
                    start: '2017-11-11',
                },
                {
                    title: 'Christmas Day',
                    start: '2017-12-25',
                }
            ],
            defaultView: 'basicWeek',
            duration: {days: 5}

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
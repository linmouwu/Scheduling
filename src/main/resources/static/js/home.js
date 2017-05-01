function changeToUserProfile() {
    $('#user_profile').delay(350).fadeIn();
    $('#pending_request').fadeOut();
    $('#calendar').fadeOut();
    $('#group_calendar').fadeOut();
    $('#schedule').fadeOut();
    $('#staff_management').fadeOut();
    $('#editEvent').fadeOut();
    $('#time_cycle_div').fadeOut();
    $('#permission_group').fadeOut();
    $('#holiday_management').fadeOut();
    $('#assign_holiday').fadeOut();
}
function changeToRequest() {
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

}
function changeToCalendar() {
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
}
function changeToGroupCalendar() {
    $('#pending_request').fadeOut();
    $('#calendar').fadeOut();
    $('#group_calendar').delay(350).fadeIn();
    $('#schedule').fadeOut();
    $('#staff_management').fadeOut();
    $('#user_profile').fadeOut();
    $('#editEvent').fadeOut();
    $('#time_cycle_div').fadeOut();
    $('#permission_group').fadeOut();
    $('#holiday_management').fadeOut();
    $('#assign_holiday').fadeOut();
}
function changeToStaff() {
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

}
function newUserForm() {
    $('#add_user_div_id').slideToggle();
}
function newAssignHoliday(id) {
    $('#assign_holiday_union_div_id').slideToggle();
    $('#current_assign_union_id').val(id);
    document.getElementById("assign_union_id_div").innerHTML = "Edit Union: " + id;
}
function changeToCreateRequest() {
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

function changeToTCC() {
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

}

function changeToPGM() {
    $('#time_cycle_div').fadeOut();
    $('#permission_group').delay(350).fadeIn();
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

function changeToEditEvent(editRequest_id) {
    $('#editEvent').delay(350).fadeIn();
    $('#time_cycle_div').fadeOut();
    $('#permission_group').fadeOut();
    $('#user_profile').fadeOut();
    $('#schedule').fadeOut();
    $('#pending_request').fadeOut();
    $('#calendar').fadeOut();
    $('#group_calendar').fadeOut();
    $('#staff_management').fadeOut();
    $('#holiday_management').fadeOut();
    $('#assign_holiday').fadeOut();

    $.get("getEditEvent", {
        'id': editRequest_id,
    }).done(function (data) {

        // console.log(new Date(data.startTime));
        var start = new Date(data.startTime);
        var format = moment(start).format('MM/DD/YYYY');
        console.log(format);
        $('#edit_StartTime_ID').val(format);
        $('#edit_EndTime_ID').val(data.endTime);
        $('#edit_event_description').val(data.description);
        $('#currentEditId').val(data.id);

    })
}

function updateHolidayDate() {
    var newHolidayDate = [];
    var holidayTable = document.getElementById("holiday_list_table");

    for (var i = 1; i < holidayTable.rows.length; i++) {
        var newDate = holidayTable.rows[i].cells[2].getElementsByTagName('input')[0].value;
        if (newDate !== null && newDate !== undefined && newDate !== "") {
            console.log(newDate);
            newHolidayDate.push(newDate);
        } else {
            var tempDate = new Date(holidayTable.rows[i].cells[1].innerHTML);
            var format = moment(tempDate).format('YYYY-MM-DD');
            newHolidayDate.push(format);
            console.log(format);
        }
        // console.log(holidayTable.rows[i].cells[2].getElementsByTagName('input')[0].value);
    }
    $.post("updateHolidayDate", {
        'dateList[]' :newHolidayDate,
    }).done(function (data) {
        var newTable = document.getElementById("holiday_list_table");
        console.log(data);
        for (var i = 1; i < holidayTable.rows.length; i++) {
            var date = new Date(data[i-1].date);
            console.log(data[i-1].date);
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
        location.reload();
        cancelAddEvent();
    })

}
function assignHoliday() {
    var unionid = $('#current_assign_union_id').val();
    var selectedHoliday = [];
    $("#assign_holiday_list input:checked").each(function () {
        selectedHoliday.push($(this).val());
    });
    console.log(selectedHoliday);
    $.post("assignHoliday", {
        'unionId' : unionid,
         'selectedHoliday[]' : selectedHoliday
    }).done(function(data) {
        $('#assign_holiday_union_div_id').slideToggle;
        cancelAssignHoliday();
        document.getElementById("assign_union_id_div").innerHTML = "";
    });
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

function cancelAddEvent() {
    $('#startTime_ID').val("");
    $('#endTime_ID').val("");
    $('#event_description').val("");
    $('#individualRequestType').val("");
}
function cancelAssignHoliday() {
    $('input:checkbox').prop( "checked", false );
}
function updateEventByShift(){
    var shift = $("#shiftTypeMenu option:selected").text();
    var shiftId = $("#shiftTypeMenu option:selected").attr('id');
    // console.log(shiftId);
    var previousShiftId = $('#previousShiftId').val();
    var previousUrl = '/allShiftTypeEvent?shiftType=' + previousShiftId;
    var eventsUrl = '/allShiftTypeEvent?shiftType=' + shiftId;
    console.log(eventsUrl);
    console.log(previousUrl);
    $('#group_calendar').fullCalendar('removeEventSource',previousUrl);
    $('#group_calendar').fullCalendar('addEventSource',eventsUrl);

    $('#group_calendar').fullCalendar('refetchEvents');
    var previousShiftId = $('#previousShiftId').val(shiftId);
    var shiftType = $('#currentShiftType').val(shiftId);
}
$(document)
    .ready(function () {

        $('#submit_button').click(addUser);
        $('#cancel_button').click(cancelAddUser);
        $('#submit_Event').click(addEvent);
        $('#cancel_Event').click(cancelAddUser);
        $('#submit_Edit_Event').click(updateEvent);
        $('#update_holiday_button').click(updateHolidayDate);
        $('#submit_holiday_assign').click(assignHoliday);
        $('#cancel_holiday_assign').click(cancelAssignHoliday);
        var union_ID = $('#currentUnionId').val();

        var eventsUrl = '/allShiftTypeEvent?shiftType=' + 1;
        var unionEventsUrl = '/allUnionEvent?union_id=' + union_ID;
        var holidaysUrl = '/allHoliday?union_id=' + union_ID;
        console.log(holidaysUrl);


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
            dayClick: function(date, jsEvent, view) {
                var shiftType = $('#currentShiftType').val();
                var date1 = new Date(date);
                console.log(date);
                getOffNumbers(date1, union_ID,shiftType);

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
                    url: eventsUrl, // use the `url` property
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
                'union_id':union_ID,
                'shiftType':shiftType
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
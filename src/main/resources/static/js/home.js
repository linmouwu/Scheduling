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
}
function newUserForm() {
    $('#add_user_div_id').slideToggle();
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

$(document)
    .ready(function () {

        $('#submit_button').click(addUser);
        $('#cancel_button').click(cancelAddUser);
        $('#submit_Event').click(addEvent);
        $('#cancel_Event').click(cancelAddUser);
        $('#submit_Edit_Event').click(updateEvent)
        var union_ID = $('#currentUnionId').val();
        var eventsUrl = '/allEvent?union_id=' + union_ID;
        $('#group_calendar').fullCalendar({
            customButtons: {
                invertButton: {
                    text: $('#currentShiftType').val(),
                    id:"123",
                    click: function() {
                        var self = this.innerHTML;
                        //
                        // var shiftType = $('#currentShiftType').val();

                        if (self ==="Day"){
                            $('#currentShiftType').val("Night");
                            this.innerHTML = "Night";
                        } else {
                            $('#currentShiftType').val("Day");
                            this.innerHTML = "Day";
                        }
                        console.log($('#currentShiftType').val());
                    }
                },
                getOffNumberButton:{
                    text:"OffNumber",
                    click:function () {
                        var moment = $('#group_calendar').fullCalendar('getDate');
                        var shiftType = $('#currentShiftType').val();
                        var date = new Date(moment._d),
                            d = date.getDate(),
                            m = date.getMonth(),
                            y = date.getFullYear();
                        getOffNumbers(date, union_ID,shiftType);
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
            duration: { days: 5 }

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
            duration: { days: 5 }

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
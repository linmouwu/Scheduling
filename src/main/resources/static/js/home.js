function getPostList() {
    var list = $("#postList");
    $.get("/grumblr/getPostList")
        .done(function (data) {
            list.data('maxTime', data['maxTime']);
            var length = data.posts.length;
            for (var i = 0; i < length; i++) {
                var post = data.posts[i];
                var newPost = $(post.html);
                newPost.data("postId", post.id);
                list.append(newPost);
                getCommentList(post.id);
            }
        });
}

function updatePostList() {
    var list = $("#postList");
    var maxTime = list.data("maxTime");
    if (maxTime === undefined) {
        maxTime = "1970-01-01T00:00+00:00";
    }
    $.get("/grumblr/updatePostList/" + maxTime)
        .done(function (data) {
            list.data('maxTime', data['maxTime']);
            var length = data.posts.length;
            for (var i = 0; i < length; i++) {
                var post = data.posts[i];
                if (post.deleted) {
                    var postIdToDelete = $("#postId" + post.id);
                    postIdToDelete.fadeOut();
                    postIdToDelete.remove();
                } else {
                    var newPost = $(post.html).hide();
                    newPost.data("postId", post.id);
                    list.prepend(newPost);
                    newPost.slideDown();
                }
            }
        });
}

function addPost() {
    var newPost = $("#newPost");
    var list = $("#postList");
    var maxTime = list.data("maxTime");
    if (maxTime === undefined) {
        maxTime = "1970-01-01T00:00+00:00";
    }
    $.post("/grumblr/post", {post: newPost.val()})
        .done(function (data) {
            newPost.val("").focus();
            list.data('maxTime', data['maxTime']);
            var length = data.posts.length;
            for (var i = 0; i < length; i++) {
                var post = data.posts[i];
                var newPostToAppend = $(post.html).hide();
                newPostToAppend.data("postId", post.id);
                list.prepend(newPostToAppend);
                newPostToAppend.slideDown();
            }
        });
}

function getCommentList(id) {
    var commentList = $('#commentList' + id);
    $.get("/grumblr/getCommentList/" + id)
        .done(function (data) {
            commentList.data('maxTime', data['maxTime']);
            var length = data.myComments.length;
            for (var i = 0; i < length; i++) {
                var comment = data.myComments[i];
                var newComment = $(comment.html);
                newComment.data("commentId", comment.id);
                commentList.append(newComment);
            }
        });
}

function updateCommentList(id) {
    var commentList = $('#commentList' + id);
    var maxTime = commentList.data("maxTime");
    if (maxTime === undefined) {
        maxTime = "1970-01-01T00:00+00:00";
    }
    $.get("/grumblr/updateCommentList/" + id + "/" + maxTime)
        .done(function (data) {
            commentList.data('maxTime', data['maxTime']);
            var length = data.myComments.length;
            for (var i = 0; i < length; i++) {
                var comment = data.myComments[i];
                if (comment.deleted) {
                    var commentIdToDelete = $('#commentId' + comment.id);
                    commentIdToDelete.slideUp();
                    commentIdToDelete.remove();
                } else {
                    var newComment = $(comment.html);
                    newComment.data("commentId", comment.id);
                    commentList.append(newComment);
                    var commentCountA = $('#comments' + id);
                    var commentCount = commentCountA.html().trim().split(" ");
                    var newCount = parseInt(commentCount[0]) + 1;
                    var newText = " " + newCount + " " + commentCount[1];
                    commentCountA.html(newText);

                }
            }
        });
}


function taggleCommentList(id) {
    var commentList = $('#commentList' + id);
    commentList.slideToggle();
}

function addComment(id) {
    var commentList = $('#commentList' + id);
    var newComment = $("#newComment" + id);
    if (newComment.val() == '') {
        return;
    }

    $.post("/grumblr/addComment/" + id, {comment: newComment.val()})
        .done(function (data) {
            var postComment = $('#comments' + id);
            var postCommentCount = postComment.html().trim().split(" ");
            var newCount = parseInt(postCommentCount[0]) + 1;
            var newText = " " + newCount + " " + postCommentCount[1];
            postComment.html(newText);

            newComment.val("").focus();
            commentList.data('maxTime', data['maxTime']);
            var length = data.myComments.length;
            for (var i = 0; i < length; i++) {
                var comment = data.myComments[i];
                var newCommentToAppend = $(comment.html).hide();
                newCommentToAppend.data("commentId", comment.id);
                commentList.append(newCommentToAppend);
                newCommentToAppend.slideDown();
            }
        });
}

function updatePostComment() {
    var postList = $('#postList').children();
    var length = postList.length;
    for (var i = 0; i < length; i++) {
        var currentPost = postList.eq(i);
        var postIdAll = currentPost.attr('id');
        var postId = postIdAll.substr(6, postIdAll.length);
        updateCommentList(postId);
    }
}

function changeToRequest() {
    $('#pending_request').delay(350).fadeIn();
    $('#calendar').fadeOut();
    $('#schedule').fadeOut();
    $('#staff_management').fadeOut();
}
function changeToCalendar() {
    $('#pending_request').fadeOut();
    $('#calendar').delay(350).fadeIn();
    $('#schedule').fadeOut();
}
function changeToStaff() {
    $('#pending_request').fadeOut();
    $('#calendar').fadeOut();
    $('#schedule').fadeOut();
    $('#staff_management').delay(350).fadeIn();
}
function newUserForm() {
    $('#add_user_div_id').slideToggle();
}

function changeToCreateRequest() {
    $('#pending_request').fadeOut();
    $('#calendar').fadeOut();
    $('#schedule').delay(350).fadeIn();
}

function changeToSchedule() {
    $('#schedule').delay(350).fadeIn();
    $('#pending_request').fadeOut();
    $('#staff_management').fadeOut();
}
function addUser() {

    var uid_ID = $('#uid_ID').val();
    var lastName_ID = $('#lastName_ID').val();
    var firstName_ID = $('#firstName_ID').val();
    var badgeNum_ID = $('#badgeNum_ID').val();
    var title_ID = $('#title_ID').val();
    var gender_ID = $('#gender_ID').val();
    var permissionGroup_ID = $('#permissionGroup_ID').val();
    var union_ID = $('#union_ID').val();
    var recruit_ID = $('#recruit_ID').val();
    var contractEmployee_id = $('#contractEmployee_id').val();
    var hireDate_ID = $('#hireDate_ID').val();
    var promoteDate_ID = $('#promoteDate_ID').val();
    var trainerID_ID = $('#trainerID_ID').val();

    var seniority = new Date(promoteDate_ID).getFullYear() - new Date(hireDate_ID).getFullYear();

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
        cancelAddUser();
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
    $('#recruit_ID').val("");
    $('#contractEmployee_id').val("");
    $('#hireDate_ID').val("");
    $('#promoteDate_ID').val("");
    $('#trainerID_ID').val("");
}

$(document)
    .ready(function () {

        // getPostList();
        // $("#newPost").focus();
        // $('#submit').click(addPost);
        // window.clearInterval(0);
        // window.clearInterval(1);
        // window.setInterval(updatePostList, 5000);
        // window.setInterval(updatePostComment, 1000);
        // // CSRF set-up copied from Django docs

        $('#submit_button').click(addUser);
        $('#cancel_button').click(cancelAddUser);

        $('#calendar').fullCalendar({
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,basicWeek,basicDay'
            },
            navLinks: true, // can click day/week names to navigate views
            editable: true,
            eventLimit: true, // allow "more" link when too many events
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
                    title: 'Independence Day',
                    start: '2017-07-04',
                },

                {
                    title :'Veterans Day',
                    start: '2017-11-11',
                },
                {
                    title :'Christmas Day',
                    start: '2017-12-25',
                }
            ]
        })

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
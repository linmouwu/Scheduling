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


$(document)
    .ready(function () {

        getPostList();
        $("#newPost").focus();
        $('#submit').click(addPost);
        window.clearInterval(0);
        window.clearInterval(1);
        window.setInterval(updatePostList, 5000);
        window.setInterval(updatePostComment, 1000);
        // CSRF set-up copied from Django docs

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
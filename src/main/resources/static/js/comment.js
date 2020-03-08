/**
 * 提交回复
 */

function replyQuestion() {
    var questionId = $("#question_id").val();
    var commentContent = $("#comment_content").val();
    commentPost(questionId, commentContent, 1);
}

function replyComment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $('#input-' + commentId).val();
    commentPost(commentId, content, 2);
}

function commentPost(targetId, content, type) {
    if (!content) {
        alert("请输入你需要回复的评论！");
        return;
    }
    $.ajax({
        method: "POST",
        contentType: "application/json",
        url: "/comment",
        data: JSON.stringify({parentId: targetId, content: content, type: type})
    }).done(function (response) {
        if (response.code == 200) {
            $("#comment_section").hide();
            window.location.reload();
        } else {
            if (response.code == 2003) {
                var isAccept = confirm("用户位登录,你需要登录吗?");
                if (isAccept) {
                    localStorage.setItem("closeable", true);
                    window.open("https://github.com/login/oauth/authorize?client_id=Iv1.282f9fa17f8e7829&redirect_uri=http://localhost:8089/callback&scope=user&state=1");
                }
            }
            console.log(response.message);
        }
    });
}

/**
 * 二级评论展开
 */

function collapseComment(e) {
    var commentId = e.getAttribute("data");
    var comment = $("#comment-" + commentId);
    var collapse = e.getAttribute("collapse");
    if (collapse) {
        comment.removeClass("in");
        e.removeAttribute("collapse");
        e.classList.remove("active");
        var subCommentContainer = $("#comment-" + commentId);
        subCommentContainer.empty();
    } else {
        $.getJSON("/comment/" + commentId, function (data) {
            console.log(data);
            var subCommentContainer = $("#comment-" + commentId);
            $.each(data.data.reverse(), function (index, comment) {
                var mediaLeftElement = $("<div/>", {
                    "class": "media-left"
                }).append($("<img/>", {
                    "class": "img-circle img-desc",
                    "src": comment.user.avatarUrl
                }));

                var mediaBodyElement = $("<div/>", {
                    "class": "media-body"
                }).append($("<h5/>", {
                    "class": "media-heading header-block title-to",
                    html: comment.user.name
                })).append($("<div/>", {
                    html: comment.content
                })).append($("<div/>", {
                    "class": "menu"
                })).append($("<span/>", {
                    "class": "pull-right",
                    html: moment(comment.gmtCreate).format("YYYY-MM-DD")
                }));

                var mediaElememt = $("<div/>", {
                    "class": "media"
                }).append(mediaLeftElement).append(mediaBodyElement);

                var commentElement = $("<div/>", {
                    "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 content-section",
                }).append(mediaElememt);

                subCommentContainer.prepend(commentElement);


            })
        });

        comment.addClass("in")
        e.setAttribute("collapse", "in");
        e.classList.add("active");
    }
}
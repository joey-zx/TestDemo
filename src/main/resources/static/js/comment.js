function reply() {
    var questionId = $("#question_id").val();
    var commentContent = $("#comment_content").val();

    if (!commentContent)  {
        alert("请输入你需要回复的评论！");
        return;
    }

    $.ajax({
        method: "POST",
        contentType: "application/json",
        url: "/comment",
        data: JSON.stringify({parentId: questionId, content: commentContent, type: 1})
    }).done(function (response) {
        if (response.code == 200) {
            $("#comment_section").hide();
            window.location.reload();
        } else {
            if (response.code == 2003) {
                var isAccept = confirm("用户位登录,你需要登录吗?");
                if(isAccept) {
                    localStorage.setItem("closeable",true);
                    window.open("https://github.com/login/oauth/authorize?client_id=Iv1.282f9fa17f8e7829&redirect_uri=http://localhost:8089/callback&scope=user&state=1");
                }
            }
            console.log(response.message);
        }
    });
}
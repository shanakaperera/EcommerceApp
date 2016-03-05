$(document).ready(function () {
    $("#loginForm").submit(function (event) {
        event.preventDefault();

        var $form = $(this),
                url = $form.attr('action');

        var posting = $.post(url, {user_name: $('#username').val(), password: $('#password').val()});

        posting.done(function (data) {
            if(data.indexOf('Sorry')>=0){
                $('#loginForm .form-group').addClass('has-error');
                
            }else{
                 window.location.reload(true);
                
            }
        });
    });
});



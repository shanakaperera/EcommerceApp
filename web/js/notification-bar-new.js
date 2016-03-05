//header notification bar.......
//this is new...
$(document).ready(function () {
    $.ajax({
        url: "statusBarLoadAction",
        dataType: 'json',
        cache: false,
        success: function (data) {
            $.each(data, function (key, value) {
                $('#status-bar').append(value.d1);
                if (value.d2 !== '0') {
                    $('#badge-id').html(value.d2);
                }
            });
        },
        error: function () {
            alert('error');
        }
    });
});

function func(id) {
    $.ajax({
        url: "notificationViewAction",
        data: {notId: id},
        cache: false,
        success: function (data) {
            $('#notificationInfo').html(data);
            $('#viewNotification').modal('show');

        },
        error: function () {
            alert('error');
        }
    });

}
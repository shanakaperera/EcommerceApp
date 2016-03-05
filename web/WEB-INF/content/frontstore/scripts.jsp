<%-- 
    Document   : scripts
    Created on : Dec 26, 2015, 3:05:30 AM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--============Scroll to Top===================================================-->
<span class="totop"><a href="#"><i class="fa fa-chevron-up"  ></i></a></span>


<!--============Script files=======================================================-->
<script src="js/jquery.js"></script>
<!-- Bootstrap JS -->
<script src="js/bootstrap.min.js"></script> 
<script src="js/owl.carousel.min.js"></script> 
<script src="js/filter.js"></script> 
<!-- Sidebar navigation -->
<script src="js/nav.js"></script> 
<!-- Validator JS -->
<script src="js/validator.min.js"></script>
<!-- Flex slider -->
<script src="js/jquery.flexslider-min.js"></script> 
<!-- Respond JS for IE8 -->
<script src="js/respond.min.js"></script>
<!-- HTML5 Support for IE -->
<script src="js/html5shiv.js"></script>
<script src="js/jquery.autocomplete.js"></script>
<!-- Custom JS -->
<script src="js/custom.js"></script>
<!-- Login JS -->
<script src="js/login.js"></script>

<script type="text/javascript">
    $("[type='number']").keypress(function (evt) {
        evt.preventDefault();
    });

    /* attach a submit handler to the form  register  form*/
    $("#registerFrom").submit(function (event) {
        event.preventDefault();
        var $form = $(this),
                url = $form.attr('action');
        var posting = $.post(url, {r_name: $('#r_name').val(), email: $('#email').val(),
            username1: $('#username1').val(), password2: $('#password2').val()});
        posting.done(function (data) {

            if (data.indexOf('successfully') >= 0) {

                $('#registerSection').html(data);
                $('#registerSection').css("color", "#0dab0b");
                window.location.reload(true);
            } else {
                $('#registerSection').html(data);
                $('#registerSection').css("color", "#b61818");

            }
        });
    });
</script>

<script type="text/javascript">
    $(document).ready(function () {
        var info = [];
        $.ajax({
            url: 'loadProductsExistAction',
            dataType: 'json',
            cache: false,
            success: function (data) {
                console.log(data);
                $.each(data, function (key, value) {
                    var person = {
                        value: value.name,
                        data: value.id
                    };
                    info.push(person);
                });
            },
            error: function () {
                alert('error');
            }
        });
        $('#search1').autocomplete({
            lookup: info,
            onSelect: function (suggestion) {
                // alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
                onSelectAction(suggestion.data);
            }
        });
    });

    function searchBtnPressed() {
        $.ajax({
            url: "mainSearchBarAction",
            data: {pName: $('#search1').val()},
            cache: false,
            success: function (data) {
                $('#indexSec').html(data);
            },
            error: function () {
                alert('error');
            }
        });
    }
</script>


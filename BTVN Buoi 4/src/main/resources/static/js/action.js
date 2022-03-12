
$('document').ready(function(){

    $('table #editBtn').on('click', function(event) {
        event.preventDefault();

        var href = $(this).attr('href')

        $.get(href, function(user){
            $('#idEdit').val(user.id);
            $('#fullNameEdit').val(user.fullName);
            $('#usernameEdit').val(user.username);
            $('#passwordEdit').val(user.password);
            $('#re-passEdit').val(user.password);

            // $('#submitEdit').on('click', function () {
            //     if($('#passwordEdit').val() !== $('#re-passEdit').val()) {
            //         $('#editModal form').on('submit', function (event) {
            //             event.preventDefault();
            //             $('.mess-rePass').innerHTML = "Mật khẩu không khớp"
            //         })
            //     } else {
            //         $('.mess-rePass').innerHTML = ""
            //     }
            //
            //
            // })
        })
    })


    $('table #deleteBtn').on('click', function(event) {
        event.preventDefault();

        var href = $(this).attr('href')

        $('#confirmDelete').attr('href', href);


    })
}) 
function doneFalse() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/job4j_todo/greet',
        dataType: 'json'
    }).done(function (response) {
        let str = '';
        $.each(response, function (key, value) {
            if (value.done === false) {
                str += '<tr> + <th> ' + value.id + '</th>';
                str += '<td>' + '<div class="form-group form-check">' + '<input type="checkbox" class="form-check-input" id="exampleCheck1">'
                    + '<label class="form-check-label" for="exampleCheck1">Check me out</label>' + '</td>'
                    + '<td>' + value.description + '</td>'
                    + '<td>' + value.created + '</td>'
                   /*   + '<td>' + value.done + '</td>'*/
                    + '<td>' + value.rank + '</td>';
                str += '<tr>';
                $('#table tr:last').after(str);
                str = '';
            }

        });

    }).fail(function (response) {
        console.log(response);
        alert('Ошибка');
    });
}
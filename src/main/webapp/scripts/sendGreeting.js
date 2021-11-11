function sendGreeting() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/job4j_todo/greet',
        cache: false,
        data: 'name=' + $('#validationDefault01').val()
            + '&cater=' + $('#validationDefault02').val(),
        dataType: 'json'
    }).done(function (response) {
        let str = '';
        $.each(response, function (key, value) {
          /*  console.log(value.id);
            console.log(value.description);
            console.log(value.created);
            console.log(value.done);
            console.log(value.rank);
            if (value.description !== $('#table th:')

            $('#table tr').each(function() {
                let customerId = $(this).find("td").eq(2).html();
                console.log('ТОч то нашели ' + customerId);
            });*/

            str += '<tr> + <th> ' + value.id + '</th>';
            str += '<td>' + '<div class="form-group form-check">' + '<input type="radio" class="form-check-input" id="checkbox">'
                + '<label class="form-check-label" for="checkbox">Check me out</label>' + '</td>'
                + '<td>' + value.description + '</td>'
                + '<td>' + value.created + '</td>'
                  /* + '<td>' + value.done + '</td>'*/
                + '<td>' + value.rank + '</td>';
            str += '<tr>';
            $('#table tr:last').after(str);
            str = '';
        });
    }).fail(function (response) {
        console.log(response);
        alert('Ошибка');
    });
}
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
            console.log('Предположительно Объект событие ' + value.event.id);
            console.log('Предположительно Объект событие ' + value.event.description);
            console.log('Предположительно Объект событие ' + value.event.created);
            console.log('Предположительно Объект событие ' + value.event.rank);
              console.log(value.id);
              console.log(value.name);
              console.log(value.password);
              console.log(value.email);
              console.log('Предположительно Объект событие ' + value.event.description);

             // alert('Все получилось!');
             // if (value.description !== $('#table th:')

              // $('#table tr').each(function() {
              //     let customerId = $(this).find("td").eq(2).html();
              //     console.log('ТОч то нашели ' + customerId);
              // });

            str += '<tr> + <th> ' + value.event.id + '</th>';
            str += '<td>' + '<div class="form-group form-check">' + '<input type="radio" class="form-check-input" id="checkbox">'
                + '<label class="form-check-label" for="checkbox">Check me out</label>' + '</td>'
                + '<td>' + value.event.done + '</td>'
                + '<td>' + value.event.description + '</td>'
                + '<td>' + value.event.created + '</td>'
                + '<td>' + value.event.rank + '</td>'
            + '<td>' + value.name + '</td>';// передаелать под него
            str += '<tr>';
            $('#table tr:last').after(str);
            str = '';
        });
    }).fail(function (response) {
        console.log(response);
        alert('Ошибка');
    });
}
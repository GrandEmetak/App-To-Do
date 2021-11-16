function add(f) {
    if (validate()) {
        $.ajax({
            type: 'Get',
            url: '//localhost:8080/job4j_todo/todo',
            data:
                'description=' + $('#validationDefault01').val()
                + '&category=' + $('#validationDefault02').val()
                + '&user=' + f ,
            dataType: 'json'
        }).done(function (response) {
            console.log('OBJECT JSON ' + response);
            let str = '';
            $.each(response, function (key, value) {
                // console.log('Предположительно Объект событие ' + value.event.id);
                // console.log('Предположительно Объект событие ' + value.event.description);
                // console.log('Предположительно Объект событие ' + value.event.created);
                // console.log('Предположительно Объект событие ' + value.event.rank);
                console.log(value.id);
                console.log(value.name);
                console.log(value.password);
                console.log(value.email);
                console.log('Предположительно Объект событие ' + value.event.description);

                str += '<tr> + <th> ' + value.event.id + '</th>';
                str += '<td>' + '<div class="form-group form-check">' + '<input type="checkbox" class="form-check-input" id="checkbox">'
                    + '<label class="form-check-label" for="checkbox">Check me out</label>' + '</td>'
                    + '<td>' + value.event.done + '</td>'
                    + '<td>' + value.event.description + '</td>'
                    + '<td>' + value.event.created + '</td>'
                    + '<td>' + value.event.rank + '</td>'
                    + '<td>' + value.name + '</td>';
                str += '<tr>';
                $('#table tr:last').after(str);
                str = '';

            });
        }).fail(function (err) {
            alert(err);
        })
    }
}
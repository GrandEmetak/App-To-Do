function add() {
    if (validate()) {
        $.ajax({
            type: 'Get',
            url: '//localhost:8080/job4j_todo/todo',
            data:
                'description=' + $('#validationDefault01').val() +
                '&category=' + $('#validationDefault02').val(),
            dataType: 'json'
        }).done(function (data) {
            console.log('OBJECT JSON ' + data);
            $('#table tr:last').append('<tr><td>' + $('#validationDefault01').val() + '</td></tr>');
            $('#table tr:last').after('<tr><td>' + $('#validationDefault02').val() + '</td></tr>');
        }).fail(function (err) {
            alert(err);
        })
    }
}
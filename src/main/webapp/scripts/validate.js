function validate() {
    const value1 = $('#validationDefault01').val();
    const value2 = $('#validationDefault02').val();
    if (value1 === '') {
        alert(value1.attr('title'));
        console.log('not equals')
        return false;
    }
    if (value2 === 'Choose...') {
        alert(value2.attr('title'));
        return false;
    }
    return true;
}
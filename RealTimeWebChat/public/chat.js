window.onload = function() {

    var messages = [];
    var socket = io.connect('http://localhost:3700');
    var username = $('#name');
    var field = $('#field');

    socket.on('message', function (data) {
        console.log(data);
        if(data.message) {
            messages.push(data);
            var html = '';
            for(var i=0; i<messages.length; i++) {
                html += messages[i].username + ': ';
                html += messages[i].message + '<br />';
            }
            $('#content').html(html);
        } else {
            console.log("There is a problem:", data);
        }
    });

    $('#send').click(function() {
        if (username.val() == '') {
            alter('Please input your username!');
        } else if (field.val() == '') {
            alter('Please input at least one character!');
        } else {
            socket.emit('send', { message: field.val(), username: username.val() });
        }
    });

}



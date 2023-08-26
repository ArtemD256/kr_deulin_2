$(document).ready(function () {
    pageFsmServiceShow();
});

function pageFsmServiceShow() {
    $.get('/api/fsmService', function (obj) {
        console.log(obj);
        let view = "";
        for(let i=0;i<obj.length;i++) {
            view += '<tr><td>' + obj[i].id + '</td><td>' +obj[i].description + '</td><td>' + obj[i].price + '</td></tr>';
        }


        $('#fsmService').html(view);
    });
}
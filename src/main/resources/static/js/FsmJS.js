$(document).ready(function () {
    pageFsmShow();
});
function pageFsmShow() {
    $.get('/api/fsm', function (obj) {
        console.log(obj);
        let view = '<tr><td>' + obj.statement + '</td>'
            + '<td>' + obj.foamIncluded + '</td>'
            + '<td>' + obj.dryingMachine + '</td>'
            + '<td>' + obj.theWaterOn + '</td>'
            + '<td>' + obj.waxCoating + '</td>'
            + '</tr>';

        $('#fsm').html(view);
    });
};
function turnOn(){
    alert("Машина запускается подождите 2 секунды.");
    setTimeout(pageFsmShow,2200);
    setTimeout(pageFsmShow,3200);
    $.ajax({
        url:'/api/turnOn',
        type: 'POST',
        cache:false
    });
}
function turnOff(){
    alert("Машина выключается, подождите 2 секунды.")
    setTimeout(pageFsmShow,2500)
    $.ajax({
        url:'/api/turnOff',
        type: 'POST',
        cache: false
    });
}
function turnOnlyWater(){
    alert("Запуск режима вода");
    let timeout = 250;
    $.ajax({
        url:'/api/onlyWater',
        type: 'POST',
        cache: false
    });
    setTimeout(pageFsmShow,timeout);
    timeout += 2000;
    setTimeout(pageFsmShow,timeout);
    timeout += 60000
    setTimeout(pageFsmShow,timeout);
}
function turnWaterFoam(){
    alert("Запуск режима вода-пена-вода")
    let time = 250;
    $.ajax({
        url:'/api/waterAndFoam',
        type: 'POST',
        cache: false
    });

    setTimeout(pageFsmShow,time);
    time+=2250;
    setTimeout(pageFsmShow,time);
    time+=60000;
    setTimeout(pageFsmShow,time);
    time+=60000;
    setTimeout(pageFsmShow,time);
    time+=60000;
    setTimeout(pageFsmShow,time)
}

function turnWaterWax(){
    alert("Запуск режима вода-воск-вода")
    let time = 250;
    $.ajax({
        url:'/api/waterAndWax',
        type: 'POST',
        cache: false
    });

    setTimeout(pageFsmShow,time);
    time+=2000;
    setTimeout(pageFsmShow,time);
    time+=60000;
    setTimeout(pageFsmShow,time);
    time+=40000;
    setTimeout(pageFsmShow,time);
    time+=60000;
    setTimeout(pageFsmShow,time);
}
function turnWaterFoamDrying(){
    alert("Запуск режима вода-пена-сушка")
    let timeout = 250;
    $.ajax({
        url:'/api/waterFoamDrying',
        type: 'POST',
        cache: false
    });

    setTimeout(pageFsmShow,timeout);
    timeout += 2000;
    setTimeout(pageFsmShow,timeout);
    timeout += 60000;
    setTimeout(pageFsmShow,timeout);
    timeout += 60000;
    setTimeout(pageFsmShow,timeout);
    timeout += 60000;
    setTimeout(pageFsmShow,timeout);
    timeout += 60000;
    setTimeout(pageFsmShow,timeout);

}
function turnWaterFoamWax(){
    alert("Запуск режима вода-пена-вода-воск-вода")
    let timeout = 250;
    $.ajax({
        url:'/api/waterFoamWax',
        type: 'POST',
        cache: false
    });
    setTimeout(pageFsmShow,timeout)
    timeout+=2000;
    setTimeout(pageFsmShow,timeout);
    timeout+=60000;
    setTimeout(pageFsmShow,timeout);
    timeout+=60000;
    setTimeout(pageFsmShow,timeout);
    timeout+=60000;
    setTimeout(pageFsmShow,timeout);
    timeout+=40000;
    setTimeout(pageFsmShow,timeout);
    timeout+=60000;
    setTimeout(pageFsmShow,timeout);

}

function added(){
    alert("Добавлено")
    $.ajax({
            url:'/api/added',
            type: 'POST',
            cache: false
    });
    setTimeout(pageFsmShow,7100);
}
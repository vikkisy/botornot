function setCounter(count) {
    $('#counter').text(30 - count);
    $("#timerSeconds").show();

}

var lastTime = parseInt(($.cookie('timepassed') || new Date().getTime()), 10);

var count = Math.max(parseInt(($.cookie('mytimeout') || 0), 10) + parseInt((new Date().getTime() - lastTime) / 1000, 10), 0);

setCounter(count);
var interval = setInterval(function () {
    count++;
    setCounter(count);

}, 1000);


var timeout = setTimeout(function () {
	$(document).ready(function() {
		$("#counter").hide();
		$("#timerSeconds").hide();
		$(".modal").fadeIn();
		$(".modal_main").show();
		$(".close").click(function() {
			$(".modal").fadeOut();
			$(".modal_main").fadeOut();
		});
	})
}, 30000 - count * 1000);


window.onbeforeunload = function () {
	if(count > 0 && count < 30){
    $.cookie('mytimeout', count, {
        expires: 7,
        path: '/'
    });
    $.cookie('timepassed', new Date().getTime(), {
        expires: 7,
        path: '/'
    });
	}
	else if(count < 0 || count > 30){
	    document.cookie = 'mytimeout' + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
	    document.cookie = 'timepassed' + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';

	}
};

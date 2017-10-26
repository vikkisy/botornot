var Timer;
var TotalSeconds;

function CreateTimer(TimerID, Time) {
    Timer = document.getElementById(TimerID);
    TotalSeconds = Time;
    UpdateTimer()
    window.setTimeout("Tick()", 1000);
}

function Tick() {
    TotalSeconds -= 1;
    if(TotalSeconds ==-1) {
    		$(document).ready(function() {
    			$(".modal").fadeIn();
    			$(".modal_main").show();
    			$(".close").click(function() {
    				$(".modal").fadeOut();
    				$(".modal_main").fadeOut();
    			});
    		})
    		
    }
    else {
	    UpdateTimer()
	    window.setTimeout("Tick()", 1000);
    }
}

function UpdateTimer() {
    Timer.innerHTML = TotalSeconds;
}
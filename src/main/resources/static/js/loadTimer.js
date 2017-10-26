
function clickBut(){
var ScriptArray = []; 
function getScriptOnce(url, callback) {
    if (ScriptArray.indexOf(url) === -1){
        if (typeof callback === 'function') {
            return $.getScript(url, function(script, textStatus, jqXHR) {
                ScriptArray.push(url);
                callback(script, textStatus, jqXHR);
            });
        } else {
            return $.getScript(url, function(){
                ScriptArray.push(url);
            });
        }
    }
    else{
        return {
            done: function () {
                return {
                    fail: function () {}
                };
            }
        };
    }
}
var jQueryURL = "/js/timer.js";
getScriptOnce(jQueryURL, function(){
});

window.setTimeout(function(){
  getScriptOnce(jQueryURL, function(){
  });
}, 2000);
}
window.onload = function(){
    window.setInterval(function(){
        let now = new Date();
        let clock = document.getElementById("clock");
        clock.innerHTML = now.toLocaleDateString() + " " + now.toLocaleTimeString();
    }, 1000);
};
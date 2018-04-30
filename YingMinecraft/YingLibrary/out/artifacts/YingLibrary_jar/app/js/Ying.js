var YingWebSocket = new WebSocket("ws://169.254.89.140:4141/");

YingWebSocket.onopen = function() {
    console.log("open");
    YingWebSocket.send("Ying");
};

YingWebSocket.onmessage = function(evt) {
    console.log(evt.data)
	var YingAudio = document.getElementById('Ying');
	YingAudio.src = evt.data;
	YingAudio.play();
};

YingWebSocket.onclose = function(evt) {
    console.log("WebSocketClosed!");
};

YingWebSocket.onerror = function(evt) {
    console.log("WebSocketError!");
};


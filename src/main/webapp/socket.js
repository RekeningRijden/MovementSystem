/**
 * Created by Eric on 25-04-16.
 */
//prepare the websocket uri
var wsUri = "ws://movement.s63a.marijn.ws/socket";

//references #output
var output;

//connects the websocket and inits ouput
function init() {
    output = document.getElementById("output");
    websocket = new WebSocket(wsUri);
    websocket.onopen = function(evt){
        onOpen(evt);
    };
    websocket.onclose = function(evt){
        onClose(evt);
    };
    websocket.onmessage = function(evt){
        onMessage(evt);
    };
    websocket.onerror = function(evt){
        onError(evt);
    };
}

//define event handlers
function onOpen(evt) {
    writeToScreen("CONNECTED");
    writeToScreen('<span style="color: green;">RESPONSE: ' + JSON.stringify(wsUri) + '</span>');
}
function onWindowClose(evt){
    websocket.close();
}
function onClose(evt){
    writeToScreen("DISCONNECTED");
}

function onMessage(evt){
    //convert json to javascript object
    var message = JSON.parse(evt.data);
    //write message.text to screen
    writeToScreen('<span style="color: green;">RESPONSE: ' + message.text + '</span>');
}

function onError(event){
    writeToScreen('<span style="color: red;">ERROR:</span> ' + event.data);
}

function doSend(message){
    writeToScreen("SENT: " + message);
    var json = "{text:'" + message + "'}";
    websocket.send(json);
}

//appends text to #output
function writeToScreen(text) {
    var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = text;
    output.appendChild(pre);
}

//invoke init() on load
window.addEventListener("load", init, false);

//handles [Enter] in #textforws/clicks on #sendButton
function keyPressed(event){
    if(event.keyCode === 13){
        document.getElementById("sendButton").click();
        document.getElementById("textforws").value='';
    }
}
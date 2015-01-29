/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var wsocket;
var serviceLocation = "ws://" + document.location.host + "/OnlineDoctorWebApp/chat/";
var $nickName;
var $message;
var $chatWindow;
var room = '';
function onMessageReceived(evt) {
    //var msg = eval('(' + evt.data + ')');
    alert("Msg received")
    var msg = JSON.parse(evt.data); // native API
    alert("Msg parsed");
    var $messageLine = '' + msg.received
            + ' _' + msg.sender
            + ' _' + msg.message
            + '';

    alert(msg.received + " "+ msg.sender +" "+msg.message);
    $chatWindow.append("\n"+$messageLine);
}
function sendMessage(evt) {
    var msg = '{"message":"' + $message.val() + '", "sender":"'
             + '", "received":""}';
    alert(msg);
    wsocket.send(msg);
    $message.val('').focus();
}

function connectToChatserver() {
//room = $('#nickname').val();
    alert("connectToChatserver");
    room = "channel";
    wsocket = new WebSocket(serviceLocation + room);
    wsocket.onmessage = onMessageReceived;
    return true;
}

function leaveRoom() {
    wsocket.close();
    $chatWindow.empty();
    $('.chat-wrapper').hide();
    $('.chat-signin').show();
    $nickName.focus();
}

$(document).ready(function () {
//    $nickName = $('#nickname');
    $message = $('#message');
    $chatWindow = $('#response');
    $('.chat-wrapper').hide();
//    $nickName.focus();
//    $('#chat-start').click(function (evt) {
//        evt.preventDefault();
//        alert("clicked");
//        connectToChatserver();
//        $('.chat-wrapper h2').text('Chat  @ channel' );
//        $('.chat-signin').hide();
//        $('.chat-wrapper').show();
//        $message.focus();
//    });
//    $('#do-chat').submit(function (evt) {
//        evt.preventDefault();
//        sendMessage()
//    });
//
//    $('#leave-room').click(function () {
//        leaveRoom();
//    });
});

function startChat(evt) {
    alert("clicked");
//        evt.preventDefault();

    if (connectToChatserver()) {
        document.getElementById("leave-chat").setAttribute("hidden",false);
        alert(document.getElementById("leave-chat").getAttribute("hidden"));
        $('.chat-wrapper h2').text('Chat  @ channel');
        $('#chat-start').hide();
        $('#leave-chat').show();
        $message.focus();
    } else {
        alert("couldn't connect to OnlineDoctor Chat server!");
    }
}

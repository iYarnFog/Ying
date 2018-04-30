var ws;

$(document).ready(function() {
	WSCraft();
});

function WSCraft() {

    Log("准备连接到聊天服务器 ...");
    try {
        if ("WebSocket" in window) {
            ws = new WebSocket("ws://zyy.com:6040/user/username/颖");
        } else if ("MozWebSocket" in window) {
            ws = new MozWebSocket("ws://zyy.com:6040/user/username/颖");
        }
    } catch(ex) {
		var data = '{"username":"系统消息","message":"' + base64Encode(ex) + '","color":"red"}';
		var data = JSON.parse(data);
		Chat(data);
	}

    ws.onopen = WSonOpen;
    ws.onmessage = WSonMessage;
    ws.onclose = WSonClose;
    ws.onerror = WSonError;

};

function WSonOpen() {
    var data = '{"username":"系统消息","message":"6JmO54mZ5a6Y5pa55YCh5a+857u/6Imy55u05pKt77yM5a+555u05pKt5YaF5a656L+b6KGMMjTlsI/ml7blnKjnur/lt6Hmn6XjgILku7vkvZXkvKDmkq3ov53ms5XjgIHov53op4TjgIHkvY7kv5fjgIHmmrTlipvnrYnkuI3oia/kv6Hmga/nmoTooYzkuLrlsIbkvJrlsIHlgZzotKblj7fjgII=","color":"#ffffff"}';
	var data = JSON.parse(data);
    Chat(data);
    $("#SendDataContainer").show();
    ws.send('{"username":"系统消息","message":"' + base64Encode(username + '进入了直播间') + '","color":"#ffffff"}');
};

function WSonMessage(event) {
	if(event.data.indexOf("{") <= -1) {
		console.log('已跳过一条聊天信息，跳过原因: 对象可能不是标准的json，跳过内容: ' + event.data);
		return false;
	}
	var data = event.data;
	var data = JSON.parse(data);
	Chat(data);
	if($('.chatlist').children().length > 100) {
		DeleteLi(1);
	}
};

function WSonError() {
	var data = '{"username":"系统消息","message":"6L+c56iL6L+e5o6l6ZSZ6K+v44CC","color":"red"}';
	var data = JSON.parse(data);
    Chat(data);
	$(".submit").hide();
};

function WSonClose() {
	var data = '{"username":"系统消息","message":"6L+c56iL6L+e5o6l5Lit5pat44CC","color":"red"}';
	var data = JSON.parse(data);
    //Chat(data);
	$(".submit").hide();
};

function SendDataClicked() {
    if (document.getElementById("DataToSend").value.trim() == "") {
		return false
	}
    ws.send('{"username":"' + username + '","message":"' + base64Encode(document.getElementById('DataToSend').value) + '","color":"' + document.getElementById('ColorData').value + '"}');
    document.getElementById("DataToSend").value = "";
	$(".word").text("200");
};

function Log(Text, Color) {
	Text = "<span style='color: " + Color + ";'>" + Text + "</span>";
    //document.getElementById("LogContainer").innerHTML = document.getElementById("LogContainer").innerHTML + Text + "<br />";
    var LogContainer = document.getElementById("LogContainer");
    LogContainer.scrollTop = LogContainer.scrollHeight;
};

function Chat(data) {
	if(data.message.length != 0) {
		//admin-icon admin-icon-0
		var text = '';
		text += '<li>';
		text += '	<span class="msg-hd">';
		text += '		<span class="labels">';
		if(data.username != "系统消息") {
		text += '			<span title="徽章主播：Style`婉妹" class="fans-level fans-level-19">婉萌</span>';
		}
		text += '	</span>';
		text += '	<span class="name-wrap">';
		text += '		<span class="name J_userMenu">' + data.username + '</span>';
		text += '			: ';
		text += '		</span>';
		text += '	</span>';
		text += '	<span class="text-content">' + base64Decode(data.message) + '</span>';
		text += '</li>';
		
		if (data.color != '#ffffff') {
			var leave = 0;
			if (data.color == '#66ff99') {
				leave = 1;
			} else if (data.color == '#33ffcc') {
				leave = 2;
			} else if (data.color == '#ccff66') {
				leave = 3;
			} else if (data.color == '#33ffff') {
				leave = 4;
			} else if (data.color == '#33ccff') {
				leave = 5;
			} else if (data.color == '#ff6699') {
				leave = 6;
			} else if (data.color == '#ff3333') {
				leave = 7;
			} else if (data.color == '#cc0033') {
				leave = 8;
			} else if (data.color == '#ff3366') {
				leave = 9;
			} else {
				leave = 0;
			}
			var text = '';
			text += '<li class="noble-speak-wrap">';
			text += '	<div class="box-noble box-noble-level-' + leave + '">';
			text += '		<span class="msg-hd">';
			text += '			<span class="labels"></span>';
			text += '			<span class="name-wrap">';
			text += '				<span class="name J_userMenu">' + data.username + '</span>';
			text += '					: ';
			text += '			</span>';
			text += '		</span>';
			text += '	<span class="msg" style="color:' + data.color + '">' + base64Decode(data.message) + '</span>';
			text += '	<span class="box-noble-icon"></span>';
			text += '</li>';
		}
		var chatlist = $('.chatlist');
		chatlist.append(text);
		var chatlist = document.getElementById("Chat");
		chatlist.scrollTop = chatlist.scrollHeight;
	} else {
		if(username == data.username || data.username == "CONSOLE") {
			var audio = document.getElementById('Ying'); 
		if(audio.paused) {                 
			audio.play();
		} else {
			audio.pause();
		}
			if(data.action[0] == "music") {
				if(data.action[1] == "play") {
					console.log(data.action[2]);
					console.log("颖:" + data.action[2] != audio.src);
					if(data.action[2] != audio.src) {
						audio.src = data.action[2];
					}
					if(audio.paused) {                 
						audio.play();
					}
					console.log(audio.src);
				}
				if(data.action[1] == "stop") {
					audio.pause();
				}
			}
		}
	}

};

$(document).keyup(function(event) {
	if (event.keyCode == 13) {
		SendDataClicked();
	}
});

$(function() {
//先选出 Input 和 统计字数 dom 节点
	var wordCount = $("#InputContainer"),
	Input = wordCount.find("#DataToSend"),
	word = wordCount.find(".word");
	//调用
	statInputNum(Input,word);
});
/*
* 剩余字数统计
* 注意 最大字数只需要在放数字的节点哪里直接写好即可 如：<var class="word">200</var>
*/
function statInputNum(Input,numItem) {
	var max = numItem.text(),
	curLength;
	Input[0].setAttribute("maxlength", max);
	curLength = Input.val().length;
	numItem.text(max - curLength);
	Input.on('input propertychange', function () {
		numItem.text(max - $(this).val().length);
	});
}


var xmlHttpRequest;
var ws = new WebSocket('ws://localhost:8080/isp2/project/drawwordchain/startbroadcast');

function checkStartRequest() {
    // 開始ボタンのレスポンス処理部分
    if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
        receiveStartResponse();
    }
}

// 返信データの処理
function receiveStartResponse() {
    var response = JSON.parse(xmlHttpRequest.responseText);

    var playerAreaElement = document.getElementById("player_area");

    while (playerAreaElement.lastChild) {
        playerAreaElement.removeChild(playerAreaElement.lastChild);
    }

    for (var i = 0; i < response.playerList.length; i++) {
        var playerElement = document.createElement("span");
        playerAreaElement.appendChild(playerElement);
        playerElement.innerHTML = "player" + (i + 1) + ":" + response.playerList[i];
        playerAreaElement.appendChild(document.createElement("br"));
    }
}

// ページ読み込み時
window.addEventListener("load", function() {

	// 開始ボタンのEventListener
    document.getElementById("start_button").addEventListener("click", function() {
        var url = "start";
        xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.onreadystatechange = checkStartRequest;
        xmlHttpRequest.open("GET", url, true);
        xmlHttpRequest.send(null);
    }, false);

	// 送信ボタンのEventListener
	document.getElementById("send_button").addEventListener("click", function() {
		// 画像->base64データに変換
    var data = canvas.toDataURL("image/jpeg");
    alert(data);


//    var image = new Image();
//    image.src = data;
//		image.onload = function() {
//		 ctx2.drawImage(image, 0, 0);

		// 画像送信の記述なし
	}, false);

	// 以下読み込み時の処理
	ws.onmessage = function(receive) {
		var playerAreaElement =  document.getElementById("player_area");
		playerAreaElement.innerHTML = receive.data;
	};

}, false);

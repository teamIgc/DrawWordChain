var xmlHttpRequest;

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

    var playerElement = document.createElement("span");
    playerAreaElement.appendChild(playerElement);
    playerElement.innerHTML = response.playerName;
    playerAreaElement.appendChild(document.createElement("br"));

    // playerListを返した場合の処理
    // for (var i = 0; i < response.playerList.length; i++) {
    //     var playerElement = document.createElement("span");
    //     playerAreaElement.appendChild(playerElement);
    //     playerElement.innerHTML = "player" + (i + 1) + ":" + response.playerList[i];
    //     playerAreaElement.appendChild(document.createElement("br"));
    // }
}

function sendToStartServlet() {
    var url = "start";
    xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = checkStartRequest;
    xmlHttpRequest.open("GET", url, true);
    xmlHttpRequest.send(null);
}

// ページ読み込み時
window.addEventListener("load", function() {

    sendToStartServlet();

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
        console.log("tests");
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

    var ws = new WebSocket('ws://localhost:8080/isp2/project/drawwordchain/startbroadcast');
    var playerName = "testマン";

    ws.onopen = function() {
        console.log("ctest1");
        ws.send('player :'+ playerName);
    };

	ws.onmessage = function(receive) {
        console.log("ctest2");
		var playerAreaElement =  document.getElementById("player_area");
        console.log(receive.data);
		// playerAreaElement.innerHTML = receive.data;
	};

}, false);

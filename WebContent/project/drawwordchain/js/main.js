var xmlHttpRequest;
var ws;
var myName;

function checkStartRequest() {
    // 開始ボタンのレスポンス処理部分
    if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
        receiveStartResponse();
    }
}

// 返信データの処理
function receiveStartResponse() {
    var response = JSON.parse(xmlHttpRequest.responseText);
    myName = response.playerName;
    if (!myName) {
        alert("プレイヤー名を入力してからアクセスしてください");
        location.replace('index.html');
    }
    sendToStartWebSocket(myName);
}

function sendToStartWebSocket(playerName) {
    ws = new WebSocket('ws://localhost:8080/isp2/project/drawwordchain/startbroadcast');
    ws.onopen = function() {
        ws.send(playerName);
    };

    ws.onmessage = function(receive) {
        var playerList = (receive.data).split(",");

        var playerAreaElement =  document.getElementById("player_area");

        // データの削除
        while (playerAreaElement.lastChild) {
            playerAreaElement.removeChild(playerAreaElement.lastChild);
        }

        // プレイヤーの挿入
        playerList.forEach(function(player) {
            var playerElement = document.createElement("div");
            playerAreaElement.appendChild(playerElement);
            playerElement.innerHTML = player;
        });
	};
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
    }, false);

	// 送信ボタンのEventListener
	document.getElementById("send_button").addEventListener("click", function() {
        //textformに日本語のみが入力されているかの判定
        for(var i=0;i<document.getElementById("word").value.length;i++){
          if(!(document.getElementById("word").value.match(/^[\u3040-\u309F]+$/))){
            alert("平仮名のみで入力してください");
          }
          //未入力を看破できない誰か助けて
          //  else if(document.getElementById("word").value==""){
          //    alert("絵の名前を入力してください");
          //    return;
          // }
        }

        //プレイヤー名の中に絵のタイトルを表示(一時的なもの)
        var playerName = document.getElementById("player_name");
        playerName.innerHTML= document.getElementById("word").value;


        // 画像->base64データに変換
            var data = canvas.toDataURL("image/jpeg");
            // alert(data);

            var img = new Image();
            img.src = data;
            img.width = 250;
            img.height = 250;
            document.getElementById('pict_display').appendChild(img);
            //がめんクリア
            ctx.beginPath();
            ctx.fillStyle = "#f5f5f5";
            ctx.globalAlpha = 1.0;
            ctx.fillRect(0, 0, 500, 500);

            //
            // var image = new Image();
            // image.src = data;
            // image.onload = function() {
            //   ctx2.drawImage(image, 0, 0);
            // }

    		// 画像送信の記述なし
        }, false);

}, false);

// ページ終了時
window.addEventListener("beforeunload", function() {

}, false);

////以下メモ用
// var playerAreaElement = document.getElementById("player_area");
//
// while (playerAreaElement.lastChild) {
//     playerAreaElement.removeChild(playerAreaElement.lastChild);
// }
//
// var playerElement = document.createElement("span");
// playerAreaElement.appendChild(playerElement);
// playerElement.innerHTML = response.playerName;
// playerAreaElement.appendChild(document.createElement("br"));

// playerListを返した場合の処理
// for (var i = 0; i < response.playerList.length; i++) {
//     var playerElement = document.createElement("span");
//     playerAreaElement.appendChild(playerElement);
//     playerElement.innerHTML = "player" + (i + 1) + ":" + response.playerList[i];
//     playerAreaElement.appendChild(document.createElement("br"));
// }

var xmlHttpRequest;
var ws;
var myName;
var LOCATION = "localhost:8080/isp2";
//School: LOCATION = "ecl.info.kindai.ac.jp/16/isp2/warup/servlet/B17/";

function checkStartRequest() {
    // 開始ボタンのレスポンス処理部分
    if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
        receiveStartResponse();
    }
}

// 返信データの処理
function receiveStartResponse() {
    var response = JSON.parse(xmlHttpRequest.responseText);
    myName = response.userName;
    console.log(myName);
    if (!myName) {
        alert("プレイヤー名を入力してからアクセスしてください");
        location.replace('index.html');
    }
    sendToStartWebSocket(myName);
}

function sendToStartWebSocket(userName) {
    ws = new WebSocket('ws://'+ LOCATION +'/project/drawwordchain/startbroadcast');
    ws.onopen = function() {
        ws.send(userName);
    };

    ws.onmessage = function(receive) {
        var userList = (receive.data).split(",");

        var userAreaElement =  document.getElementById("user_area");

        // データの削除
        while (userAreaElement.lastChild) {
            userAreaElement.removeChild(userAreaElement.lastChild);
        }

        // プレイヤーの挿入
        userList.forEach(function(user) {
            var userElement = document.createElement("div");
            userAreaElement.appendChild(userElement);
            userElement.innerHTML = user;
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
        var userName = document.getElementById("user_name");
        userName.innerHTML= document.getElementById("word").value;


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
// var userAreaElement = document.getElementById("user_area");
//
// while (userAreaElement.lastChild) {
//     userAreaElement.removeChild(userAreaElement.lastChild);
// }
//
// var userElement = document.createElement("span");
// userAreaElement.appendChild(userElement);
// userElement.innerHTML = response.userName;
// userAreaElement.appendChild(document.createElement("br"));

// userListを返した場合の処理
// for (var i = 0; i < response.userList.length; i++) {
//     var userElement = document.createElement("span");
//     userAreaElement.appendChild(userElement);
//     userElement.innerHTML = "user" + (i + 1) + ":" + response.userList[i];
//     userAreaElement.appendChild(document.createElement("br"));
// }

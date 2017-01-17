var xmlHttpRequest;
var startws; // ゲームの開始ボタンを押すまでのWebSocket変数
var updatews = null; // ゲームプレイ中のWebSocket変数
var myName; // 自分のuser名
var userList;
var playerFlag; // 書き手かどうかの判別
var startButtonJson;
var WSLOCATION = wsLocationResult;
// School: "ecl.info.kindai.ac.jp/16/isp2/warup/servlet/B17";
// "localhost:8080/isp2";

// 読み込み時に呼び出される
function init() {
    // サーブレットからユーザ名を取得
    sendToStartServlet();

    // 開始ボタンのEventListener
    document.getElementById("start_button").addEventListener("click", function() {
        updatews.send(startButtonJson);
    }, false);

    // 送信ボタンのEventListener
    document.getElementById("send_button").addEventListener("click", function() {
        // textBoxについての設定
            // 絵のタイトル未入力の際の動作
            var drawWord = document.getElementById("word");
            if (!drawWord.value) {
                alert("絵の名前を入力してください");
                return;
            }

            // textformに日本語のみが入力されているかの判定
            if (!(drawWord.value.match(/^[\u3040-\u309F]+$/)) && !drawWord.value.match(/ー/)) {
                alert("平仮名のみで入力してください");
                return;
            }
            var lastWord = drawWord.value.slice(-1);
            if (lastWord.match(/ん/)) {
                alert("勝手にしりとりを終わらせないでください");
                return;
            }


        // 画像->base64データに変換
        var data = canvas.toDataURL("image/jpeg");
        // alert(data);

        // 画像送信用
        // json = {"playerName":"myName","userList":[],"imgName":"絵の名前","img":"絵のデータ"}
        var json = "{\"playerName\": \"" + myName + "\",\"userList\":[],\"imgName\": \"drawWord.value\",\"img\": \"" + data + "\"}";

        // 画像データ送信用のupdatewsを受け取ってから送信する
        updatews.send(json);

        // 画面クリア
        clearCanvas();

    }, false);

}

/* xmlHttpRequestをStartに送る/受取はcheckStartRequest */
function sendToStartServlet() {
    // ここで開始ボタンを無効化
    // <未実装>
    var url = "start";
    xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onreadystatechange = checkStartRequest;
    xmlHttpRequest.open("GET", url, true);
    xmlHttpRequest.send(null);
}

/* xmlHttpRequestの受取/受け取った自分の名前をsendToStartWebSocketに送信 */
function checkStartRequest() {
    // 開始ボタンのレスポンス処理部分
    if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
        // 返信データの処理
        var response = JSON.parse(xmlHttpRequest.responseText);
        myName = response.userName;
        // StartWebSocketに送信
        sendToStartWebSocket(myName);
    }
}

/* startwsで自分の名前を送信/参加者を受取/参加者リストに表示/参加を確認できたらsendToUpdateWebSocketへの接続を行う */
function sendToStartWebSocket(userName) {
    startws = new WebSocket(WSLOCATION+'startbroadcast');

    startws.onopen = function() {
        startws.send(userName);
    };

    startws.onmessage = function(receive) {
        userList = (receive.data).split(",");
        console.log("startwsのonmessage");
        var userAreaElement = document.getElementById("user_name");

        // データの削除
        while (userAreaElement.lastChild) {
            userAreaElement.removeChild(userAreaElement.lastChild);
        }

        // プレイヤーの挿入
        userList.forEach(function(user) {
            console.log("userList: "+user);
            var userElement = document.createElement("div");
            userElement.appendChild(document.createTextNode(user));
            userAreaElement.appendChild(userElement);
        });

        // StartWebSocketにデータを送信
        sendToUpdateWebSocket();
    };
}

/* updatewsで自分の名前と参加者リストを送信/ */
function sendToUpdateWebSocket() {

    if(updatews === null) {
        updatews = new WebSocket(WSLOCATION + 'updatebroadcast');
    }

    /* 接続時にユーザリストと自分の名前をパック */
    updatews.onopen = function() {
        // userListJson = a", "b", "c"
        var userListJson = "";

        for (var i = 0; i < userList.length; i++) {
            userListJson += "\"" + userList[i] + "\"";
            if (i == userList.length - 1) {} else {
                userListJson += ",";
            }
        }

        // json = {"playerName":"myName","userList":["test","test2"],"imgName":"","img":""}
        startButtonJson = "{\"playerName\": \"" + myName + "\",\"userList\":[" + userListJson + "],\"imgName\": \"\",\"img\": \"\"}";

        // ここで開始ボタンを有効化
        // <未実装>
    };

    /* プレイヤー名と最初の文字を受取 */
    updatews.onmessage = function(receive) {
        console.log("updatewsのonmessage");
        // receive.data = {"playerName" : "Name", "firstChar" : "文字"}
        var response = JSON.parse(receive.data);
        //firstCharをplay_displayの最初に表示
        var firstChar = response.firstChar;
        var playerName = response.playerName;

        //初めの文字を表示
        var element = document.createElement('p');
        element.style.cssText = "font-size:100pt;" + "display:table-cell;" + "vertical-align:middle;";
        element.appendChild(document.createTextNode("[" + firstChar + "]"));
        document.getElementById("pict_display").appendChild(element);

        //矢印を表示
        var arrow = document.createElement('p');
        arrow.style.cssText = "display:table-cell;" + "vertical-align:middle;" + "font-size:100px;" + "color:white;";
        var arrowText = document.createTextNode("→");
        arrow.appendChild(arrowText);
        document.getElementById('pict_display').appendChild(arrow);

        //現在のプレイヤー名を表示
        document.getElementById('now_draw_user').innerHTML=playerName;


        // 以下やること
        // 開始ボタンを無効にする
        // playerFlagをtrueにする．


        //startwsを切断
        console.log("startwsの切断");
        startws.close(1000);

        // send_buttonを押したときのみに更新してしまうため，他のプレイヤーが受信できない問題
        // updatews.onmessageの更新/imgデータの受取
        updatews.onmessage = function(receive) {
            console.log("更新後のupdatewsのonmessage");
            var response = JSON.parse(receive.data);
            var imgData = response.img;
            var img = new Image();
            img.src = imgData;
            img.width = 250;
            img.height = 250;

            console.log(response.img);

            var newImg = document.createElement('p');
            newImg.style.cssText = "display:table-cell;" + "vertical-align:middle;" + "border: 1px solid black;";
            newImg.appendChild(img);
            document.getElementById('pict_display').appendChild(newImg);
            //画像と画像の間に矢印
            var arrow = document.createElement('p');
            arrow.style.cssText = "display:table-cell;" + "vertical-align:middle;" + "font-size:100px;" + "color:white;";
            var arrowText = document.createTextNode("→");
            arrow.appendChild(arrowText);
            document.getElementById('pict_display').appendChild(arrow);
        };

        // データを受け取ったときに現在の落書きを削除
        clearCanvas();

    };
}

function clearCanvas() {
    ctx.beginPath();
    ctx.fillStyle = "#f5f5f5";
    ctx.globalAlpha = 1.0;
    ctx.fillRect(0, 0, 500, 500);
}

// ページ読み込み時
window.addEventListener("load", function() {
    init(); // 初期化用メソッド
}, false);

// // ページ終了時
window.addEventListener("beforeunload", function() {
    startws.close(1000);
    updatews.close(1000);
}, false);

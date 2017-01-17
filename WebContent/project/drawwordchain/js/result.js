var xmlHttpRequest;

var json_data2 =
{
  "statement": [
    {"name":"つじの","data":"data:image/jpeg;base64,","word":"初音ミク"},
    {"name":"TND","data":"data:image/jpeg;base64,","word":"車"}
  ],
  "judge":[
    false,
    true
    ]
};

//たぶん使わない
function JsonParse(text){
  return eval("(" + text + ")");
}

  // json_data2 = JSON.stringify(json_data2);
	// var obj = JSON.parse(json_data2);
	// // テスト出力
	// console.log(obj);
  // console.log(obj.statement[2].name);
  // console.log(obj.judge.length);

// なぜか使えない
// function createCanvas(canvasNum,canvasWord){
//   var obj = document.getElementById('gamediv');
//   var canvas = document.createElement('canvas');
//   canvas.id = "canvasNum";    //ID
//   canvas.height = 400;         //サイズ　縦
//   canvas.width = 400;          //サイズ　横
//   obj.appendChild(canvas);
//
//   var c2 = document.getElementById("canvasNum");
//   var ctx2 = c2.getContext("2d");
//   var image = new Image();
//   image.src = canvasWord;
//   image.onload = function() {
//     ctx2.drawImage(image, 0, 0);
//   }
// }


//サーバからのデータ受信の状態をチェック
function checkReadyState(){
  if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200){
    receiveResponse();//返信データの処理
  }
}

window.addEventListener("load", function() {
  var url = "result";
  xmlHttpRequest = new XMLHttpRequest();
  xmlHttpRequest.onreadystatechange = checkReadyState;
  xmlHttpRequest.open("GET", url, true);
  xmlHttpRequest.send(null);
}, false);

// 返信データの処理 66 or 68行目 Jsonのオブジェクト化をparseかevalの違い
function receiveResponse() {
  // var response = JSON.stringify(json_data2); //おそらく不要
  // response = JSON.parse(response);//Jsonをjavascriptのオブジェクトに変換
  // response = JSON.parse(xmlHttpRequest.responseText);
  var response = eval("(" + xmlHttpRequest.responseText + ")");
  for(var i=0; i<response.statement.length; i++){
    CreateText(response.statement[i].name,response.statement[i].word);//描いた人の名前とイラスト名表示
    Base64ToImage(response.statement[i].data);//画像表示
    if(i != response.statement.length-1){
      JudgeToImage(response.judge[i]);//正誤判定
    }
  }
}

//上が使えないため画像を直接埋め込むタイプ
function Base64ToImage(base64img) {
    var img = new Image();
    img.src = base64img;
    var show = document.getElementById('gamediv');
    show.appendChild(img);
}

//正誤判定の画像挿入
function JudgeToImage(hantei){
  var o = document.createElement("img");
  if (hantei) {
    o.src = "img/maru.png";
  }
  else {
    o.src = "img/batu.png";
  }
  document.getElementById("gamediv").appendChild(o);
  document.getElementById("gamediv").appendChild( document.createElement( 'br' ) );
}

//描いた人の名前とイラスト名表示
function CreateText(name,word){
  var t = document.createTextNode("描き手:"+name+"、イラスト名:"+word);
  document.getElementById("gamediv").appendChild(t);
}

// receiveResponse();//テスト用なのであとでコメントアウトしてね

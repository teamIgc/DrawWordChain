var xmlHttpRequest;

var json_data2 = {
  "statement":[
    {"name":"A君",
      "data":"111",
      "word":"りんご"},
    {"name":"B君",
      "data":"222",
      "word":"ぽち"},
    {"name":"C君",
      "data":"333",
      "word":"車"}
  ],
  "judge":[
    false,
    true
    ]
};


var json_text = '{"ary":[0,1,2],"obj":{"a":0,"b":1,"c":2}}';
var json_data = {
  "name":"Penguin Statue",
  "height":20,
  "weight":0.25,
  "price":1500,
  "memo":"かわいいペンギンの置物"
};

function JsonParse(text){
	return eval("(" + text + ")");
}
var obj = JsonParse(json_data);

// テスト出力
console.log(obj);


// var data = JSON.parse(json_data);
// alert(data["name"]);
// alert(data.statement[2].name);


// document.getElementById("can").addEventListener("click", function() {
//     xmlHttpRequest = new XMLHttpRequest();
//     xmlHttpRequest.open("get", "result.json", true);
//     xmlHttpRequest.send();
//     var response = JSON.parse(xmlHttpRequest.responseText);
//     alert(response.item[2].itemName);
// }, false);


    // document.getElementById("can").addEventListener("click", function() {
    //     xmlHttpRequest = new XMLHttpRequest();
    //     xmlHttpRequest.open("get", "result.json", true);
    //     //alert(xmlHttpRequest.responseText);
    //     var response = JSON.parse(xmlHttpRequest.responseText);
    //     alert(data["suffix1"]);
    // }, false);

    document.getElementById('can').addEventListener("click", function() {
     for(var i = 0; i < 10; i++) {

       /* ID=gamedivの要素を取得 */
       var obj = document.getElementById('gamediv');
       /* canvasの要素を生成 */
       var canvas = document.createElement('canvas');
       canvas.id = "gamecanvas";    //ID
       canvas.height = 400;         //サイズ　縦
       canvas.width = 400;          //サイズ　横
       /* gamedivに、新しく作ったgamecanvasを追加 */
       obj.appendChild(canvas);

       var o = document.createElement("img");
       o.id = "gamecanvas";

       if (i % 2 == 0) {
         o.src = "img/maru.png";
       }
       else {
         o.src = "img/batu.png";
       }
       document.getElementById("gamediv").appendChild(o);
     }

     }, false);

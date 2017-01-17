//URLを取得
// http://localhost:8080/isp2/project/drawwordchain/main.html
// ws://localhost:8080/isp2/project/drawwordchain/startbroadcast
var tmp = "ws"+location.href.substr(4);
var wsLocationResult = tmp.substr(0, tmp.length - 9);
console.log(wsLocationResult);
var resultLocacion = location.href.substr(0, tmp.length - 9)+"result.html";

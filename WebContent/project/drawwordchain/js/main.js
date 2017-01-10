var xmlHttpRequest;

function sendSendRequest() {
	var messageElement = document.getElementById("message");

	var url = "send";
	xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = checkSendRequest;
	xmlHttpRequest.open("POST", url, true);
	xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlHttpRequest.send("message=" + messageElement.value);
}

function receiveSendResponse() {
	//var response = eval("(" + xmlHttpRequest.responseText + ")");
}

function checkSendRequest() {
	if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
		receiveSendResponse();
	}
}

function sendUpdateRequest() {
	var url = "update";
	xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = checkUpdateRequest;
	xmlHttpRequest.open("GET", url, true);
	xmlHttpRequest.send(null);
}

function receiveUpdateResponse() {
	//console.log(xmlHttpRequest.responseText);
	//var response = eval("(" + xmlHttpRequest.responseText + ")");
	var response = JSON.parse(xmlHttpRequest.responseText);

	var nameElement = document.getElementById("name");
	nameElement.innerHTML = response.user.name;

	var statementListElement = document.getElementById("statement_list");
	statementListElement.innerHTML = "";
	for(var i = 0; i < response.statementList.length; i++) {
		var statement = response.statementList[i];

		var statementElement = document.createElement("tr");
		statementListElement.appendChild(statementElement);

		var userElement = document.createElement("td");
		statementElement.appendChild(userElement);
		userElement.innerHTML = statement.user.name + ">";

		var messageElement = document.createElement("td");
		statementElement.appendChild(messageElement);
		messageElement.innerHTML = statement.message;
	}
}

function checkUpdateRequest() {
	if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
		receiveUpdateResponse();
	}
}

function checkStartRequest() {
	// 開始ボタンのレスポンス処理部分
	if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
		receiveStartResponse();
	}
}

function receiveStartResponse() {
	var response = JSON.parse(xmlHttpRequest.responseText);

	var playerAreaElement = document.getElementById("player_area");

	while (playerAreaElement.lastChild) {
	  playerAreaElement.removeChild(playerAreaElement.lastChild);
	}

	for(var i = 0; i < response.playerList.length; i++) {
		var playerElement = document.createElement("span");
		playerAreaElement.appendChild(playerElement);
		playerElement.innerHTML = "player"+(i+1)+":"+response.playerList[i];
		playerAreaElement.appendChild(document.createElement("br"));
	}
}

window.addEventListener("load", function() {
	var sendButtonElement = document.getElementById("send_button");

	sendButtonElement.addEventListener("click", function() {
		var url = "start";
		xmlHttpRequest = new XMLHttpRequest();
		xmlHttpRequest.onreadystatechange = checkStartRequest;
		xmlHttpRequest.open("GET", url, true);
		xmlHttpRequest.send(null);
	}, false);

	// sendUpdateRequest();
	//
	// setInterval(sendUpdateRequest, 1000);
}, false);

window.addEventListener('beforeunload', function(event) {
  console.log('I am the 1st one.');
  // これでブラウザバック時にセッションを削除する．
});

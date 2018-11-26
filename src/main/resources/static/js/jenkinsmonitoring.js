//jenkins: http://localhost:8008/job/Masterarbeitssoftware-backend
/*
 * For more information please visit: https://12factor.net/
 * Author: Nicolas Corpancho Villasana
 * 
 * 
 */
$(function init(){
	var url = 'http://localhost:8008/job/'+jenkinsurl+'/lastBuild/api/json';
	makeCorsRequest(url);
});

function setBuildBy() {
	//TODO
	$("#buildby").text("Not implemented")
}
function setBuildNr() {
	//TODO
	//strict separation of config from code
	//check if .properties .yml  or .rb are available
	$("#buildnr").text("Not implemented")
}
function setBranch() {
	//TODO
	$("#branch").text("Not implemented")
}
function setDuration() {
	//TODO
	$("#duration").text("Not implemented")
}
function setEstimatedDuration() {
	//TODO
	$("#estimatedduration").text("Not implemented")
}
function setResult() {
	//TODO
	$("#result").text("Not implemented")
}
function setTimestamp() {
	//TODO
	$("#timestamp").text("Not implemented")
}

//Create the XHR object.
function createCORSRequest(method, url) {
  var xhr = new XMLHttpRequest();
  if ("withCredentials" in xhr) {
    // XHR for Chrome/Firefox/Opera/Safari.
    xhr.open(method, url, true);
  } else if (typeof XDomainRequest != "undefined") {
    // XDomainRequest for IE.
    xhr = new XDomainRequest();
    xhr.open(method, url);
  } else {
    // CORS not supported.
    xhr = null;
  }
  return xhr;
}

// Make the actual CORS request.
function makeCorsRequest(url) {
  // This is a sample server that supports CORS.

  var xhr = createCORSRequest('GET', url);
  if (!xhr) {
	console.log('CORS not supported');
    return;
  }

  // Response handlers.
  xhr.onload = function() {
    var reponseJson = xhr.responseText;
    console.log("respose: ", reponseJson);
  };

  xhr.onerror = function() {
    console.log('Woops, there was an error making the request.');
  };

  xhr.send();
}


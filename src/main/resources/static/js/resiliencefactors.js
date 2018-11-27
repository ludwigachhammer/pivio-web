/*
 * For more information please visit: https://12factor.net/
 * Author: Nicolas Corpancho Villasana
 * 
 * 
 */
$(function init(){
	isHostedInAZDCloud();
	checkRedundancy();
	checkZeroDowntimeDeployment();
	checkRetry();
	checkIsolation();
	checkCaching();
	checkFallback();
	checkLooseCoupling();
});
function isHostedInAZDCloud() {
	if(cloudUrl.includes("azd")){
		$("#azdcloud").text("Yes")
	}else{
		$("#azdcloud").text("No")
	}
	
}
function checkRedundancy() {
	//TODO
	$("#redundancy").text("Not implemented")
}
function checkZeroDowntimeDeployment() {
	//TODO
	//strict separation of config from code
	//check if .properties .yml  or .rb are available
	$("#zerodowntimedeployment").text("Not implemented")
}
function checkRetry() {
	//TODO
	$("#retry").text("Not implemented")
}
function checkIsolation() {
	//TODO
	$("#isolation").text("Not implemented")
}
function checkCaching() {
	//TODO
	$("#caching").text("Not implemented")
}
function checkFallback() {
	//TODO
	$("#fallback").text("Not implemented")
}
function checkLooseCoupling() {
	//TODO
	$("#loosecoupling").text("Not implemented")
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
    var text = xhr.responseText;
    console.log('Response from CORS request to ' + url);
    console.log(text);
  };

  xhr.onerror = function() {
    console.log('Woops, there was an error making the request.');
  };

  xhr.send();
}


/*
 * For more information please visit: https://12factor.net/
 * Author: Nicolas Corpancho Villasana
 * 
 * 
 */
/*<![CDATA[*/
$(function init(){
	var lastJenkinsJob;
	//var name = /*[[${pivio.document.name}]]*/ '';
	var url = 'http://localhost:8008/job/'+jenkinsurl+'/lastBuild/api/json';
	makeCorsRequest(url);
	checkCodebase();
	checkDependencies();
	checkConfiguration();
	checkBackingServices();
	checkBuildReleaseRun();
	checkProcesses();
	checkPortBinding();
	checkConcurrency();
	checkDisposability();
	checkDevProdParity();
	checkLogs();
	checkAdminProcesses();
});
function checkCodebase() {
	//is always yes due to groovyfile in repository
	$("#codebase").text("Yes")
}
function checkDependencies() {
	//TODO
	//https://api.github.com/search/code?q=dependencies+repo:
	$("#dependencies").text("Not implemented")
}
function checkConfiguration() {
	//TODO
	//strict separation of config from code
	//check if .properties .yml  or .rb are available
	//https://api.github.com/search/code?q=dependencies+repo:+filename:.yml
	//https://api.github.com/search/code?q=dependencies+repo:+filename:.yaml
	//https://api.github.com/search/code?q=dependencies+repo:+filename:.properties
	//https://api.github.com/search/code?q=dependencies+repo:+filename:.rb
	$("#configuration").text("Not implemented")
}
function checkBackingServices() {
	//TODO
	//check services of app in CF
	$("#backingservices").text("Not implemented")
}
function checkBuildReleaseRun() {
	//TODO
	//
	$("#buildreleaserun").text("Not implemented")
}
function checkProcesses() {
	//TODO
	//horizontal scaling
	$("#processes").text("Not implemented")
}
function checkPortBinding() {
	//TODO
	//port in config files
	$("#portbinding").text("Not implemented")
}
function checkConcurrency() {
	//TODO
	//check horizontal scaleability
	$("#concurrency").text("Not implemented")
}
function checkDisposability() {
	//TODO
	//
	$("#disposability").text("Not implemented")
}
function checkDevProdParity() {
	//TODO
	//similarity of prod and dev config files
	$("#devprodparity").text("Not implemented")
}
function checkLogs() {
	//TODO
	$("#logs").text("Not implemented")
}
function checkAdminProcesses() {
	//TODO
	$("#adminprocesses").text("Not implemented")
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
    lastJenkinsJob = text;
  };

  xhr.onerror = function() {
    console.log('Woops, there was an error making the request.');
  };

  xhr.send();
}
/*]]>*/

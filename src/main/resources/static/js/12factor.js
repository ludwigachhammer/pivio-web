/*
 * For more information please visit: https://12factor.net/
 * Author: Nicolas Corpancho Villasana
 * 
 * 
 */
/*<![CDATA[*/


$(function init(){
	console.log("12 factor");
	var githubUrl = 'https://www.github.com/Nicocovi/Microservice1';
	var repository = githubUrl.substring(githubUrl.lastIndexOf('/')+1, githubUrl.length);
	var tmpUrl = githubUrl.substring(0, githubUrl.lastIndexOf('/'));
	var user = githubUrl.substring(tmpUrl.lastIndexOf('/')+1, tmpUrl.length);
	var searchUrl = user+'/'+repository;
	console.log(repository);
	console.log(user);
	checkCodebase();
	checkDependencies(searchUrl);
	checkConfiguration(searchUrl);
	checkBackingServices();
	checkBuildReleaseRun();
	checkProcesses();
	checkPortBinding(searchUrl);
	checkConcurrency();
	checkDisposability();
	checkDevProdParity(searchUrl);
	checkLogs();
	checkAdminProcesses();
});
function checkCodebase() {
	//is always yes due to groovyfile in repository
	$("#codebase").text("Yes")
}
function checkDependencies(searchUrl) {
	//TODO
	var dependenciesUrl = 'https://api.github.com/search/code?q=dependencies+repo:'+searchUrl;
	$.ajax({
		url: dependenciesUrl,
		type: "GET",
		success: function(result){
			if(result.items.length > 0){
				var tmpArray = result.items;
				var domElement = "";
				for (i = 0; i < tmpArray.length; i++) {
				    domElement = domElement + tmpArray[i].name + '; ';
				}
				$("#dependencies").text(domElement)
			}
		}
	});
	
}
function checkConfiguration(searchUrl) {
	//strict separation of config from code
	//check if .properties .yml  or .rb are available
	var yml = 'https://api.github.com/search/code?q=repo:'+searchUrl+'+filename:.yml';
	var yaml = 'https://api.github.com/search/code?q=+repo:'+searchUrl+'+filename:.yaml';
	var properties = 'https://api.github.com/search/code?q=repo:'+searchUrl+'+filename:.properties';
	//https://api.github.com/search/code?q=repo:'+searchUrl+'+filename:.rb
	var configArray = [];
	var _this = this;
	getRequestForConfigFiles(yml);
	getRequestForConfigFiles(yaml);
	getRequestForConfigFiles(properties);
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
function checkPortBinding(searchUrl) {
	//port in config files
	var portsUrl = 'https://api.github.com/search/code?q=port+repo:'+searchUrl;
	$.ajax({
		url: portsUrl,
		type: "GET",
		success: function(result){
			if(result.items.length > 0){
				var tmpArray = result.items;
				var domElement = "";
				for (i = 0; i < tmpArray.length; i++) {
				    domElement = domElement + tmpArray[i].name + '; ';
				}
				$("#portbinding").text(domElement);
			}else{
				$("#portbinding").text("No ports are defined in the repository");
			}
		}
	});	
	
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
function checkDevProdParity(searchUrl) {
	//similarity of prod and dev config files
	var prodUrl = 'https://api.github.com/search/code?q=repo:'+searchUrl+'+filename:prod';
	var devUrl = 'https://api.github.com/search/code?q=repo:'+searchUrl+'+filename:dev';
	getRequestForConfigFiles(prodUrl);
	getRequestForConfigFiles(devUrl);
	//
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
    console.log('Response' + text);
    return text;
  };

  xhr.onerror = function() {
    console.log('Woops, there was an error making the request.');
  };

  xhr.send();
}

function getRequestForConfigFiles(URL){
	$.ajax({
		url: URL,
		type: "GET",
		success: function(result){
			if(result.items.length > 0){
				var tmpArray = result.items;
				var domElement = "";
				for (i = 0; i < tmpArray.length; i++) {
				    domElement = domElement + tmpArray[i].name + '; ';
				}
				var text = $("#configuration").text() + domElement;
				$("#configuration").text(text);
			}
		}
	});	
}

function getRequestDevProdParity(URL){
	$.ajax({
		url: URL,
		type: "GET",
		success: function(result){
			if(result.items.length > 0){
				var tmpArray = result.items;
				var domElement = "";
				for (i = 0; i < tmpArray.length; i++) {
				    domElement = domElement + tmpArray[i].name + '; ';
				}
				var text = $("#devprodparity").text() + domElement;
				$("#devprodparity").text(text);
			}else{
				//$("#devprodparity").text(text);
			}
		}
	});	
}
/*]]>*/

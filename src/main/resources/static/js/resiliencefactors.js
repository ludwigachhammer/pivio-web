/*
 * For more information please visit: https://12factor.net/
 * Author: Nicolas Corpancho Villasana
 * 
 * 
 */
$(function init(){
	var githubUrl = 'https://www.github.com/Nicocovi/Microservice1';
	var repository = githubUrl.substring(githubUrl.lastIndexOf('/')+1, githubUrl.length);
	var tmpUrl = githubUrl.substring(0, githubUrl.lastIndexOf('/'));
	var user = githubUrl.substring(tmpUrl.lastIndexOf('/')+1, tmpUrl.length);
	var searchUrl = user+'/'+repository;
	var azdcloud = cloudUrl;
	isHostedInAZDCloud(azdcloud);
	checkRedundancy();
	checkZeroDowntimeDeployment();
	checkRetry(searchUrl);
	checkIsolation();
	//checkCaching(searchUrl);
	checkFallback(searchUrl);
	checkLooseCoupling();
});
function isHostedInAZDCloud(azdcloud) {
	var azdcloud = "http://pivio-web.cfapps.com";
	console.log(azdcloud);
	if(azdcloud.includes("azd")){
		$("#azdcloud").text("Yes")
	}else{
		$("#azdcloud").text("No")
	}
	
}
function checkRedundancy() {
	//TODO
	//Mindestens 2 Instanzen
	$("#redundancy").text("No")
}
function checkZeroDowntimeDeployment() {
	//TODO
	$("#zerodowntimedeployment").text("Not implemented")
}
function checkRetry(searchUrl) {
	//Nutzung von failsafe oder Histrix
	var failsafeUrl = 'https://api.github.com/search/code?q=failsafe+repo:'+searchUrl;
	var histrixUrl = 'https://api.github.com/search/code?q=histrix+repo:'+searchUrl;
	//getHistrixFailsafeRequest(failsafeUrl, "failsafe", "retry");
	//getHistrixFailsafeRequest(histrixUrl, "histrix", "retry");
	$("#retry").text("No failsafe-dependency could be found; No histrix-dependency could be found;")
}
function checkIsolation() {
	//TODO
	$("#isolation").text("Not implemented")
}
function checkCaching(searchUrl) {
	//springboot features - caching
	var cacheUrl = 'https://api.github.com/search/code?q=cach+repo:'+searchUrl;
	$.ajax({
		url: cacheUrl,
		type: "GET",
		success: function(result){
			if(result.items.length > 0){
				var tmpArray = result.items;
				var domElement = "";
				for (i = 0; i < tmpArray.length; i++) {
				    domElement = domElement + tmpArray[i].name + '; ';
				}
				$("#caching").text(domElement);
			}else{
				$("#caching").text("No caching actions found");
			}
		}
	});
}
function checkFallback(searchUrl) {
	//Nutzung von failsafe oder Histrix
	var failsafeUrl = 'https://api.github.com/search/code?q=failsafe+repo:'+searchUrl;
	var histrixUrl = 'https://api.github.com/search/code?q=histrix+repo:'+searchUrl;
	//getHistrixFailsafeRequest(failsafeUrl, "failsafe", "fallback");
	//getHistrixFailsafeRequest(histrixUrl, "histrix", "fallback");
	$("#fallback").text("No failsafe-dependency could be found; No histrix-dependency could be found;")
}
function checkLooseCoupling() {
	//TODO
	$("#loosecoupling").text("Not implemented")
}

function getHistrixFailsafeRequest(URL, dependency, element){
	$.ajax({
		url: URL,
		type: "GET",
		dep: dependency,
		el: element,
		success: function(result){
			if(result.items.length > 0){
				var tmpArray = result.items;
				var domElement = "";
				for (i = 0; i < tmpArray.length; i++) {
				    domElement = domElement + tmpArray[i].name + '; ';
				}
				var text = $("#"+this.el).text() + domElement;
				$("#"+this.el).text(text);
			}else{
				var text = $("#"+this.el).text() + 'No '+this.dep+'-dependency could be found; ';
				$("#"+this.el).text(text);
			}
		}
	});	
}

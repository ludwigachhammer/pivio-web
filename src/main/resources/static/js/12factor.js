/*
 * For more information please visit: https://12factor.net/
 * Author: Nicolas Corpancho Villasana
 * 
 * 
 */
$(function init(){
	var lastJenkinsJob;
	console.log("Checking 12 factors...");
	$.ajax({
	    url: 'http://localhost:8008/job/Masterarbeitssoftware-backend/lastBuild/api/json',
	    dataType: 'json',
	    cache: false,
	    success: function succ(result) {
	        //console.log("result", result);
	    	lastJenkinsJob = result;
	    	console.log(lastJenkinsJob);
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
	    }
	});
});
function checkCodebase() {
	//Groovy script builds from 1 repository
	$("#codebase").text("Yes")
}
function checkDependencies() {
	//TODO
	$("#dependencies").text("Not implemented")
}
function checkConfiguration() {
	//TODO
	//strict separation of config from code
	//check if .properties .yml  or .rb are available
	$("#configuration").text("Not implemented")
}
function checkBackingServices() {
	//TODO
	$("#backingservices").text("Not implemented")
}
function checkBuildReleaseRun() {
	//TODO
	$("#buildreleaserun").text("Not implemented")
}
function checkProcesses() {
	//TODO
	$("#processes").text("Not implemented")
}
function checkPortBinding() {
	//TODO
	$("#portbinding").text("Not implemented")
}
function checkConcurrency() {
	//TODO
	$("#concurrency").text("Not implemented")
}
function checkDisposability() {
	//TODO
	$("#disposability").text("Not implemented")
}
function checkDevProdParity() {
	//TODO
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

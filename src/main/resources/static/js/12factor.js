/*
 * For more information please visit: https://12factor.net/
 * Author: Nicolas Corpancho Villasana
 * 
 * 
 */
$(
	var lastJenkinsJob;
	console.log("Checking 12 factors...");
	$.ajax({
	    url: 'http://localhost:8008/job/Masterarbeitssoftware-backend/lastBuild/api/json',
	    dataType: 'json',
	    cache: false,
	    success: function succ(result) {
	        //console.log("result", result);
	    	lastJenkinsJob(result);
	    	console.log("result");
	    }
	});
);
function checkCodebase() {
	//TODO
	$("#codebase").text("Not implemented")
}
function checkDependencies() {
	//TODO
	return "Not implemented"
}
function checkConfiguration() {
	//TODO
	return "Not implemented"
}
function checkBackingServices() {
	//TODO
	return "Not implemented"
}
function checkBuildReleaseRun() {
	//TODO
	return "Not implemented"
}
function checkProcesses() {
	//TODO
	return "Not implemented"
}
function checkPortBinding() {
	//TODO
	return "Not implemented"
}
function checkConcurrency() {
	//TODO
	return "Not implemented"
}
function checkDisposability() {
	//TODO
	return "Not implemented"
}
function checkDevProdParity() {
	//TODO
	return "Not implemented"
}
function checkLogs() {
	//TODO
	return "Not implemented"
}
function checkAdminProcesses() {
	//TODO
	return "Not implemented"
}

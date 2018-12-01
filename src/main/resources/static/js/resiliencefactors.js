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


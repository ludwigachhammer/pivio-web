/*
 * Author: Nicolas Corpancho Villasana
 * 
 * 
 */
$(function init(){
	var jenkins = 'http://localhost:8008/job/'+jenkinsUrl+'/lastBuild/api/json';
	$.ajax({
		url: jenkins,
		type: "GET",
		success: function(result){
			//setBuildBy(result.executor);
			setBuildNr(result.number);
			setDuration(result.duration);
			setEstimatedDuration(result.estimatedDuration);
			setResult(result.result);
			setTimestamp(result.timestamp);
			setJenkinsUrl(result.url);
		}
	});	
});

function setBuildBy(text) {
	$("#buildby").text(text)
}
function setBuildNr(text) {
	$("#buildnr").text(text)
}
function setDuration(text) {
	$("#duration").text(text)
}
function setEstimatedDuration(text) {
	$("#estimatedduration").text(text)
}
function setResult(text) {
	$("#result").text(text)
}
function setTimestamp(text) {
	$("#timestamp").text(text)
}
function setJenkinsUrl(text) {
	$("#jenkinsurl").text(text)
}


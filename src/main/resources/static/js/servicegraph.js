$(function getRelations(nodes, links) {

	var searchQuery = encodeURI(`{"query":{"match_all": {}}}`),
    	url = `${apiserver_js}/document?query=${searchQuery}&fields=id,status,name,service,domain,subdomain,product`;	
	
	console.log("Test");
	
	$.ajax({
	    url: url,
	    dataType: 'json',
	    cache: false,
	    success: function listRelations(result) {
	        //console.log("result", result);
	        servicegraph(result);
	    }
	});

});

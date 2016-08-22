define('WebRequest2', function() {
  var webRequest = {
    http_post : function(url, callback, data) {
		console.log('this is WebRequest.http_post,url=' + url);
    },
  	http_test : function(url) {
			console.log('this is WebRequest.http_test,url=' + url);
	  }
  }
  return webRequest;
});
define('http/WebRequest', function() {
  var webRequest = {
    http_post : function(url, callback, data, async) {
  	  /**
	  loading();
  	  $.post({
  		url: url,
  		dataType: 'json',
  		data: data,
  		async: async==null?true:async,
  		success: function(msg) {
  			loading('hide');
  			callback(msg);
  		}
  	  });
		**/
		console.log('this is WebRequest.http_post,url=' + url);
    },
  	http_test : function(url) {
			console.log('this is WebRequest.http_test,url=' + url);
	  }
  }
  return webRequest;
});
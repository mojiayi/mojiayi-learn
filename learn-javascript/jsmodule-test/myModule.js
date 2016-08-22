define('myModule', ['WebRequest2'], function (WebRequest) {
        var myModule = {
            doStuff : function(){
              $('#showText').html('Yay! 这是测试文字');
              console.log('Yay! 这是测试文字');
              
            },
            http_post : function(url) {
		          console.log('这是从myModule调用WebRequest.http_post,url=' + url);
              WebRequest.http_post(url);
	          }
        }
 
        return myModule;
  });
require(['myModule', 'http/WebRequest'], 
    function( myModule, webRequest ){
        // start the main module which in-turn
        // loads other modules
        //var module = new myModule();
        //module.doStuff();
        myModule.doStuff();
        myModule.http_post('heihei');
        //console.log(webRequest);
        webRequest.http_post('webRequest-hehehehe');
});
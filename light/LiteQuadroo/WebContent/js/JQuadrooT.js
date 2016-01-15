$(function () {

    $("#flowaccord").accordion({
        collapsible: true,
        active: false
    });

    $("#sceneaccord").accordion({
        collapsible: true,
        active: false
    });
    $("#wordaccord").accordion({
        collapsible: true,
        active: false
    });
    $("#scenewordaccord").accordion({
        collapsible: true,
        active: false
    });
    $("#flowsceneaccord").accordion({
        collapsible: true,
        active: false
    });
    $("#flowsceneprotaccord").accordion({
        collapsible: true,
        active: false
    });
    $("#tabs").tabs();

   //span id="wsel"
    	$("input[name='words']").change(function(){
    		$("#wsel").html($(this).val());
            //alert($(this).val());
        });
        
         $("input[name='uwords']").change(function(){
    		$("#wsel").html($(this).val());
            //alert($(this).val());
        });

    	$("input[name='dwords']").change(function(){
    		$("#wsel").html($(this).val());
            //alert($(this).val());
        });  



});
   
function doAddWord() {
    var data = $('#wt').val();

    url = 'http://localhost:8080/pureQuadroo/quadrooServer/$AWord';
    addQWord(url, data);
}


function doUpdateWord(){
	var data1 = $('#uwt').val() ;
	var data = $('#wsel').html() ;
	
	alert(data);
	url = 'http://localhost:8080/pureQuadroo/quadrooServer/$CWord';
	updateQWord(url,data,data1);
}

function doDeleteWord(){
	var data = $('#wsel').html() ;
	alert("selection" +data);
	url = 'http://localhost:8080/pureQuadroo/quadrooServer/$DWord';
	deleteQWord(url,data);
}
  

function doListWord() {

    alert("doListWord");

    var data = 'dummy';

    url = 'http://localhost:8080/pureQuadroo/quadrooServer/LSAWords';
    //alert("new version");

    ListWord(url, data);
}


function updateQWord(curl,cdata, data1) {
	    // Create our XMLHttpRequest object
	    var hr = new XMLHttpRequest();
	   
	    var url = curl+"?quadroodata={W:"+cdata+",nW:"+data1+"}" ;
	 alert ("data before"+ cdata +"url:"+url); 
	 //   var uurl = '"'+url+'"';
	hr.open("GET",url ,false);
	hr.send(null);
	var docr = hr.responseText;
	alert("Cool Java res:"+docr);
	//var xmlDoc = $.parseXML( docr );
//alert('XML:'+xmlDoc);
//var myArray = getXMLToArray(xmlDoc);

//alert(myArray['Qreply']['reply']);
//$("#reply").html("Reply:"+myArray['Qreply']['reply']);
	var jso = JSON.parse(docr);
    
    var js2 = jso.replystatus;
    var js2o = JSON.stringify(js2);
 /*   var js3 = JSON.parse(js2o);
    //	alert  ("js3"+JSON.stringify(js3));
    var js2o = JSON.stringify(js3);
    var js2oo = JSON.parse(js2o);
    var js3o = js2oo.result;	*/
	
$("#replyuw").html("Reply:"+js2o);
if (js2=="done")
$("#wsel").html(data1);  }
	   

function deleteQWord(curl,cdata) {
	    // Create our XMLHttpRequest object
	    var hr = new XMLHttpRequest();
	   
	    var url = curl+"?quadroodata={W:"+cdata+"}" ;
	 alert ("data before"+ cdata +"url:"+url); 
	 //   var uurl = '"'+url+'"';
	hr.open("GET",url ,false);
	hr.send(null);
	var docr = hr.responseText;
	alert("Cool Java res:"+docr);
	//var xmlDoc = $.parseXML( docr );
//alert('XML:'+xmlDoc);
//var myArray = getXMLToArray(xmlDoc);

//alert(myArray['Qreply']['reply']);
//$("#reply").html("Reply:"+myArray['Qreply']['reply']);
	var jso = JSON.parse(docr);
    
    var js2 = jso.replystatus;
  
$("#replydw").html("Reply:"+js2);
if (js2=="done")
	$("#wsel").html(""); 
	   }
	    



function addQWord(curl, cdata) {


    // Create our XMLHttpRequest object
    var hr = new XMLHttpRequest();

    var url = curl + "?quadroodata={W:" + cdata + "}";
    alert("data before" + cdata + "url:" + url);
    //   var uurl = '"'+url+'"';
    hr.open("GET", url, false);
    hr.send(null);
    var docr = hr.responseText;
  //  alert("Cool Java res:" + docr);
    //var xmlDoc = $.parseXML( docr );
    //alert('XML:'+xmlDoc);
    //var myArray = getXMLToArray(xmlDoc);

    //alert(myArray['Qreply']['reply']);
    //$("#reply").html("Reply:"+myArray['Qreply']['reply']);
   // var docr = hr.responseText;
    var jso = JSON.parse(docr);
     
    var js2 = jso.reply;
    var js2o = JSON.stringify(js2);
    var js3 = JSON.parse(js2o);
    //	alert  ("js3"+JSON.stringify(js3));
    var js2o = JSON.stringify(js3);
    var js2oo = JSON.parse(js2o);
    var js3o = js2oo.result;
    
    $("#wsel").html(cdata);
    $("#reply").html("Reply:" + js3o);
}


function ListWord(curl, cdata) {


    // Create our XMLHttpRequest object
    var hr = new XMLHttpRequest();


    var url = curl + "?quadroodata={W:" + cdata + "}";



    hr.open("GET", url, false);

    //finaly we send and we do it in sync
    hr.send(null);

    var docr = hr.responseText;
    var jso = JSON.parse(docr);
     
    var js2 = jso.reply;
    var js2o = JSON.stringify(js2);
    var js3 = JSON.parse(js2o);
    //	alert  ("js3"+JSON.stringify(js3));
    var js2o = JSON.stringify(js3);
    var js2oo = JSON.parse(js2o);
    var js3o = js2oo.result;
    
    var jslhtm = "<p>Word List </>  ";
    //  var result = $.parseJSON(js3os);
    $.each(js3o, function (k, v) {

        //display the key and value pair
        //  alert(k + ' is v ' + v);
        var sv = JSON.stringify(v);
        var svj = JSON.parse(sv);
        var nn = svj.name;
        var inn = '<input type="radio" name="words" value="' + nn + '">' + nn + '</input>';
        // alert("inn:" + inn);                 

        //  jslhtm = jslhtm+"<li>"+ nn + "</li>";
        jslhtm = jslhtm + inn + " ";
        // alert(k + ' name ' +JSON.stringify(nn));
    });
    //    jslhtm = jslhtm + "</ul>";
    //alert("replt:"+ jslhtm);
    //var listhtm = buildList(docr);




    $("#replylist").html(jslhtm);

    //span id="wsel"
    $("input[name='words']").change(function () {
        $("#wsel").html($(this).val());
        //alert($(this).val());
    });





}
//alert('Selected'+	$('input:radio[name=words]:checked').val());	
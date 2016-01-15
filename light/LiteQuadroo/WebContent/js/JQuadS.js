   
function doAddScene() {
    var data = $('#wts').val();

    url = 'http://localhost:8080/pureQuadroo/quadrooServer/$AScene';
    addQScene(url, data);
}


function doUpdateScene(){
	var data1 = $('#ust').val() ;
	var data = $('#ssel').html() ;
	
	alert(data);
	url = 'http://localhost:8080/pureQuadroo/quadrooServer/$CScene';
	updateQScene(url,data,data1);
}

function doDeleteScene(){
	var data = $('#ssel').html() ;
	alert("selection" +data);
	url = 'http://localhost:8080/pureQuadroo/quadrooServer/$DScene';
	deleteQScene(url,data);
}
  

function doListScene() {

    alert("doListWord");

    var data = 'dummy';

    url = 'http://localhost:8080/pureQuadroo/quadrooServer/$LScene';
    //alert("new version");

    ListQScene(url, data);
}


function updateQScene(curl,cdata, data1) {
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
	
$("#replyus").html("Reply:"+js2o);
if (js2=="done")
$("#wsel").html(data1);  }
	   

function deleteQScene(curl,cdata) {
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
  
$("#replyds").html("Reply:"+js2);
if (js2=="done")
	$("#ssel").html(""); 
	   }
	    



function addQScene(curl, cdata) {


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
    
    $("#ssel").html(cdata);
    $("#replyas").html("Reply:" + js3o);
}


function ListQScene(curl, cdata) {


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
    
    var jslhtm = "<p>Scene List </>  ";
    //  var result = $.parseJSON(js3os);
    $.each(js3o, function (k, v) {

        //display the key and value pair
         
        var sv = JSON.stringify(v);
        var svj = JSON.parse(sv);
        var nn = svj.name;
        var inn = '<input type="radio" name="scenes" value="' + nn + '">' + nn + '</input>';
         
        jslhtm = jslhtm + inn + " ";
         
    });
    



    $("#replylists").html(jslhtm);

   
    $("input[name='scenes']").change(function () {
        $("#lsel").html($(this).val());
        //alert($(this).val());
    });


}


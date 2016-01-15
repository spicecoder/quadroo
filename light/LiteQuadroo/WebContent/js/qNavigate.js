var flowSceneIds = new Array(); var popper = new Array();
var flowScenes = new Array(); 
var fid ="current flow" ;


var attachString = function(so,i){alert ("in the attached to  with" + i );};

var interaction =  function(so) {alert ("in the interaction with" + so );};
 
function flow(aSceneArray,functionOnEach,interaction){ 
	alert ("in flow" + aSceneArray.length);
	
	var num_s = aSceneArray.length;
	for (var i = 0;i<num_s;i++){ 
			functionOnEach(aSceneArray[i],i)	;
	interaction(aSceneArray[i]);
		
	} 
	
}



function doTraverseFlow() {
	alert("doTraverseFlow");

    var data = "Morning To Work";

    url = 'http://localhost:8080/pureQuadroo/quadrooServer/LFlowScenes';
    //alert("new version");

    ListFlowScenes(url, data);	
	
//	navModal(fid);
    flow(flowScenes,attachString,interaction);
	
	
	
} 
//==========================================



function ListFlowScenes(curl, cdata) {


    // Create our XMLHttpRequest object
    var hr = new XMLHttpRequest();


    var url = curl + "?quadroodata={W:" + cdata + "}";



    hr.open("GET", url, false);

    //finaly we send and we do it in sync
    hr.send(null);

    var docr = hr.responseText;
    var jso = JSON.parse(docr);
   
    var js2 = jso.reply;
    var js3oo = js2.result;
   
    var sceneArray = js3oo.scenes; 
    for(var i = 0; i < sceneArray.length; i++) {
    	flowScenes.push(sceneArray[i].scene);
    	flowSceneIds.push(sceneArray[i].sid);
    alert("scene received"+ sceneArray[i].scene);
    

}

}


//===============================
function popScene(pos){ 
$("#ds").html( ' <h1> Scene: '+ flowScenes[pos] +'</h1>') ;
  var sno = flowSceneIds[pos];
 // $("#ds").css(' {background: url("media/sc.png") no-repeat; background-size: 100%;	} ');
//create word list for the scene
$("#ds").append("<h2> Word View In scene </h2>");
$('#ds').append('<div id="display">display <img src="media/sc.png" /> </div>') ;
$("#ds").append("<ul>");

$("#ds").append("</ul>");

 
 alert ("on pos:" + pos+popper[pos]);
 
} 

 
  
 
 
function navi(fid) {
//first start
$("#dialog-modal").empty();
$("#dialog-modal").css('background-color','green');
$("#dialog-modal").css('border','1px solid black');
$("#dialog-modal").append('<DIV id="ds" > SCENE :   </DIV>  ');
$("#dialog-modal").append('<DIV id="scenecontent" > flow:' + fid + '</DIV>  ');
 
 pos=0;
$("#ds").text(flowScenes[0]) ;
popScene(pos);
//alert("ready to click");
$("#dialog-modal").append('<p> <button onclick= "navic()"  >Traverse</button>  </p> '); 



 } 
//navigation scripts
var count = 0;

function navModal(fid) {

//alert("finder"+"#fid"+fid + " .subFScene [lvl='fs']");
//alert("no of scenes"+$("#fid"+fid + " .subFScene [lvl='fs']").length);
//$("#fid"+fid + " .subFScene [lvl='fs']").each(function( index ) {
//alert( index + ": " + $(this).text() );
//});
navi(fid);
//alert("before");
$("#dialog-modal").dialog({
height: 400,
width:500,
modal: true
});
}
function navic(){
 alert ("in navic"+ pos);
pos = pos +1;
if (pos == (flowScenes.length )) { revisit = true; pos = 0; } 
popScene(pos);

 
} 




var xmlhttp


function SendData() { 

    xmlhttp=null;

    var Url="XMLHttpRequest_responseXML.php";           // THE SERVER SCRIPT TO HANDLE THE REQUEST 


  if (window.XMLHttpRequest) {

      xmlhttp=new XMLHttpRequest()                            // For all modern browsers

  } else if (window.ActiveXObject) {

     xmlhttp=new ActiveXObject("Microsoft.XMLHTTP")   // For (older) IE

  }


 if (xmlhttp!=null)  {

     xmlhttp.onreadystatechange=onStateChange;
  
     Url = "http://localhost:8080/silvio/SQLExecutor";
     xmlhttp.open("GET", Url, true);                                                         //  (httpMethod,  URL,  asynchronous)
 
    // xmlhttp.overrideMimeType('text/xml');

     xmlhttp.send(null);


/* 
   // How to send a POST request
    xmlhttp.open("POST", Url, true);                                                         //  (httpMethod,  URL,  asynchronous)

    xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

     xmlhttp.send( "Date=" + escape(Arg) );
*/


  } else {

     alert("The XMLHttpRequest not supported");

  }

}





function  onStateChange()  {


  if (xmlhttp.readyState==4) {                                                                                      // 4 => loaded complete


       if (xmlhttp.status==200) {                                                                                    // HTTP status code  ( 200 => OK )
  
            var xmlDoc=xmlhttp.responseXML                                                                    // "xmlDoc" the returned xml object

            
            // alert(typeof xmlhttp.responseXML)                                                               // Returns: object   
           // alert ("childNodes: "+xmlDoc.childNodes.length)                                             // Just for test

           var DateNode=xmlDoc.getElementsByTagName('Date')[0].firstChild.nodeValue      // The <Date> element's node value  (the sent date)
                                                                                                   

            var Xml2String                                                                                             // Convert the xml to string just to display it 

            if (xmlDoc.xml) {
                Xml2String=xmlDoc.xml                                                                            // Converts the xml object to string  (  For IE)
            }else{
                Xml2String= new XMLSerializer().serializeToString(xmlDoc);                        // Converts the xml object to string (For rest browsers, mozilla, etc)
            }


           var Message= "--------- RESPONSE HEADERS  (NOTE THE CONTENT-TYPE) --------\n\n"

           Message+=xmlhttp.getAllResponseHeaders()
           Message+="\n\n----------------------------------- XML -----------------------------------\n\n"
           Message+= Xml2String
           Message+="\n\n--------------------- DATE (PARSED FROM XML) ----------------------\n\n"
           Message+=DateNode


            alert( Message ) 
       
             // alert( Xml2String )

             //alert (xmlhttp.responseText)          
     
            document.getElementById("CellData").value=Xml2String

      } else {

                alert("statusText: " + xmlhttp.statusText + "\nHTTP status code: " + xmlhttp.status);

      }  // End of:   if (xmlhttp.status==200)

  }

}
//<![CDATA[	

var sqlExecutorAddress = 'http://localhost:8080/sil-vio/SQLExecutor?nomeUtente=silvio&password=**silvio**&nomeDB=ap1fruitore&query=';
var xmlhttp;


function executeSQL(){
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest(); // For all modern browsers
    }else if (window.ActiveXObject) {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); // For (older) IE
    }
    
    if (xmlhttp != null) {			
		document.getElementById('risultato').innerHTML = "<span id=\"ricercaInCorso\">Ricerca in corso...</span>";
		var codiceFiscale = document.getElementById('ricercaCodiceFiscale').value;
		if(codiceFiscale==null || codiceFiscale==""){
			return;
		}
		var indirizzo = sqlExecutorAddress + "SELECT * FROM paziente WHERE codice_fiscale = '" + codiceFiscale + "';"
        xmlhttp.open("GET", indirizzo, true);
        xmlhttp.send(null);
        xmlhttp.onreadystatechange = stateChanged; 
    }else {
        alert("The XMLHttpRequest not supported");
    }
    
}

function stateChanged(){
    if (xmlhttp.readyState == 4) { // 4 => loaded complete
        if (xmlhttp.status == 200) {
            var dom = xmlhttp.responseXML;
            dump(dom.documentElement.nodeName == "parsererror" ? "error while parsing" : dom.documentElement.nodeName);
			createResultTable(dom);
        }
        else {
            alert("statusText: " + xmlhttp.statusText + "\nHTTP status code: " + xmlhttp.status);
        }
        
    }
};
								
								
function createResultTable(xmlDoc){  
	var rootElement = xmlDoc.getElementsByTagName('results').item(0);
	var totalMsg=rootElement.getElementsByTagName('result').length; 
	var nsEl = "";
	if(totalMsg==0){
		nsEl = "<span class=\"nessunPaziente\">Nessun paziente trovato</span>";
	}else{		
		for(var j=0;j<totalMsg;j++){  
			var root=xmlDoc.getElementsByTagName('result')[j];
			nsEl += "<div id=\"tessera\" >";
			nsEl += "<span class=\"nome\">"+root.getElementsByTagName("paziente.nome").item(0).textContent.toUpperCase()+"</span>";
			nsEl += "<span class=\"cognome\">"+root.getElementsByTagName("paziente.cognome").item(0).textContent.toUpperCase()+"</span>";
			nsEl += "<span class=\"codiceFiscale\">"+root.getElementsByTagName("paziente.codice_fiscale").item(0).textContent.toUpperCase()+"</span>";
			nsEl += "<span class=\"luogoNascita\">"+root.getElementsByTagName("paziente.comune_residenza").item(0).textContent.toUpperCase()+"</span>";
			nsEl += "<span class=\"dataNascita\">"+root.getElementsByTagName("paziente.data_nascita").item(0).textContent.toUpperCase()+"</span>";
			nsEl += "<span class=\"sesso\">"+root.getElementsByTagName("paziente.sesso").item(0).textContent.toUpperCase()+"</span>";
			nsEl += "</div>";
		}  
	}
	document.getElementById('risultato').innerHTML = nsEl;
}  

//]]>

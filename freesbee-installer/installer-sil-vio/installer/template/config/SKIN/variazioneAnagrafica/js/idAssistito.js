//<![CDATA[	

var bindings = new Array(6);
bindings[0] = new binding('CodiceFiscale','parametroInterattivo7');
bindings[1] = new binding('RegioneDestinatario','parametroInterattivo8');
bindings[2] = new binding('CodiceAslDestinatario','parametroInterattivo9');
bindings[3] = new binding('IdOperatoreDestinatario','parametroInterattivo10');
bindings[4] = new binding('CodiceAslResidenza','parametroInterattivo11');
bindings[5] = new binding('CodiceAslAssistenza','parametroInterattivo12');

function copiaValori(){
	var text = document.getElementById('CodiceFiscale').value;
	document.getElementById('CodiceFiscale').value = text.toLowerCase();
	for(var i = 0;i<bindings.length;i++){
		copiaValore(bindings[i]);
	}
}

function inizializzaValori(){
	for(var i = 0;i<bindings.length;i++){		
		inizializzaValore(bindings[i]);
		if(bindings[i].source == "RegioneDestinatario"){					
		   var selRegione = document.getElementById('RegioneDestinatario');
		   regione(selRegione.value);
		}
	}
	
}

function asl(){
	var codiceAsl = document.getElementById('CodiceAslDestinatario').value;
	if (codiceAsl == "") return;
	var selRegione = document.getElementById('RegioneDestinatario').value;
	var risorsa = "";
	if(selRegione=="015"){   	
		switch (codiceAsl) {
			case '101':
				risorsa = "http://localhost:9192/EROGATORE/ServizioIdentificazioneAssistito_4/";
				break;
			case '102':
				risorsa = "https://sp.example.unibas.org/PD_IASSISTITO_BARI/";
				break;
			default:
				risorsa = "";
		}
	}
	document.getElementsByClassName('boxRisorsa').item(0).value = risorsa;
}

function regione(reg) {		
   if (reg == "") return;
   
   var province = new Array();
   var selProv = document.getElementById('CodiceAslDestinatario');
   while(selProv.options.length > 0) selProv.removeChild(selProv.options[0]); 		   
   
   switch(reg) {		    
        case '001':        
            province.push(new Option('AVEZZANO/SULMONA',101));
            province.push(new Option('CHIETI',102));
            province.push(new Option('LANCIANO/VASTO',103));
            province.push(new Option('L\'AQUILA',104));
            province.push(new Option('PESCARA',105));   
            province.push(new Option('TERAMO',106));   
            break;
   
        case '002': 
            province.push(new Option('VENOSA',101));
            province.push(new Option('POTENZA',102));
            province.push(new Option('LAGONEGRO',103));
            province.push(new Option('MATERA',104));
            province.push(new Option('MONTALBANO JONICO',105));
            break;
   
        case '015': 
            province.push(new Option('CATANIA',101));
            province.push(new Option('PALERMO',102));
            break;
   
        case '013': 
            province.push(new Option('BA/1',101));
            province.push(new Option('BA/2',102));
            province.push(new Option('BA/3',103));
            province.push(new Option('BA/4',104));
            province.push(new Option('BA/5',105));
            province.push(new Option('FG/1',106));
            province.push(new Option('FG/2',107));
            province.push(new Option('FG/3',108));
            province.push(new Option('LE/1',109));
            province.push(new Option('LE/2',110));
            province.push(new Option('TA/1',111));
            province.push(new Option('BR/1',112));
            break;
			
        default: 
            province.push(new Option('ASL 1',101));
            province.push(new Option('ASL 2',102));
            province.push(new Option('ASL 3',103));
            province.push(new Option('ASL 4',104));
            province.push(new Option('ASL 5',105));
   }		
    
   for (i=0; i<province.length; i++) {
      selProv.appendChild(province[i]);
   }
}

//]]>
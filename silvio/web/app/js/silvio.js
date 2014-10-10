//<![CDATA[	
function binding(source, target){
	this.source = source;
	this.target = target;
}

function copiaValore(x) {
	var sourceElement = document.getElementById(x.source);
	var text = sourceElement.value;
		var targetElements = document.getElementsByClassName(x.target);
		for(var i=0;i<targetElements.length;i++){
			targetElements.item(i).value = text;
		}
}

function inizializzaValore(x) {	
	var targetElements = document.getElementsByClassName(x.target);
	for(var i=0;i<targetElements.length;i++){
		var text = targetElements.item(i).value;		
		if(text != ""){
			var sourceElement = document.getElementById(x.source);
			sourceElement.value = text;
		}
	}
}

//]]>
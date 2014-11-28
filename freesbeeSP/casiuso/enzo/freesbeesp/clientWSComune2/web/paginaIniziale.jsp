<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Documento senza titolo</title>

<link href="/clientWSComune2/styleIndex.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>jQuery Spritely Example from AddyOsmani.com</title>
	<meta name="viewport" content="width = 320" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<script type="text/javascript" src="/clientWSComune2/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="/clientWSComune2/jquery.spritely-0.1.js"></script>
	<style type="text/css">
		body {
			background: #fff;
			margin:0 auto;
		}
		
		#sky {
	position:absolute;
	width:718px;
	height: 213px;
	background: transparent url(/clientWSComune2/images/cielo.png) 0 0px repeat-x;
		}
		
		#city2 {
			position: absolute;
			width: 718px; 
			height: 219px;
			background: transparent url(/clientWSComune2/images/citta2.png) 0 58px repeat-x; 
			
		}
		#city1 {
			position: absolute;
			width: 718px; 
			height: 219px;
			background: transparent url(/clientWSComune2/images/citta1.png) 0 58px repeat-x;
			
		}
		
		
		#plane1 {
			position:relative;
			width: 365px;
			height: 82px;
			background: transparent url(/clientWSComune2/images/aereomezzi.png) 0 0px no-repeat;		}
		
		#content {
		 
		   width: 100%;
		   position:relative;
		   clear:both;
		}
		
		#leftcontent {
		  width:70%;
		}
		
		#rightcontent {
		  width:30%;
		  background-color:blue;
		}
		
		
		
	</style>
	<script type="text/javascript">
		(function($) {
			$(document).ready(function() {
			
				$('#city1').pan({fps: 30, speed: 1, dir: 'right'}); 
				
				$('#city2').pan({fps: 30, speed: 2, dir: 'right'}); 
				
				$('#sky').pan({fps: 30, speed: 0.5, dir: 'right'}); 
				
				var stage_left = (($('body').width() - 866) / 2);
				var stage_top = 30;
				
				
				$('#plane1').sprite({fps: 8, no_of_frames: 14})
					.spRandom({
						top: 40,
						left: stage_left + 100,
						right: 100,
						bottom: 90,
						speed: 3000,
						pause: 1000
					});
				
			
			});
		})(jQuery);
	</script>
</head>

<body>
<div class="contenitore">
  <div class="header"></div>
  
  <div id='cssmenu'>
<ul>
   <li class='active '><a href='/clientWSComune2/paginaIniziale.jsp'><span>Home</span></a></li>
   <li><a href='https://sp.comune.pz:8443/freesbeesp/schermoLogin.faces?autenticazione=UTENTE&uriDiDesti
nazione=http://localhost:8080/clientWSComune2/paginaCerca.jsp&silSessionId=${sessione}&risorsa=https://sp.comune.pz/PD_CERCA_IMMATRICOLAZIONE'><span>Cerca </span></a></li>
   <li><a href='https://sp.comune.pz:8443/freesbeesp/schermoLogin.faces?autenticazione=UTENTE&uriDiDesti
nazione=http://localhost:8080/clientWSComune2/paginaNuovaImmatricolazione.jsp&silSessionId=${sessione}&risorsa=https://sp.comune.pz/PD_CERCA_CITTADINO'><span>Immatricolazione</span></a></li>
   <li><a href='#'><span>Modifica</span></a></li>
   <li><a href='#'><span>Elimina</span></a></li>
   <li><a href='#'><span>Info</span></a></li>
   <li><a href='#'><span>Esci</span></a></li>
</ul>
</div>
  <div class="corpo">
    <div class="animazione">
    <div id="sky"></div>

    <div id="city1"></div>

    <div id="city2"></div>
    <div id="plane1"></div>
    </div>
  </div>
  <div class="footer">
  </div>
</div>
</body>
</html>

$(document).ready(function(){

	//valida data
	$('input[type="date"]').change(function () {   

	   var ano = "", dia = "", mes = "";
	   var valor = this.value;
	   
	   if (valor.length > 10) {   

		   if (valor.length == 12) {   //123456-01-25
            	ano = valor.substring(0, 4); 
            	dia = valor.substring(10);
	    		mes = valor.substring(7, 9);
		   }
		   else if (valor.length == 11) {    //12345-01-25
			   	ano = valor.substring(0, 4); 
	    		dia = valor.substring(9);
	    		mes = valor.substring(6, 8);
		   }
                                                                             
           var myDate = new Date(ano, mes, ano);
          
           var data = myDate.getFullYear().toString();
		
           var data = myDate.getFullYear().toString() + 
			           "-" + myDate.getMonth().toString() +
                       "-" + myDate.getDate().toString();

           alert("Data inválida. Informe dd/MM/aaaa")
           this.value = data;
	   }    
	});
});
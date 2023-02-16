/**
 * 
 */
alert("este texto es el que modificas");

$.ajax({
    type: 'POST',
    beforeSend:function(){
    	 obj = document.getElementById("procesando2");
			obj.style.display = (obj.style.display == 'none') ? 'block'	: 'block';
			document.getElementById("formulario5").style.display = "none";
			document.getElementById("formulario6").style.display = "none";
    },
    success:function(data){
    	 obj = document.getElementById("procesando2");
			obj.style.display = (obj.style.display == 'none') ? 'block'	: 'block';
			document.getElementById("formulario5").style.display = "none";
			document.getElementById("formulario6").style.display = "none";          
    },
    error:function(){
    	 obj = document.getElementById("procesando2");
			obj.style.display = (obj.style.display == 'none') ? 'block'	: 'block';
			document.getElementById("formulario5").style.display = "none";
			document.getElementById("formulario6").style.display = "none";
    }
});

$.ajax({
	   type: "GET",
	   beforeSend: function(){
		   obj = document.getElementById("procesando2");
			obj.style.display = (obj.style.display == 'none') ? 'block'	: 'block';
			document.getElementById("formulario5").style.display = "none";
			document.getElementById("formulario6").style.display = "none";
		   
	   },
	   complete: function(){
		   obj = document.getElementById("procesando2");
			obj.style.display = (obj.style.display == 'none') ? 'block'	: 'block';
			document.getElementById("formulario5").style.display = "none";
			document.getElementById("formulario6").style.display = "none";
	   }
	});

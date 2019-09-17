
               
var translate = function(e)
{   
    var dst = document.getElementById("dstLang").value;
    var src = document.getElementById("srcLang").value;
    var input = document.getElementById("word").value;
    var eng = document.getElementById("eng").value;
    var srb = document.getElementById("srb").value;
    var ger = document.getElementById("ger").value;
    var hint = document.getElementById("hintSpan");
    var hint2 = document.getElementById("hintSpan2");
    hint.innerHTML="";
    hint2.innerHTML="";
if((e.value==="button1" && isWord(input)) || (e.value==="button2" &&isWord(eng)&&isWord(srb)&&isWord(ger))){              
xmlhttp=new XMLHttpRequest();
xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState===4 && xmlhttp.status===200)
{
document.getElementById("demo").innerHTML=xmlhttp.responseText;
}
};
xmlhttp.open("GET","TranslateServlet?button="+e.value+"&input="+input+"&src="+src+"&dst="+dst+"&eng="+eng+"&srb="+srb+"&ger="+ger,true);
xmlhttp.send();
}else{
    if(e.value==="button1")
    hint.innerHTML="You must insert some word!";
    else hint2.innerHTML="You need to insert word in all three languages!";
    }
};

                  
                //HELPER FUNCTIONS- IS CHAR LETTER/DIGIT
                        var isCharLetter = function (c){
   			 if (c >= '0' && c <= '9') 
   			 	return false;
				else  return true;	
			};

			

			var isWord = function(sl){
				var provera;
				if(sl===""||sl.length<2) return false;
				for(var i =0; i<sl.length; i++){
					if(isCharLetter(sl.charAt(i))) provera = true;
						else{ provera = false;
							break;
					}
				}return provera;
			};


			
           

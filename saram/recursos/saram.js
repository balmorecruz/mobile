/**
 * 
 */
$.ajax({
    type: 'post',
    url: '"<%=strDownloadURLTest%>"+"/downloaddocservlet?downloadType="+dType+"&PNumber="+"<%=custPNo%>"',
    data: {
        name: $('#dType').val()
    },
    beforeSend:function(){
    alert("Please wait.....");
        // this is where we append a loading image
    },
    success:function(responseText){
    alert.close();
        // successful request; do something with the data            
    },
    error:function(){
        // failed request; give feedback to user
    }
});
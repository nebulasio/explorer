<?php 
include_once 'header.php';
?>




<div class="container node_status">
	<div class="col-md-12" id="nodes">

	</div>
</div>


<div class="container node_status">
    <div class="col-md-12">	
        <table class="table table-hover" id="block_height">
        </table>
    </div>
</div>

<div class="container node_status">
	<div class="col-md-12">	
    	<table class="table table-hover" id="block_hash">
        </table>
    </div>
</div>

<?php 
include_once 'footer.php';
?>

<script src="https://cdn.bootcss.com/tether/1.4.3/js/tether.min.js "></script>
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js "></script>
<script src="https://cdn.bootcss.com/bootbox.js/4.4.0/bootbox.min.js "></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js "></script>
<script src="<?php echo $base_url;?>static/frontend/js/home.v1.js "></script>
<script>
$(document).ready(function(){
	node_block();
	window.setInterval("node_block_val()",20000); 
	});
function node_block_val(){
	$("#nodes").empty();
	$("#block_height").empty();
	$("#block_hash").empty();
	node_block();
}
function node_block(){
	//get the node info by ajax
	  $.get("<?php echo $base_url;?>Influx_nebulas/node_status",function(result){
		  var background ='';
		  for(var node in result){
			  if(result[node].status_int===1){
				  background = 'green';
			  }else{
				  background = 'red';
			  }
			  str = '<div class="col-xs-6 col-sm-4 col-md-3 col-lg=2"><p class="node_font" style="background:'+background+';">'+result[node].node_addr+'<p></div>';
			  
			  $("#nodes").append(str);
		  }
		  });
	  $.get("<?php echo $base_url;?>Influx_nebulas/block_height",function(result){
		  var background ='';
		  for(var height in result){

			  str = '<tr><td>node:'+result[height].node_addr+'</td><td>time:'+result[height].block_time+'</td><td>block_height:'+result[height].block_height+'</td></tr>';
			  $("#block_height").append(str);
		  }
		  });
	  $.get("<?php echo $base_url;?>Influx_nebulas/block_hash",function(result){
		  var background ='';
		  for(var hash in result){

			  str = '<tr><td>node:'+result[hash].node_addr+'</td><td>time:'+result[hash].block_time+'</td><td>block_hash:'+result[hash].block_hash+'</td></tr>';
			  $("#block_hash").append(str);
		  }
		  });
}
</script>


</body>
</html>
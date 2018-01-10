<?php 
include_once 'header.php';
?>




<div class="container node_status" id="nodes_parent">
	<div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes">

	</div>
</div>
<div class="container">
	<div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes">
	<div class="col-md-12 table-responsive">
		<table class="table table-hover">
    		<thead>
    			<tr>
    				<th>Block Hash</th>
    				<th>Block Height</th>
				</tr>
    		</thead>
    		<tbody>
    			<tr>
    				<td id="block_hash"></td>
    				<td id="block_height"></td>
				</tr>
    		</tbody>
        </table>
        </div>
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
	window.setInterval("node_block_val()",60000); 
	});
function node_block_val(){
	height = $("#nodes").height();
	$("#nodes_parent").height(height);	
	$("#nodes").empty()
	node_block();
}
function node_block(){
	//get the node info by ajax
	  $.get("<?php echo $base_url;?>Influx_nebulas/node_status",function(result){
		  var background ='';
		  var str = '';
		  for(var node in result){
			  if(result[node].status_int===1){
				  background = 'green';
			  }else{
				  background = 'red';
			  }
			  str += '<div class="col-xs-6 col-sm-4 col-md-3 col-lg=2"><p class="div_node" style="background:'+background+';">'+result[node].node_addr+'<p></div>';
			  
			  
		  }
		  $("#nodes").append(str);
		  });
	  $.get("<?php echo $base_url;?>Influx_nebulas/retrun_block_hash_height",function(result){
		  block_hash = result.block_hash;
		  block_height = result.block_height;
		  $("#block_hash").html(block_hash);
		  $("#block_height").html(block_height);
		  });
}
</script>


</body>
</html>
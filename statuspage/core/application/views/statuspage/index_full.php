<?php 
include_once 'header.php';
?>




<div class="container node_status" id="nodes_parent">
	<div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes">
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="cal_1">cal_1:13.56.18.241</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="cal_2">cal_2:13.56.149.52</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="cal_3">cal_3:54.215.252.139</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="lon_1">lon_1:35.177.86.207</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="lon_2">lon_2:35.176.104.249</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="lon_3">lon_3:35.177.190.41</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="jan_1">jan_1:13.230.161.29</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="jan_2">jan_2:54.249.49.73</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="jan_3">jan_3:54.238.223.81</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="can_1">can_1:35.182.48.19</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="can_2">can_2:35.182.4.249</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="can_3">can_3:35.182.169.167</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="par_1">par_1:52.47.129.84</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="par_2">par_2:52.47.120.101</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="par_3">par_3:52.47.155.0</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="sin_1">sin_1:13.250.28.192</p>
		</div>
		
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="sin_2">sin_2:54.179.143.169</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="sin_3">sin_3:13.250.10.239</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="vir_1">vir_1:47.89.191.206</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="hk_1">hk_1:47.52.174.176</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="ger_1">ger_1:47.91.89.136</p>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<p class="div_node_full" id="syd_1">syd_1:54.206.110.30</p>
		</div>
		
	</div>
</div>
<div class="container">
	<div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes">
	<div class="col-md-12 table-responsive">
		<table class="table table-hover">
    		<thead>
    			<tr>
    				<th>Block Hash</th>
    				<th style="width:20%;">Block Height</th>
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

<div class="container">
        <div class="col-md-12">
            <div style=" font-size: 2rem; text-align: left; padding-right: 1rem; margin-bottom: 1rem;">History</div>
            <hr/>
        </div>
</div>    
<div class="container">
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">cal_1</p></div>
        <div class="col-md-10" id="h_cal_1">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">cal_2</p></div>
        <div class="col-md-10" id="h_cal_2">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">cal_3</p></div>
        <div class="col-md-10" id="h_cal_3">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">lon_1</p></div>
        <div class="col-md-10" id="h_lon_1">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">lon_2</p></div>
        <div class="col-md-10" id="h_lon_2">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">lon_3</p></div>
        <div class="col-md-10" id="h_lon_3">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">jan_1</p></div>
        <div class="col-md-10" id="h_jan_1">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">jan_2</p></div>
        <div class="col-md-10" id="h_jan_2">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">jan_3</p></div>
        <div class="col-md-10" id="h_jan_3">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">can_1</p></div>
        <div class="col-md-10" id="h_can_1">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">can_2</p></div>
        <div class="col-md-10" id="h_can_2">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">can_3</p></div>
        <div class="col-md-10" id="h_can_3">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">par_1</p></div>
        <div class="col-md-10" id="h_par_1">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">par_2</p></div>
        <div class="col-md-10" id="h_par_2">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">par_3</p></div>
        <div class="col-md-10" id="h_par_3">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">sin_1</p></div>
        <div class="col-md-10" id="h_sin_1">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">sin_2</p></div>
        <div class="col-md-10" id="h_sin_2">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">sin_3</p></div>
        <div class="col-md-10" id="h_sin_3">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">vir_1</p></div>
        <div class="col-md-10" id="h_vir_1">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">hk_1</p></div>
        <div class="col-md-10" id="h_hk_1">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">ger_1</p></div>
        <div class="col-md-10" id="h_ger_1">
        
        </div>
    </div>
    <div class="col-md-12" style="padding-left: 0px;padding-right:0px;" id="nodes_history">
        <div class="col-md-2"><p class="div_node_history" id="vir_1">syd_1</p></div>
        <div class="col-md-10" id="h_syd_1">
        
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
	get_nodes_history();
	window.setInterval("node_block_val()",60000); 
	});
function node_block_val(){
	node_block();
}
function node_block(){
	//get the node info by ajax
	  $.get("<?php echo $base_url;?>Influx_nebulas/node_status",function(result){		  
		  var str = '';
		  var nodes = $("#nodes").find("p");
		  $.each(nodes,function(key,val){
			  background ='red';
			  id = nodes[key].id;
			  for(var n=0;n<result.length;n++){
				  var obj = result[n];				  
				  if(obj.node_addr == id){
					  background = 'green';				  
				  }				  
			  }
			  p_id = '#'+id;
			  $(p_id).attr("style","background:"+background);			  			  
			  });
		  console.log('nodestatus');
		  //$("#nodes").append(str);
		  });
	  $.get("<?php echo $base_url;?>Influx_nebulas/retrun_block_hash_height",function(result){
		  block_hash = result.block_hash;
		  block_height = result.block_height;
		  $("#block_hash").html(block_hash);
		  $("#block_height").html(block_height);
		  console.log('block_height_hash');
		  });
}
function get_nodes_history(){
	$.get("<?php echo $base_url;?>Influx_nebulas/get_nodes",function(result){
		  for(var a=0;a<result.length;a++){
			  nodes = result[a].node_addr;
			  url = 'Influx_nebulas/get_nodes_history/'+nodes;
			  $.get('<?php echo $base_url;?>'+url,function(res){
				  node_addr = res.node_addr;
				  historys = res.history;
				  for(b=historys.length-1;b!=-1;b--){
					  id = '#h_'+node_addr;
					  str = '<span class="span_history" title="'+node_addr+':\n'+historys[b].node_date+'\n'+historys[b].node_points+'" style="background:'+historys[b].node_color+'"></span>';
					  $(id).append(str);
					  }
				  });

		  }

		  });
}
</script>


</body>
</html>

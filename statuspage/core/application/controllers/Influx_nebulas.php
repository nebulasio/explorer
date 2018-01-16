<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Influx_nebulas extends CI_Controller{
    
    
    
    public function __construct(){
        parent::__construct();
        require APPPATH.'vendor/autoload.php';
        
        
    }
    public $host = '';
    public $port = '';
    public $username = '';
    public $password = '';
    public $ssl = TRUE;
    public $verifySSL = TRUE;
    public $readTimeout = 100000;
    public $connectTimeout = 100000;
    
    
    /*
     * node_status
     */
    public function node_status(){
        $client = new \InfluxDB\Client($this->host,$this->port,$this->username,$this->password,$this->ssl,$this->verifySSL);
        $database = $client->selectDB('nebulas');
        $result = $database->query('SELECT "value" FROM "neb.start.gauge" WHERE ("chainID" = \'1001\') AND time > now() - 5s GROUP BY "node_addrs"');
        //SELECT "value" FROM "neb.start.gauge" WHERE ("chainID" = '1001') AND time > now() - 5s GROUP BY "node_addrs"
        // get the points from the resultset yields an array
        $points = $result->getSeries();
        $node_status = array();
        //get each node's status info,select the lastest point
        foreach ($points as $value){
            $tmp = array();
            if(array_key_exists('tags', $value)){
                if(array_key_exists('node_addrs', $value['tags'])){
                    $node_addr = $value['tags']['node_addrs'];
                    //echo $node_addr;
                    $tmp['node_addr'] = $node_addr;
                }
            }
            if(array_key_exists('values', $value)){
                $value_temp = end($value['values']);
                $status_time = $value_temp[0];
                $status_int = $value_temp[1];
                $tmp['status_time'] = $status_time;
                $tmp['status_int'] = $status_int;
            }
            array_push($node_status, $tmp);
        }
        //response as json
        $this->output->set_header('Content-Type:application/json;charset=utf-8');
        echo json_encode($node_status);
    }
    
    /*
     * var_dump node_status
     */
    public function node_status_var(){
        $client = new \InfluxDB\Client($this->host,$this->port,$this->username,$this->password,$this->ssl,$this->verifySSL);
        $database = $client->selectDB('nebulas');
        $result = $database->query('SELECT "value" FROM "neb.start.gauge" WHERE ("chainID" = \'1001\') AND time > now() - 5s GROUP BY "node_addrs"');
        //SELECT "value" FROM "neb.start.gauge" WHERE ("chainID" = '1001') AND time > now() - 5s GROUP BY "node_addrs"
        // get the points from the resultset yields an array
        $points = $result->getSeries();
        var_dump($points);
    }
    /*
     * block height
     */
    public function block_height(){
        $client = new \InfluxDB\Client($this->host,$this->port,$this->username,$this->password,$this->ssl,$this->verifySSL);
        $database = $client->selectDB('nebulas');
        $result = $database->query('SELECT "value" FROM "neb.block.height.gauge" WHERE ("chainID" = \'1001\') AND time > now() - 5s GROUP BY "node_addrs"');
        // get the points from the resultset yields an array
        $points = $result->getSeries();
        $node_block_height = array();
        //get each node's status info,select the lastest point
        foreach ($points as $value){
            $tmp = array();
            if(array_key_exists('tags', $value)){
                if(array_key_exists('node_addrs', $value['tags'])){
                    $node_addr = $value['tags']['node_addrs'];
                    //echo $node_addr;
                    $tmp['node_addr'] = $node_addr;
                }
            }
            if(array_key_exists('values', $value)){
                $value_temp = end($value['values']);
                $block_time = $value_temp[0];
                $block_height = $value_temp[1];
                $tmp['block_time'] = $block_time;
                $tmp['block_height'] = $block_height;
            }
            array_push($node_block_height, $tmp);
        }
        //response as json
        $this->output->set_header('Content-Type:application/json;charset=utf-8');
        echo json_encode($node_block_height);
    }
    /*
     * var_dump block_height
     */
    public function block_height_var(){
        $client = new \InfluxDB\Client($this->host,$this->port,$this->username,$this->password,$this->ssl,$this->verifySSL);
        $database = $client->selectDB('nebulas');
        $result = $database->query('SELECT "value" FROM "neb.block.height.gauge" WHERE ("chainID" = \'1001\') AND time > now() - 5s GROUP BY "node_addrs"');
        // get the points from the resultset yields an array
        $points = $result->getSeries();
        var_dump($points);
    }
    
    /*
     * block hash
     */
    public function block_hash(){
        $client = new \InfluxDB\Client($this->host,$this->port,$this->username,$this->password,$this->ssl,$this->verifySSL);
        $database = $client->selectDB('nebulas');
        $result = $database->query('SELECT "value" FROM "neb.block.tailhash.gauge" WHERE ("chainID" = \'1001\') AND time > now() - 5s GROUP BY "node_addrs"');
        // get the points from the resultset yields an array
        $points = $result->getSeries();
        $node_block_hash = array();
        //get each node's status info,select the lastest point
        foreach ($points as $value){
            $tmp = array();
            if(array_key_exists('tags', $value)){
                if(array_key_exists('node_addrs', $value['tags'])){
                    $node_addr = $value['tags']['node_addrs'];
                    //echo $node_addr;
                    $tmp['node_addr'] = $node_addr;
                }
            }
            if(array_key_exists('values', $value)){
                $value_temp = end($value['values']);
                $block_time = $value_temp[0];
                $block_hash = $value_temp[1];
                $tmp['block_time'] = $block_time;
                $tmp['block_hash'] = $block_hash;
            }
            array_push($node_block_hash, $tmp);
        }
        //response as json
        $this->output->set_header('Content-Type:application/json;charset=utf-8');
        echo json_encode($node_block_hash);
    }
    /*
     * var_dump block_hash
     */
    public function block_hash_var(){
        $client = new \InfluxDB\Client($this->host,$this->port,$this->username,$this->password,$this->ssl,$this->verifySSL);
        $database = $client->selectDB('nebulas');
        $result = $database->query('SELECT "value" FROM "neb.block.tailhash.gauge" WHERE ("chainID" = \'1001\') AND time > now() - 5s GROUP BY "node_addrs"');
        // get the points from the resultset yields an array
        $points = $result->getSeries();
        var_dump($points);
    }
    
    /*
     * get block height by api
     */
    private function get_block_hash_api(){
        $url = 'https://testnet.nebulas.io/v1/user/nebstate';
        $nebstate_json = $this->curl($url);
        $nebstate = json_decode($nebstate_json,TRUE);
        $tail = $nebstate['tail'];
        return $tail;
    }
    
    /*
     * get block hash by api
     */
    private function get_block_height_api(){
        $hash = $this->get_block_hash_api();
        $url = 'https://testnet.nebulas.io/v1/user/getBlockByHash';
        $postdata = array('hash'=>$hash);
        $blockbyhash_json =$this->curl($url,$postdata);
        $blockbyhash = json_decode($blockbyhash_json,TRUE);
        $height = $blockbyhash['height'];
        return array('block_hash'=>$hash,'block_height'=>$height);
    }
    
    /*
     * response json like {"hash":xxxxxx,"height":18888}
     */
    public function retrun_block_hash_height(){
        $response = $this->get_block_height_api();
        $this->output->set_header('Content-Type:application/json;charset=utf-8');
        echo json_encode($response);
    }

    /*
     * curl
     */
    private function curl($url,$postFields = null)
    {
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_FAILONERROR, false);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        if ($this->readTimeout) {
            curl_setopt($ch, CURLOPT_TIMEOUT, $this->readTimeout);
        }
        if ($this->connectTimeout) {
            curl_setopt($ch, CURLOPT_CONNECTTIMEOUT, $this->connectTimeout);
        }
        //https
        if(strlen($url) > 5 && strtolower(substr($url,0,5)) == "https" ) {
            curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
            curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, false);
        }
        
        if (is_array($postFields) && 0 < count($postFields))
        {
            $postBodyString = "";
            $postMultipart = false;
            foreach ($postFields as $k => $v)
            {
                if("@" != substr($v, 0, 1))
                {
                    //$postBodyString .= "$k=" . urlencode($v) . "&";
                    $postBodyString = json_encode($postFields);
                }
                else
                {
                    $postMultipart = true;
                }
            }
            unset($k, $v);
            curl_setopt($ch, CURLOPT_POST, true);
            if ($postMultipart)
            {
                curl_setopt($ch, CURLOPT_POSTFIELDS, $postFields);
            }
            else
            {
                //curl_setopt($ch, CURLOPT_POSTFIELDS, substr($postBodyString,0,-1));
                curl_setopt($ch, CURLOPT_POSTFIELDS, $postBodyString);
                curl_setopt($ch, CURLOPT_HTTPHEADER, array(
                    'Content-Type: application/json',
                    'Content-Length: ' . strlen($postBodyString))
                    );
            }
        }
        $reponse = curl_exec($ch);
        
        if (curl_errno($ch))
        {
            throw new Exception(curl_error($ch),0);
        }
        curl_close($ch);
        return $reponse;
    }
    
    /**
     * get nodes
     */
    public function get_nodes(){
        $this->load->model('nebulas_model');
        $nodes = $this->nebulas_model->get_nodes();
        $this->output->set_header('Content-Type:application/json;charset=utf-8');
        echo json_encode($nodes);
    }
    
    /**
     * @return mixed[]
     */
    public function get_nodes_history(){
        $node_addr = $this->uri->segment(3);
        $this->load->model('nebulas_model');        
        $response_nodes = array();
        $history = array();
        $response = $this->nebulas_model->get_node_history($node_addr,'30');                
            $response_color = array();            
            $tmp = array();
            foreach ($response as $value){                
                if(array_key_exists('node_points', $value)){
                    if ($value['node_points']>40000){
                        $tmp = array(
                            'node_date'=>$value['node_date'],
                            'node_points'=>$value['node_points'],
                            'node_color'=>'green'
                        );
                    }elseif ($value['node_points']<30000){
                        $tmp = array(
                            'node_date'=>$value['node_date'],
                            'node_points'=>$value['node_points'],
                            'node_color'=>'red'
                        );
                    }else{
                        $tmp = array(
                            'node_date'=>$value['node_date'],
                            'node_points'=>$value['node_points'],
                            'node_color'=>'yellow'
                        );
                    }
                }else {
                    $tmp = array(
                        'node_date'=>'2018-01-08',
                        'node_points'=>'0',
                        'node_color'=>'red'
                    );
                }
                array_push($history, $tmp);
            }
            
            $response_color = array(
                'node_addr' =>$node_addr,
                'history' => $history
            ); 
 
        
        $this->output->set_header('Content-Type:application/json;charset=utf-8');
        echo json_encode($response_color);
        
    }
}
<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class History_nebulas extends CI_Controller{
    
    
    
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
    public $chainid = '1001';
    
    public function get_yesterday_total_points(){
        $client = new \InfluxDB\Client($this->host,$this->port,$this->username,$this->password,$this->ssl,$this->verifySSL);
        $database = $client->selectDB('nebulas');
        //$result = $database->query('SELECT "value" FROM "neb.start.gauge" WHERE ("chainID" = \''.$this->chainid.'\') AND time > now() - 5s GROUP BY "node_addrs"');
        //SELECT "value" FROM "neb.start.gauge" WHERE ("chainID" = '1001') AND time > now() - 5s GROUP BY "node_addrs"
        // get the points from the resultset yields an array
        //$points = $result->getSeries();        
        $node_addrs = array();
        $tmpp = array("cal_1","cal_2","cal_3","lon_1","lon_2","lon_3","jan_1","jan_2","jan_3","can_1","can_2","can_3","par_1","par_2","par_3","sin_1","sin_2","sin_3","vir_1","hk_1","ger_1","syd_1");
        foreach($tmpp as $tmp_node){
            $tmp['node_addr']=$tmp_node;
            array_push($node_addrs, $tmp);
        }
        
        //get each node's status info,select the lastest point
        /*
        foreach ($points as $value){
            $tmp = array();
            if(array_key_exists('tags', $value)){
                if(array_key_exists('node_addrs', $value['tags'])){
                    $node_addr = $value['tags']['node_addrs'];
                    //echo $node_addr;
                    $tmp['node_addr'] = $node_addr;
                }
            }
            array_push($node_addrs, $tmp);
        }*/
        
        $this->load->model('nebulas_model');
        foreach ($node_addrs as $node_addrt){
            $t = date('Y-m-d',time());
            $timet = date('Y-m-d\TH:i:s.0000\Z', strtotime($t)-1*86400);
            $timett = date('Y-m-d\TH:i:s.0000\Z', strtotime($t)-0*86400);
            $resultc = $database->query('SELECT count(value) FROM "neb.start.gauge" WHERE ("chainID" = \''.$this->chainid.'\') AND  time > \''.$timet.'\' AND time < \''.$timett.'\'  AND node_addrs=\''.$node_addrt['node_addr'].'\'');
            $c = $resultc->getSeries();
            $count = 0;
            if(is_array($c) && 0 < count($c)){
                $tmp = $c[0];
                if(array_key_exists('values', $tmp)){
                    $count = $tmp['values'][0][1];
                }
            }
            $data = array(
                'node_addr'     =>  $node_addrt['node_addr'],
                'node_date'     =>  date('Y-m-d',strtotime($t)-1*86400),
                'node_points'   =>  $count
            );
            if($this->nebulas_model->insert_node_points($data)){
                print_r($data);
            }else{
                echo 'error';
            }


        }
        
        
        //response as json
        //$this->output->set_header('Content-Type:application/json;charset=utf-8');
        //echo json_encode($node_status);
    }
}
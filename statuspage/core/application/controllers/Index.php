<?php
defined('BASEPATH') OR exit('No direct script access allowed');
/**
* index controler
*/
class Index extends CI_Controller
{
    
    public function __construct()
    {
        parent::__construct();
    }


    /**
     * index
     */
    public function index() {
        $data['base_url'] = $this->config->base_url();
        $this->load->view('statuspage/index_full',$data);
    }
    
    /**
     * index full info
     */
    public function index_full() {
        $data['base_url'] = $this->config->base_url();
        $this->load->view('statuspage/index',$data);
    }
    
    public function test()
    {
        echo 'hello ,i am luoam<br/>';
        echo BASEPATH;
        echo('<BR />');
        echo APPPATH;
        echo('<br />');
        echo VIEWPATH;
        echo('<br />');
        echo FCPATH;
    }
}


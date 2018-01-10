<?php
defined('BASEPATH') OR exit('No direct script access allowed');
class Nebulas_model extends CI_Model{
    /**
     * @param array $data
     */
    public function insert_node_points($data){
        $this->load->database();
        $node_addr = '';
        $node_date = '';
        if(array_key_exists('node_addr', $data)){
            $node_addr = $data['node_addr'];
        }
        if(array_key_exists('node_date', $data)){
            $node_date = $data['node_date'];
        }
        $query = $this->db->select('node_addr')
                            ->from('node_points')
                            ->where('node_addr',$node_addr)
                            ->where('node_date',$node_date)
                            ->get();
        $result = $query->row_array();
        if(count($result) > 0){
            $this->db->where('node_addr',$node_addr);
            $this->db->where('node_date',$node_date);
            $this->db->delete('node_points');
        }
        $this->db->insert('node_points',$data);
        $id = $this->db->affected_rows();
        $this->db->close();
        if ($id > 0){
            return TRUE;
        }else{
            return FALSE;
        }
    }
    
    /**
     * get-nodes
     */
    public function get_nodes(){
        $this->load->database();
        $query = $this->db->select('node_addr')
                        ->from('node_points')
                        ->distinct()
                        ->get();
        $result = $query->result_array();
        $this->db->close();
        return $result;
    }
    
    public function get_node_history($node_addr,$days = '30'){
        $this->load->database();
        $query = $this->db->select('node_addr,node_date,node_points')
                        ->from('node_points')
                        ->where('node_addr',$node_addr)
                        ->order_by('node_date','DESC')
                        ->limit($days)
                        ->get();
        $result = $query->result_array();
        $this->db->close();
        return $result;
    }
}
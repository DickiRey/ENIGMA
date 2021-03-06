<?php
defined('BASEPATH') OR exit('no direct script access allowed');

class Kehadiran_model extends CI_Model
{
    private $_tkehadiran = "kehadiran";
    private $_tsession = "session";

    public function getAll()
    {
        return $this->db->from("kehadiran")
            ->join('mahasiswa', 'mahasiswa.nim=kehadiran.id_mhs')
            ->join('kelas', 'kelas.id_kelas=kehadiran.id_kelas')
            // ->join('dosen', 'dosen.id_dsn=kehadiran.id_dsn')
            ->get()    
            ->result();
    }

    public function delKehadiran($where)
    {
        $this->db->where($where);
        $this->db->delete($this->_tkehadiran);
    }

    public function getSession()
    {
        return $this->db->from($this->_tsession)
            ->join('mahasiswa', 'mahasiswa.nim=session.id_mhs')
            ->join('kelas', 'kelas.id_kelas=session.id_kelas')
            ->get()
            ->result();
    }

    public function delAllSes()
    {
        $query = "TRUNCATE TABLE session";
        return $this->db->query($query);
    }
}
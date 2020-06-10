<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Produk_model extends CI_Model {

	private $table = 'produk';

	//fungsi insert ke database
    public function get_insert($data){
		$this->db->insert($this->table, $data);
		return TRUE;
	 }

	 function get_allimage() {
        $this->db->from($this->table);
        $query = $this->db->get();
        return $query->result();
 	}

	public function read()
	{
		$this->db->select('produk.id, produk.barcode, produk.nama_produk, produk.image, produk.harga, produk.stok, produk.deskripsi, kategori_produk.kategori, kalori_produk.kalori');
		$this->db->from($this->table);
		$this->db->join('kategori_produk', 'produk.kategori = kategori_produk.id');
		$this->db->join('kalori_produk', 'produk.kalori = kalori_produk.id');
		return $this->db->get();
	}

	public function update($id, $data)
	{
		$this->db->where('id', $id);
		return $this->db->update($this->table, $data);
	}

	public function delete($id)
	{
		$this->db->where('id', $id);
		return $this->db->delete($this->table);
	}
	
	public function getProduk($id)
	{
		$this->db->select('produk.id, produk.barcode, produk.nama_produk, produk.harga, produk.stok, produk.deskripsi, kategori_produk.id as kategori_id, kategori_produk.kategori, kalori_produk.id as kalori_id, kalori_produk.kalori');
		$this->db->from($this->table);
		$this->db->join('kategori_produk', 'produk.kategori = kategori_produk.id');
		$this->db->join('kalori_produk', 'produk.kalori = kalori_produk.id');
		$this->db->where('produk.id', $id);
		return $this->db->get();
	}

	public function getBarcode($search='')
	{
		$this->db->select('produk.id, produk.barcode');
		$this->db->like('barcode', $search);
		return $this->db->get($this->table)->result();
	}

	public function getNama($id)
	{
		$this->db->select('nama_produk, stok');
		$this->db->where('id', $id);
		return $this->db->get($this->table)->row();
	}

	public function getStok($id)
	{
		$this->db->select('stok, nama_produk, harga, barcode');
		$this->db->where('id', $id);
		return $this->db->get($this->table)->row();
	}

	public function produkTerlaris()
	{
		return $this->db->query('SELECT produk.nama_produk, produk.terjual FROM `produk` 
		ORDER BY CONVERT(terjual,decimal)  DESC LIMIT 5')->result();
	}

	public function dataStok()
	{
		return $this->db->query('SELECT produk.nama_produk, produk.stok FROM `produk` ORDER BY CONVERT(stok, decimal) DESC LIMIT 50')->result();
	}

}

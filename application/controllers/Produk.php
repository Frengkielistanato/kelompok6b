<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Produk extends CI_Controller {

	public function __construct()
	{
		parent::__construct();
		if ($this->session->userdata('status') !== 'login' ) {
			redirect('/');
		}
		$this->load->model('produk_model');
		$this->load->helper(array('form', 'url'));
	}

	public function index()
	{
		$this->load->view('produk');
	}

	public function read()
	{
		header('Content-type: application/json');
		if ($this->produk_model->read()->num_rows() > 0) {
			foreach ($this->produk_model->read()->result() as $produk) {
				$data[] = array(
					'barcode' => $produk->barcode,
					'nama' => $produk->nama_produk,
					'image' => $produk->image,
					'kategori' => $produk->kategori,
					'kalori' => $produk->kalori,
					'harga' => $produk->harga,
					'stok' => $produk->stok,
					'deskripsi' => $produk->deskripsi,
					'action' => '<button class="btn btn-sm btn-success" onclick="edit('.$produk->id.')">Edit</button> <button class="btn btn-sm btn-danger" onclick="remove('.$produk->id.')">Delete</button>'
				);
			}
		} else {
			$data = array();
		}
		$produk = array(
			'data' => $data
		);
		echo json_encode($produk);
	}

	public function add()
	{	
		$data = array(
			'barcode' => $this->input->post('barcode'),
			'nama_produk' => $this->input->post('nama_produk'),
			'image' => $this->input->post('image'),
			'kalori' => $this->input->post('kalori'),
			'kategori' => $this->input->post('kategori'),
			'harga' => $this->input->post('harga'),
			'stok' => $this->input->post('stok'),
			'deskripsi' => $this->input->post('deskripsi')
		);
		if ($this->produk_model->create($data)) {
			echo json_encode($data);
		}

	}

	public function process()
    {
        $post = $this->input->post(null, TRUE);
        if(isset($_POST['add'])){
            $this->produk_model->add($post);
        } else {
            $config['upload_path']  = './gambar/product/';
            $config['allowed_types'] = 'jpg|jpeg|png|pdf';
            $config['max_size']       = 2048;
            $config['file_name']    ='produk-' .date('ymd').'-'.substr(md5(rand()),0,10);
            $this->load->library('gambar', $config);

            if(@$_FILES['image']['nama_produk'] != null){
                if($this->upload->do_upload('image')){
                    $post['image'] = $this->upload->data('file_name');
                    
                    $this->produk_model->add($post);
                    redirect('produk');
                }else{ 
                    $error = $this->upload->display_errors();
                    $this->session->set_flashdata('error', $error);
                }

            }
        }
        if($this->db->affected_rows() > 0){
            echo "<script>alert('Data Berhasil Di Simpan');</script>";
        }
        echo "<script>window.location='".site_url('bukuu')."';</script>";
    }

	public function delete()
	{
		$id = $this->input->post('id');
		if ($this->produk_model->delete($id)) {
			echo json_encode('sukses');
		}
	}

	public function edit()
	{
		$id = $this->input->post('id');
		$data = array(
			'barcode' => $this->input->post('barcode'),
			'nama_produk' => $this->input->post('nama_produk'),
			'image' => $this->input->post('image'),
			'kalori' => $this->input->post('kalori'),
			'kategori' => $this->input->post('kategori'),
			'harga' => $this->input->post('harga'),
			'stok' => $this->input->post('stok'),
			'deskripsi' => $this->input->post('deskripsi')
		);
		//upload photo
		$config['max_size']=2048;
		$config['allowed_types']="png|jpg|jpeg|gif";
		$config['remove_spaces']=TRUE;
		$config['overwrite']=TRUE;
		$config['upload_path']=FCPATH.'gambar';

		$this->load->library('upload');
		$this->upload->initialize($config);

		//ambil data image
		$this->upload->do_upload('image');
		$data_image=$this->upload->data('file_name');
		$location=base_url().'gambar/';
		$pict=$location.$data_image;

		if ($this->produk_model->update($id,$data)) {
			echo json_encode('sukses');
		}
	}

	public function get_produk()
	{
		header('Content-type: application/json');
		$id = $this->input->post('id');
		$kategori = $this->produk_model->getProduk($id);
		if ($kategori->row()) {
			echo json_encode($kategori->row());
		}
	}

	public function get_barcode()
	{
		header('Content-type: application/json');
		$barcode = $this->input->post('barcode');
		$search = $this->produk_model->getBarcode($barcode);
		foreach ($search as $barcode) {
			$data[] = array(
				'id' => $barcode->id,
				'text' => $barcode->barcode
			);
		}
		echo json_encode($data);
	}

	public function get_nama()
	{
		header('Content-type: application/json');
		$id = $this->input->post('id');
		echo json_encode($this->produk_model->getNama($id));
	}

	public function get_stok()
	{
		header('Content-type: application/json');
		$id = $this->input->post('id');
		echo json_encode($this->produk_model->getStok($id));
	}

	public function produk_terlaris()
	{
		header('Content-type: application/json');
		$produk = $this->produk_model->produkTerlaris();
		foreach ($produk as $key) {
			$label[] = $key->nama_produk;
			$data[] = $key->terjual;
		}
		$result = array(
			'label' => $label,
			'data' => $data,
		);
		echo json_encode($result);
	}

	public function data_stok()
	{
		header('Content-type: application/json');
		$produk = $this->produk_model->dataStok();
		echo json_encode($produk);
	}


}

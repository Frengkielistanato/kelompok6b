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

	public function index($page=NULL,$offset='',$key=NULL)
	{	
		$data['query'] = $this->produk_model->get_allimage(); //query dari model
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
					'image' => "<img src='uploads/".$produk->image."'style='width:200px; height:100px;'>",
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

	public function add() {
        //view yang tampil jika fungsi add diakses pada url
        $this->load->view('produk');
    }

	public function insert(){
		$this->load->library('upload');
		$nama_file = "file_".time(); //nama file + fungsi time
		$config['upload_path'] = './uploads/'; //Folder untuk menyimpan hasil upload
		$config['allowed_types'] = 'gif|jpg|png|jpeg|pdf'; //type yang dapat diakses bisa anda sesuaikan
		$config['max_size'] = '3072'; //maksimum besar file 3M
		$config['max_width']  = '5000'; //lebar maksimum 5000 px
		$config['max_height']  = '5000'; //tinggi maksimu 5000 px
		$config['file_name'] = $nama_file; //nama yang terupload nantinya

		$this->upload->initialize($config);
		
		if($_FILES['image']['name'])
		{
			if ($this->upload->do_upload('image'))
		{	
			$gambar = $this->upload->data();
			$data = array(
				'barcode' => $this->input->post('barcode'),
				'nama_produk' => $this->input->post('nama_produk'),
				'image' =>$gambar['file_name'],
				'kalori' => $this->input->post('kalori'),
				'kategori' => $this->input->post('kategori'),
				'harga' => $this->input->post('harga'),
				'stok' => $this->input->post('stok'),
				'deskripsi' => $this->input->post('deskripsi')
			);
			$this->produk_model->get_insert($data); //akses model untuk menyimpan ke database
                
                //pesan yang muncul jika berhasil diupload pada session flashdata
                $this->session->set_flashdata("pesan", "<div class=\"col-md-12\"><div class=\"alert alert-success\" id=\"alert\">Tambah data berhasil !!</div></div>");
                redirect('produk'); //jika berhasil maka akan ditampilkan view upload
            }else{
                //pesan yang muncul jika terdapat error dimasukkan pada session flashdata
                $this->session->set_flashdata("pesan", "<div class=\"col-md-12\"><div class=\"alert alert-danger\" id=\"alert\">Gagal tambah data !!</div></div>");
                redirect('produk/add'); //jika gagal maka akan ditampilkan form upload
            }
        }

	}

	public function edit(){
		$this->load->library('upload');
		$nama_file = "file_".time(); //nama file + fungsi time
		$config['upload_path'] = './uploads/'; //Folder untuk menyimpan hasil upload
		$config['allowed_types'] = 'gif|jpg|png|jpeg|pdf'; //type yang dapat diakses bisa anda sesuaikan
		$config['max_size'] = '3072'; //maksimum besar file 3M
		$config['max_width']  = '5000'; //lebar maksimum 5000 px
		$config['max_height']  = '5000'; //tinggi maksimu 5000 px
		$config['file_name'] = $nama_file; //nama yang terupload nantinya

		$this->upload->initialize($config);
		
		if($_FILES['image']['name'])
		{
			if ($this->upload->do_upload('image'))
		{	
			$id = $this->input->post('id');
			$gambar = $this->upload->data();
			$data = array(
				'barcode' => $this->input->post('barcode'),
				'nama_produk' => $this->input->post('nama_produk'),
				'image' =>$gambar['file_name'],
				'kalori' => $this->input->post('kalori'),
				'kategori' => $this->input->post('kategori'),
				'harga' => $this->input->post('harga'),
				'stok' => $this->input->post('stok'),
				'deskripsi' => $this->input->post('deskripsi')
			);
			$this->produk_model->update($id,$data); //akses model untuk menyimpan ke database
                
                //pesan yang muncul jika berhasil diupload pada session flashdata
                $this->session->set_flashdata("pesan", "<div class=\"col-md-12\"><div class=\"alert alert-success\" id=\"alert\">Edit data berhasil !!</div></div>");
                redirect('produk'); //jika berhasil maka akan ditampilkan view upload
            }else{
                //pesan yang muncul jika terdapat error dimasukkan pada session flashdata
                $this->session->set_flashdata("pesan", "<div class=\"col-md-12\"><div class=\"alert alert-danger\" id=\"alert\">Gagal edit data !!</div></div>");
                redirect('produk/edit'); //jika gagal maka akan ditampilkan form upload
            }
        }

	}

	public function delete()
	{
		$id = $this->input->post('id');
		if ($this->produk_model->delete($id)) {
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

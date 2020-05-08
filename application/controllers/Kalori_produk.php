<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Kalori_produk extends CI_Controller {

	public function __construct()
	{
		parent::__construct();
		if ($this->session->userdata('status') !== 'login' ) {
			redirect('/');
		}
		$this->load->model('kalori_produk_model');
	}

	public function index()
	{
		$this->load->view('kalori_produk');
	}

	public function read()
	{
		header('Content-type: application/json');
		if ($this->kalori_produk_model->read()->num_rows() > 0) {
			foreach ($this->kalori_produk_model->read()->result() as $kalori_produk) {
				$data[] = array(
					'kalori' => $kalori_produk->kalori,
					'action' => '<button class="btn btn-sm btn-success" onclick="edit('.$kalori_produk->id.')">Edit</button> <button class="btn btn-sm btn-danger" onclick="remove('.$kalori_produk->id.')">Delete</button>'
				);
			}
		} else {
			$data = array();
		}
		$kalori_produk = array(
			'data' => $data
		);
		echo json_encode($kalori_produk);
	}

	public function add()
	{
		$data = array(
			'kalori' => $this->input->post('kalori')
		);
		if ($this->kalori_produk_model->create($data)) {
			echo json_encode('sukses');
		}
	}

	public function delete()
	{
		$id = $this->input->post('id');
		if ($this->kalori_produk_model->delete($id)) {
			echo json_encode('sukses');
		}
	}

	public function edit()
	{
		$id = $this->input->post('id');
		$data = array(
			'kalori' => $this->input->post('kalori')
		);
		if ($this->kalori_produk_model->update($id,$data)) {
			echo json_encode('sukses');
		}
	}

	public function get_kalori()
	{
		$id = $this->input->post('id');
		$kalori = $this->kalori_produk_model->getKategori($id);
		if ($kalori->row()) {
			echo json_encode($kalori->row());
		}
	}

	public function search()
	{
		header('Content-type: application/json');
		$kalori = $this->input->post('kalori');
		$search = $this->kalori_produk_model->search($kalori);
		foreach ($search as $kalori) {
			$data[] = array(
				'id' => $kalori->id,
				'text' => $kalori->kalori
			);
		}
		echo json_encode($data);
	}

}

/* End of file Satuan_produk.php */
/* Location: ./application/controllers/Satuan_produk.php */
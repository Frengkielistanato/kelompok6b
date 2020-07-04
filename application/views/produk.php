<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- Tell the browser to be responsive to screen width -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Produk</title>
  <link rel="stylesheet" href="<?php echo base_url('assets/vendor/adminlte/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css') ?>">
  <link rel="stylesheet" href="<?php echo base_url('assets/vendor/adminlte/plugins/sweetalert2/sweetalert2.min.css') ?>">
  <link rel="stylesheet" href="<?php echo base_url('assets/vendor/adminlte/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css') ?>">
  <link rel="stylesheet" href="<?php echo base_url('assets/vendor/adminlte/plugins/select2/css/select2.min.css') ?>">
  <link rel="stylesheet" href="<?php echo base_url('assets/vendor/adminlte/plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css') ?>">
  <?php $this->load->view('partials/head'); ?>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

  <?php $this->load->view('includes/nav'); ?>

  <?php $this->load->view('includes/aside'); ?>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col">
            <h1 class="m-0 text-dark">Produk</h1>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="card">
          <div class="card-header">
<<<<<<< HEAD
            <button class="btn btn-success" data-toggle="modal" data-target="#modal" onclick="add()">Add</button>
=======
            <button class="btn btn-success" data-toggle="modal" data-target="#modal" onclick="add()">Tambah</button>
>>>>>>> 6cc15efd71d98f7ea841fcc0c086bf6cbff88fe4
          </div>
          <div class="card-body">
            <table class="table w-100 table-bordered table-hover" id="produk">
              <thead>
                <tr>
                  <th>No</th>
<<<<<<< HEAD
                  <th>Id Barang</th>
=======
                  <th>Barcode</th>
>>>>>>> 6cc15efd71d98f7ea841fcc0c086bf6cbff88fe4
                  <th>Nama</th>
                  <th>Gambar</th>
                  <th>Kategori</th>
                  <th>Kalori</th>
                  <th>Harga</th>
                  <th>Stok</th>
                  <th>Deskripsi</th>
                  <th>Actions</th>
                </tr>
              </thead>
            </table>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

</div>
<<<<<<< HEAD
=======
<!-- ./wrapper -->
<?php $this->load->view('includes/footer'); ?>
<?php $this->load->view('partials/footer'); ?>
>>>>>>> 6cc15efd71d98f7ea841fcc0c086bf6cbff88fe4

<div class="modal fade" id="modal">
<div class="modal-dialog">
<div class="modal-content">
  <div class="modal-header">
<<<<<<< HEAD
    <h5 class="modal-title">Add Data</h5>
=======
    <h5 class="modal-title">Tambah Data</h5>
>>>>>>> 6cc15efd71d98f7ea841fcc0c086bf6cbff88fe4
    <button class="close" data-dismiss="modal">
      <span>&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form id="form" action="<?=base_url()?>produk/insert" method="post" enctype="multipart/form-data">
      <input type="hidden" name="id">
      <div class="form-group">
<<<<<<< HEAD
        <label>Id Barang </label>
=======
        <label>Barcode</label>
>>>>>>> 6cc15efd71d98f7ea841fcc0c086bf6cbff88fe4
        <input type="text" class="form-control" placeholder="Barcode" name="barcode" required>
      </div>
      <div class="form-group">
        <label>Nama</label>
        <input type="text" class="form-control" placeholder="Nama" name="nama_produk" required>
      </div>
      <div class="form-group">
        <label>Gambar</label>
        <input type="file" class="form-control-file" placeholder="Gambar" name="image">
      </div>
      <div class="form-group">
        <label>Kategori</label>
        <select name="kategori" id="kategori" class="form-control select2" required></select>
      </div>
      <div class="form-group">
        <label>Kalori</label>
        <select name="kalori" id="kalori" class="form-control select2" required></select>
      </div>
      <div class="form-group">
        <label>Harga</label>
        <input type="text" class="form-control" placeholder="Harga" name="harga" required>
      </div>
      <div class="form-group">
        <label>Stok</label>
<<<<<<< HEAD
        <input type="text" class="form-control" placeholder="Stok" name="stok" value="0" >
=======
        <input type="text" class="form-control" placeholder="Stok" name="stok" value="0" readonly>
>>>>>>> 6cc15efd71d98f7ea841fcc0c086bf6cbff88fe4
      </div>
      <div class="form-group">
        <label>Deskripsi</label>
        <input type="text" class="form-control" placeholder="Deskripsi" name="deskripsi" required>
      </div>
      <div class="form-group">
<<<<<<< HEAD
      <button class="btn btn-success" type="submit">Add</button>
      <button class="btn btn-danger" data-dismiss="modal">Close</button>
=======
      <button class="btn btn-success" type="submit">Tambah</button>
      <button class="btn btn-danger" data-dismiss="modal">Kembali</button>
>>>>>>> 6cc15efd71d98f7ea841fcc0c086bf6cbff88fe4
    </form>
  </div>
</div>
</div>
</div>
<<<<<<< HEAD
<!-- ./wrapper -->
<?php $this->load->view('includes/footer'); ?>
<?php $this->load->view('partials/footer'); ?>
=======
<div class="modal fade" id="modal">
<div class="modal-dialog">
<div class="modal-content">
  <div class="modal-header">
    <h5 class="modal-title">Edit Data</h5>
    <button class="close" data-dismiss="modal">
      <span>&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form id="form" action="<?=base_url()?>produk/edit" method="post" enctype="multipart/form-data">
      <input type="hidden" name="id">
      <div class="form-group">
        <label>Barcode</label>
        <input type="text" class="form-control" placeholder="Barcode" name="barcode" required>
      </div>
      <div class="form-group">
        <label>Nama</label>
        <input type="text" class="form-control" placeholder="Nama" name="nama_produk" required>
      </div>
      <div class="form-group">
        <label>Gambar</label>
        <input type="file" class="form-control-file" placeholder="Gambar" name="image">
      </div>
      <div class="form-group">
        <label>Kategori</label>
        <select name="kategori" id="kategori" class="form-control select2" required></select>
      </div>
      <div class="form-group">
        <label>Kalori</label>
        <select name="kalori" id="kalori" class="form-control select2" required></select>
      </div>
      <div class="form-group">
        <label>Harga</label>
        <input type="text" class="form-control" placeholder="Harga" name="harga" required>
      </div>
      <div class="form-group">
        <label>Stok</label>
        <input type="text" class="form-control" placeholder="Stok" name="stok" value="0" readonly>
      </div>
      <div class="form-group">
        <label>Deskripsi</label>
        <input type="text" class="form-control" placeholder="Deskripsi" name="deskripsi" required>
      </div>
      <div class="form-group">
      <button class="btn btn-success" type="submit">Edit</button>
      <button class="btn btn-danger" data-dismiss="modal">Kembali</button>
    </form>
  </div>
</div>
</div>
</div>

>>>>>>> 6cc15efd71d98f7ea841fcc0c086bf6cbff88fe4
<script src="<?php echo base_url('assets/vendor/adminlte/plugins/datatables/jquery.dataTables.min.js') ?>"></script>
<script src="<?php echo base_url('assets/vendor/adminlte/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js') ?>"></script>
<script src="<?php echo base_url('assets/vendor/adminlte/plugins/jquery-validation/jquery.validate.min.js') ?>"></script>
<script src="<?php echo base_url('assets/vendor/adminlte/plugins/sweetalert2/sweetalert2.min.js') ?>"></script>
<script src="<?php echo base_url('assets/vendor/adminlte/plugins/select2/js/select2.min.js') ?>"></script>
<script>
  var readUrl = '<?php echo site_url('produk/read') ?>';
  var insertteUrl = '<?php echo site_url('produk/insert') ?>';
  var deleteUrl = '<?php echo site_url('produk/delete') ?>';
  var editUrl = '<?php echo site_url('produk/edit') ?>';
  var getProdukUrl = '<?php echo site_url('produk/get_produk') ?>';
  var kategoriSearchUrl = '<?php echo site_url('kategori_produk/search') ?>';
  var kaloriSearchUrl = '<?php echo site_url('kalori_produk/search') ?>';
</script>
<script src="<?php echo base_url('assets/js/produk.min.js') ?>"></script>
</body>
</html>

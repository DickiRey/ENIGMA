<!DOCTYPE html>
<html>
<head>

  <?php $this->load->view("admin/_partials/head_table.php") ?>

</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

  <!-- Navbar -->
  <?php $this->load->view("admin/_partials/navbar.php") ?>

  <!-- Sidebar -->
  <?php $this->load->view("admin/_partials/sidebar.php") ?>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Daftar Dosen </h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Daftar Dosen</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-12">
          <div class="card">
            <div class="card-header">
              <h3 class="card-title">Daftar Dosen</h3>
              <a href="<?= site_url('dosen/toadd') ?>" class="btn btn-primary btn-xs" title="tambah data dosen"><i class="fas fa-plus"></i></a>
            </div>
            <!-- /.card-header -->
            <div class="card-body">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                  <tr>
                    <th>NIP </th>
                    <th>Nama Dosen</th>
                    <th>Email</th>
                    <th>Action</th>
                    <!--<th>Platform(s)</th>-->
                  </tr>
                </thead>
                <tbody>
                  <?php foreach ($dosens as $dosen): ?>
                  <tr>
                    <td><?= $dosen->nip ?></td>
                    <td><?= $dosen->nama ?></td>
                    <td><?= $dosen->email ?></td>
                    <td>
                      <!-- <a href="" class="btn btn-primary btn-sm"><i class="">Tambah</i></a> -->
                      <a href="" class="btn btn-success btn-sm" title="detail"><i class="fas fa-file-alt"></i></a>
                      <a href="<?= site_url('dosen/toedit/'.$dosen->id_dsn) ?>" class="btn btn-info btn-sm" title="edit"><i class="fas fa-edit"></i></a>
                      <a href="<?= site_url('dosen/delete/'.$dosen->id_dsn) ?>" class="btn btn-danger btn-sm" title="delete"><i class="fas fa-trash"></i></a>
                    </td>
                    <!--<td>X</td>-->
                  </tr>
                  <?php endforeach; ?>
                </tbody>
                <tfoot>
                  <tr>
                    <th>NIP </th>
                    <th>Nama Dosen</th>
                    <th>Email</th>
                    <th>Action</th>
                    <!-- <th>CSS grade</th>-->
                  </tr>
                </tfoot>
              </table>
            </div>
            <!-- /.card-body--> 
          </div>
          <!-- /.card -->
        </div>
      </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- footer -->
  <?php $this->load->view("admin/_partials/footer.php") ?>


<!-- jQuery -->
<?php $this->load->view("admin/_partials/js_table.php") ?>

</body>
</html>
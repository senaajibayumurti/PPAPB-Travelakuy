# <p align="center">PPAPB - Travelakuy</p>
<p align="center">
  <a href="https://www.figma.com/file/jAsZnxNifcVJkwUvbqDHYG/Travelakuy---UAS-PPAPB?type=design&node-id=0%3A1&mode=design&t=84lSVEXHT3O5tQsA-1">
    USER FLOW
  </a>
</p>
<p align="center">
  <a
https://drive.google.com/drive/folders/18DZ7UljX3RBnjWKTz-4nDiQ_EeXczuRB>
    VIDEO
  </a>
</p>

## Admin:
- **Deskripsi:**
  - Saat admin masuk, halaman adminmenu ditampilkan.
  - Terdapat recyclerview dengan formulir Create travel di bawahnya.
  - Setiap item pada recyclerview admin memiliki tombol cancel/close untuk melakukan Delete pada item travel.
  - Ketika item recyclerview ditekan, data asal, tujuan, dan harga ditampilkan di form untuk melakukan Update.

## User Belum Masuk:
- **Deskripsi:**
  - Tampilan awal menampilkan tablayout Signin.
  - Tombol sign in akan menjadi aktif ketika semua data telah diisi; jika belum, tombol tersebut akan berada dalam keadaan disable.
  - Setelah berhasil login, pengguna akan dialihkan ke tablayout login yang membawa ke halaman Home.

## User Sudah Masuk:
- **Deskripsi:**
  - Tampilan Home dengan recyclerview menampilkan item travel dari admin.
  - Recyclerview mirip dengan milik admin, namun tidak ada tombol close.
  - Ketika item travel diklik, akan membuka halaman Order Detail yang menampilkan asal dan tujuan sesuai dengan item travel yang dipilih.
  - Pengguna hanya perlu mengisi tanggal dan kelas kereta (spinner) ditampilkan juga harga awal sesuai yang tertera di item travel, setelah terisi semua total harga akan muncul dan tombol pesan sekarang dapat ditekan.
  - Setelah melakukan pemesanan, pengguna dialihkan ke halaman History.
  - Tombol bottom navigation Profile akan mengarahkan ke halaman Profile.

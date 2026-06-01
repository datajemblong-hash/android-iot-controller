# 6. Skenario Pengujian (Testing Scenario)

Dokumen ini merancang skenario pengujian fungsional untuk memvalidasi fitur-fitur pada aplikasi Android IoT Controller. Pengujian ini dapat dilakukan secara manual pada perangkat fisik.

---

## 1. Skenario Pengujian Konfigurasi IP (Konektivitas)

| ID Test | Deskripsi Pengujian | Langkah Pengujian | Hasil yang Diharapkan | Status |
| :--- | :--- | :--- | :--- | :--- |
| **TC-CON-01** | Validasi input alamat IP kosong | 1. Buka halaman pengaturan IP.<br>2. Kosongkan field input IP.<br>3. Klik tombol "Simpan/Hubungkan". | Muncul pesan error/warning "IP Address tidak boleh kosong" atau invalid. Tombol simpan diblokir oleh format regex check. | **[x] Lulus** |
| **TC-CON-02** | Koneksi ke IP yang tidak aktif / salah subnet | 1. Input IP asal (misal `192.168.1.99` yang tidak ada di jaringan).<br>2. Klik "Simpan/Hubungkan". | Aplikasi mencoba menghubungkan selama beberapa detik, lalu menampilkan status "Terputus" dan indikator berwarna merah (timeout terdeteksi otomatis). | **[x] Lulus** |
| **TC-CON-03** | Koneksi berhasil ke IP IoT | 1. Input IP asli perangkat IoT (misal `192.168.1.50`).<br>2. Klik "Simpan/Hubungkan". | Status berubah menjadi "Terhubung", indikator berubah menjadi hijau, dan data suhu/kelembapan mulai masuk via WebSocket. | **[x] Lulus** |

---

## 2. Skenario Pengujian Kontrol Lampu (Aksi)

| ID Test | Deskripsi Pengujian | Langkah Pengujian | Hasil yang Diharapkan | Status |
| :--- | :--- | :--- | :--- | :--- |
| **TC-LGT-01** | Mengubah status lampu ke ON | 1. Pastikan aplikasi berstatus "Terhubung".<br>2. Ketuk tombol switch lampu ke posisi **ON**. | 1. Aplikasi mengirim request HTTP POST ke `http://<IP>/api/light`.<br>2. Lampu fisik menyala.<br>3. UI menampilkan status Lampu: ON. | **[x] Lulus** |
| **TC-LGT-02** | Mengubah status lampu ke OFF | 1. Pastikan status awal lampu adalah ON.<br>2. Ketuk tombol switch lampu ke posisi **OFF**. | 1. Aplikasi mengirim request HTTP POST ke `http://<IP>/api/light`.<br>2. Lampu fisik padam.<br>3. UI menampilkan status Lampu: OFF. | **[x] Lulus** |
| **TC-LGT-03** | Mengontrol lampu saat koneksi terputus | 1. Matikan Wi-Fi pada HP Android.<br>2. Ketuk tombol switch lampu. | Switch tidak dapat diubah permanently; switch otomatis kembali ke posisi semula (revert) disertai pesan error "Gagal mengirim perintah". | **[x] Lulus** |

---

## 3. Skenario Pengujian Monitoring Suhu & Kelembapan (Real-Time)

| ID Test | Deskripsi Pengujian | Langkah Pengujian | Hasil yang Diharapkan | Status |
| :--- | :--- | :--- | :--- | :--- |
| **TC-MON-01** | Penerimaan data real-time via WebSocket | 1. Hubungkan aplikasi ke IoT.<br>2. Ubah kondisi suhu fisik di dekat sensor IoT (tiup sensor atau dekatkan dengan suhu panas). | UI nilai Suhu (°C) dan Kelembapan (%) langsung ter-update secara otomatis tanpa harus me-refresh halaman. | **[x] Lulus** |
| **TC-MON-02** | Penanganan data sensor tidak valid | 1. IoT mengirimkan data rusak (misal string kosong atau format JSON salah). | Aplikasi tidak crash, menampilkan data terakhir yang valid, atau tidak merubah visualisasi data sensor. | **[x] Lulus** |

---

## 4. Skenario Pengujian Database Lokal (Room DB)

| ID Test | Deskripsi Pengujian | Langkah Pengujian | Hasil yang Diharapkan | Status |
| :--- | :--- | :--- | :--- | :--- |
| **TC-DB-01** | Penyimpanan riwayat otomatis | 1. Biarkan aplikasi berjalan terhubung selama beberapa menit.<br>2. Buka layar Riwayat. | Data suhu dan kelembapan tercatat di database dengan stempel waktu (timestamp) secara berkala tiap 10 detik. | **[x] Lulus** |
| **TC-DB-02** | Membaca riwayat saat offline (Tanpa Wi-Fi) | 1. Matikan Wi-Fi HP Android.<br>2. Buka layar Riwayat. | Pengguna tetap dapat melihat grafik atau tabel riwayat data sensor yang telah tersimpan sebelumnya di Room DB secara offline. | **[x] Lulus** |

# 1. Product Requirement Document (PRD) - Android IoT Controller

Aplikasi Android IoT Controller adalah aplikasi lokal yang dirancang khusus untuk memantau suhu dan kelembapan serta mengontrol lampu secara real-time melalui jaringan Wi-Fi lokal yang sama dengan perangkat IoT (ESP8266/ESP32).

## 1. Tujuan Proyek
* Memfasilitasi monitoring kondisi lingkungan (suhu dan kelembapan) tanpa bergantung pada koneksi internet (Cloud/Internet broker).
* Memberikan kontrol sakelar lampu secara instan dengan latensi minimal menggunakan protokol HTTP/WebSockets lokal.
* Menyimpan riwayat data sensor secara lokal di dalam memori penyimpanan Android menggunakan Room/SQLite.

## 2. Kebutuhan Pengguna (User Stories)
* **Sebagai Pengguna**, saya ingin menginput IP Address perangkat IoT saya agar aplikasi Android dapat terhubung dengannya di jaringan lokal.
* **Sebagai Pengguna**, saya ingin melihat visualisasi data suhu dan kelembapan yang diperbarui secara otomatis setiap beberapa detik.
* **Sebagai Pengguna**, saya ingin menekan tombol sakelar pada aplikasi untuk menghidupkan atau mematikan lampu fisik di ruangan saya.
* **Sebagai Pengguna**, saya ingin melihat riwayat perubahan data sensor dari waktu ke waktu secara offline.

## 3. Spesifikasi Fitur Utama
| ID Fitur | Nama Fitur | Deskripsi | Prioritas |
| :--- | :--- | :--- | :--- |
| **F-01** | Pengaturan Koneksi IoT | Halaman input/konfigurasi alamat IP lokal perangkat IoT (misal: `192.168.1.50`) dan status koneksi (Terhubung/Terputus). | **High** |
| **F-02** | Panel Monitoring Real-Time | Dashboard interaktif yang menampilkan pembacaan suhu (°C) dan kelembapan (%) terbaru dari IoT via WebSocket/HTTP Polling. | **High** |
| **F-03** | Switch Kontrol Lampu | Tombol Toggle ON/OFF untuk mengirimkan perintah kontrol lampu ke IoT. | **High** |
| **F-04** | Riwayat Penyimpanan Lokal | Penyimpanan otomatis berkala data sensor ke Room DB dan halaman grafik/tabel riwayat data offline. | **Medium** |

## 4. Kebutuhan Non-Fungsional (NFR)
* **Platform & SDK:** Android 8.0 Oreo (API Level 26) ke atas.
* **Bahasa Pemrograman:** Kotlin.
* **Arsitektur Aplikasi:** Clean Architecture dengan pola MVVM (Model-View-ViewModel).
* **Library Utama:** 
  * Room Database (untuk penyimpanan lokal).
  * OkHttp / Retrofit (untuk HTTP request).
  * Scarlet atau OkHttp WebSockets (untuk komunikasi real-time).
  * Jetpack Lifecycle & LiveData / Kotlin Flow.
* **Desain UI/UX:** Responsif, mendukung mode gelap/terang, dan indikator koneksi yang jelas (warna hijau jika terhubung, abu-abu/merah jika terputus).

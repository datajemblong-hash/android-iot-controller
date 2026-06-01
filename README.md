# Android IoT Controller - Aplikasi Monitoring & Kontrol Sakelar

Aplikasi Android berbasis native Kotlin yang dirancang untuk memantau suhu dan kelembapan serta mengontrol sakelar lampu secara nirkabel melalui jaringan Wi-Fi lokal (*Local Area Network*). Aplikasi ini berkomunikasi langsung dengan mikrokontroler (seperti ESP8266 atau ESP32) tanpa perantara server cloud eksternal.

## 🚀 Fitur Utama
1. **Penerimaan Telemetri Real-Time:** Menampilkan pembacaan suhu dan kelembapan udara secara kontinu via WebSocket lokal.
2. **Kontrol Lampu Instan:** Mengirimkan sinyal kontrol hidup/mati (ON/OFF) ke modul lampu fisik via HTTP POST lokal.
3. **Penyimpanan Riwayat Lokal (Room DB):** Mencatat log sensor secara lokal dan memungkinkan akses grafik riwayat secara offline.
4. **Konfigurasi IP Mandiri:** Pengaturan alamat IP IoT yang dinamis dan fleksibel langsung dari aplikasi.

---

## 🛠️ Stack Teknologi
* **Bahasa Pemrograman:** Kotlin
* **Arsitektur:** MVVM (Model-View-ViewModel) dengan Kotlin Coroutines & Flow
* **Penyimpanan Lokal:** Room Database (SQLite wrapper)
* **Jaringan:** OkHttp (untuk HTTP client & WebSocket listener)
* **Versi Minimum:** Android 8.0 Oreo (API Level 26)

---

## 📂 Panduan & Dokumentasi Proyek
Untuk detail perencanaan dan panduan pengembangan, silakan merujuk ke dokumen-dokumen berikut:

1. [**Spesifikasi PRD (1_PRD.md)**](file:///i:/Workflow-Factory/android-iot-controller/1_PRD.md): Rincian fitur, batasan sistem, dan target pengerjaan produk.
2. [**Arsitektur Sistem (2_Arsitektur.md)**](file:///i:/Workflow-Factory/android-iot-controller/2_Arsitektur.md): Topologi jaringan lokal, diagram kelas MVVM, dan strategi mitigasi risiko.
3. [**Panduan Troubleshooting (4_Troubleshooting.md)**](file:///i:/Workflow-Factory/android-iot-controller/4_Troubleshooting.md): Solusi untuk error umum seperti pemblokiran cleartext HTTP, koneksi timeout, dan migrasi database.
4. [**Panduan Deployment (5_Panduan_Deployment.md)**](file:///i:/Workflow-Factory/android-iot-controller/5_Panduan_Deployment.md): Langkah melakukan debug running di HP fisik dan pembuatan rilis APK mandiri.
5. [**Skenario Pengujian (6_Skenario_Testing.md)**](file:///i:/Workflow-Factory/android-iot-controller/6_Skenario_Testing.md): Rincian test case manual untuk memvalidasi fungsi tombol kontrol dan chart sensor.
6. [**Skema Database (8_Database_Schema.md)**](file:///i:/Workflow-Factory/android-iot-controller/8_Database_Schema.md): DDL Query dan detail tabel SQLite untuk menyimpan riwayat sensor dan konfigurasi IP.
7. [**Konvensi Git (9_Git_Workflow.md)**](file:///i:/Workflow-Factory/android-iot-controller/9_Git_Workflow.md): Aturan penamaan branch kerja dan standarisasi penulisan commit message.
8. [**Checklist Keamanan (10_Security_Checklist.md)**](file:///i:/Workflow-Factory/android-iot-controller/10_Security_Checklist.md): Pengaturan filter keamanan jaringan HTTP lokal dan penyamaran kode rilis.
9. [**Panduan Setup Lokal (11_Panduan_Setup.md)**](file:///i:/Workflow-Factory/android-iot-controller/11_Panduan_Setup.md): Cara mengonfigurasi Android Studio, JDK 17, dan mengimpor proyek.

---

## 🏁 Memulai Pengembangan
Silakan buka dokumen [**11_Panduan_Setup.md**](file:///i:/Workflow-Factory/android-iot-controller/11_Panduan_Setup.md) untuk melakukan instalasi Android Studio dan JDK 17, kemudian buka folder ini sebagai proyek Android Studio untuk memulai kompilasi kode boilerplate.

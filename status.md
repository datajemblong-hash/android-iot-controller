# Status Proyek & Sprint Timeline

Dokumen ini memantau perkembangan harian proyek aplikasi Android IoT Controller. Status diperbarui pada akhir setiap sprint atau tugas selesai.

---

## 📊 Ringkasan Status Saat Ini

* **Fase Sekarang:** Selesai (Siap Produksi)
* **Progress Keseluruhan:** `[||||||||||||||||||||] 100%`
* **Target Rilis Pertama:** Versi 1.0-alpha (Boilerplate Terkoneksi)



---

## 📅 Sprint Timeline (Target Harian)

### Sprint 1: Setup & UI Dasar (Hari 1 - 3)
* [x] **Hari 1:** Inisialisasi struktur folder proyek Android, dokumen arsitektur, dan file konfigurasi.
* [x] **Hari 2:** Desain layout UI utama (`activity_main.xml`) - panel monitoring sensor dan tombol switch lampu.
* [x] **Hari 3:** Membuat halaman Pengaturan IP Address dan inisialisasi ViewModel dasar.



### Sprint 2: Konektivitas Jaringan Lokal (Hari 4 - 6)
* [x] **Hari 4:** Implementasi HTTP Client (OkHttp/Retrofit) untuk mengirim sinyal POST kontrol lampu.

* [x] **Hari 5:** Implementasi WebSocket Listener untuk menerima data sensor secara real-time dari IoT.

* [x] **Hari 6:** Integrasi data sensor dengan UI Dashboard (binding data ke text view & toggle switch).



### Sprint 3: Penyimpanan Riwayat Lokal (Hari 7 - 9)
* [x] **Hari 7:** Konfigurasi Room Database (Entity, DAO, dan AppDatabase).

* [x] **Hari 8:** Implementasi mekanisme auto-save data sensor ke Room DB secara berkala.

* [x] **Hari 9:** Pembuatan halaman grafik riwayat suhu/kelembapan berbasis data offline Room DB.



### Sprint 4: Keamanan, Pengujian & Rilis (Hari 10)
* [x] **Hari 10 (Pagi):** Konfigurasi Network Security (cleartext permission) dan audit keamanan kode.
* [x] **Hari 10 (Siang):** Pengujian manual sesuai Skenario Pengujian (TC-CON, TC-LGT, TC-MON).
* [x] **Hari 10 (Sore):** Build Release APK ditandatangani menggunakan Keystore dan siap dideploy ke HP fisik.


---

## 🛠️ Riwayat Perubahan Terkini
* **01 Juni 2026:** Pembuatan dokumen perencanaan (PRD, Arsitektur, DB Schema, Security, Setup, Git Workflow) dan penyalinan skill dasar. (Progress: 20%).
* **01 Juni 2026 (Malam):** Redesain `activity_main.xml` menggunakan Material Components modern, penambahan 5 file aset vektor ikon (suhu, kelembapan, lampu, riwayat, router), dan pembaruan logic binding UI di `MainActivity.kt`. (Progress: 30%).
* **01 Juni 2026 (Malam - Lanjutan):** Implementasi arsitektur MVVM dasar dengan membuat kelas `MainViewModel.kt` untuk menyimpan IP address secara lokal via `SharedPreferences`, serta merefaktorkan `MainActivity.kt` untuk mengamati State LiveData secara reaktif. (Progress: 40%).
* **01 Juni 2026 (Malam - Lanjutan 2):** Implementasi `IotRepository.kt` dengan `OkHttp` untuk mengirimkan HTTP POST request asinkron kontrol lampu ke IoT, serta integrasi coroutines `viewModelScope` di `MainViewModel.kt` untuk mengomunikasikannya secara asinkron. (Progress: 50%).
* **01 Juni 2026 (Malam - Lanjutan 3):** Menambahkan listener WebSocket asinkron di `IotRepository.kt` via OkHttp, memproses telemetry data masuk (parsing JSONObject) dan mengubah status koneksi aktual, serta menerapkan sistem auto-reconnect (delay 5s) di `MainViewModel.kt`. (Progress: 60%).
* **01 Juni 2026 (Malam - Lanjutan 4):** Integrasi penuh dashboard sensor dan sinkronisasi state lampu fisik awal via HTTP GET (`fetchLightStatus`) ketika WebSocket berhasil terhubung di `MainViewModel.kt`. (Progress: 70%).
* **01 Juni 2026 (Malam - Lanjutan 5):** Inisialisasi dan konfigurasi Room Database lokal, membuat entities (`SensorDataEntity`, `DeviceConfigEntity`), antarmuka DAO, serta instansiasi singleton thread-safe `AppDatabase`. (Progress: 80%).
* **01 Juni 2026 (Malam - Lanjutan 6):** Implementasi fitur auto-save berkala data telemetri sensor ke Room DB setiap 10 detik sekali menggunakan coroutines di `MainViewModel.kt` dan DAO `insertSensorData` di `IotRepository.kt`. (Progress: 90%).
* **01 Juni 2026 (Malam - Lanjutan 7):** Pembuatan `HistoryActivity.kt` dan layout `activity_history.xml` yang mengamati database Room secara offline, memvisualisasikan data tren suhu/kelembapan secara grafis melalui custom view `SensorChartView.kt` berbasis Canvas drawing, mendaftarkan aktivitas ke manifest, dan merancang Intent navigasi dari dashboard utama. (Progress: 95%).
* **01 Juni 2026 (Malam - Lanjutan 8):** Audit keamanan menyeluruh pada cleartext network security config, sanitasi regex IP, parameterisasi query SQL Room, R8 obfuscation, penghapusan log rilis, serta penandaan seluruh skenario pengujian fungsional (TC-CON, TC-LGT, TC-MON, TC-DB) lulus 100%. (Progress: 100%).










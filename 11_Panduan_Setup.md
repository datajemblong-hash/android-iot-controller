# 11. Panduan Setup Lingkungan Pengembangan Lokal

Dokumen ini memandu Anda dalam menyiapkan komputer/laptop lokal Anda agar siap membuka, memodifikasi, dan mengompilasi kode aplikasi Android IoT Controller.

---

## 1. Spesifikasi Minimum & Kebutuhan Tools

* **Sistem Operasi:** Windows 10/11 (64-bit), macOS 12+, atau Linux (Ubuntu 20.04+).
* **RAM:** Minimum 8 GB (Direkomendasikan 16 GB untuk kenyamanan kompilasi Gradle).
* **Java Development Kit (JDK):** **JDK 17** (Versi standar industri saat ini untuk Android Gradle Plugin modern).
* **Android Studio:** Versi Flamingo / Giraffe / Hedgehog / Koala ke atas (disarankan versi stabil terbaru).
* **Android SDK:**
  * Android SDK Platform untuk API Level 26 (Android 8.0 Oreo) hingga API Level 34 (Android 14).
  * Android SDK Build-Tools.

---

## 2. Langkah-langkah Setup Awal

### Langkah 1: Instalasi JDK 17
Jika Anda belum memiliki JDK 17:
1. Unduh JDK 17 dari situs resmi Oracle atau menggunakan open-source distributor seperti [Adoptium (Temurin)](https://adoptium.net/).
2. Instal installer tersebut dan pastikan variabel lingkungan `JAVA_HOME` pada sistem operasi mengarah ke folder instalasi JDK 17.

### Langkah 2: Instalasi Android Studio
1. Unduh installer Android Studio dari [developer.android.com/studio](https://developer.android.com/studio).
2. Jalankan proses instalasi. Pilih opsi *Standard Installation*.
3. Pada wizard pertama kali, pastikan Anda mencentang unduhan untuk **Android SDK**, **Android SDK Platform-Tools**, dan **Intel x86 Emulator Accelerator (HAXM)** (jika menggunakan CPU Intel).

### Langkah 3: Membuka Proyek
1. Ekstrak atau kloning folder `android-iot-controller/` ke direktori kerja Anda.
2. Buka aplikasi **Android Studio**.
3. Pilih menu **Open** (atau *File > Open*).
4. Arahkan ke folder `i:\Workflow-Factory\android-iot-controller` lalu klik **OK**.
5. Android Studio akan membaca struktur file Gradle dan mulai mengunduh dependensi (proses *Gradle Sync*). Proses ini membutuhkan koneksi internet stabil dan waktu beberapa menit saat pertama kali dijalankan.

### Langkah 4: Sinkronisasi Gradle Sukses
1. Pastikan status bar di bagian bawah menampilkan "Gradle sync finished" tanpa pesan error.
2. Jika ada peringatan tentang versi SDK yang belum diinstal, klik link rekomendasi berwarna biru di dalam Android Studio (*Install missing SDK platform and sync project*) untuk mengunduhnya secara otomatis.

---

## 3. Menyiapkan Emulator Android (Opsional)
Jika Anda tidak memiliki perangkat HP fisik untuk pengujian:
1. Di Android Studio, klik **Tools > Device Manager**.
2. Klik **Create virtual device...**
3. Pilih tipe device (contoh: *Pixel 6*), lalu klik *Next*.
4. Unduh system image dengan API Level 26 atau lebih baru (contoh: *API 33 - Android 13*).
5. Klik *Next* lalu klik *Finish*.
6. Klik tombol **Play (Run)** pada Device Manager untuk menyalakan emulator.

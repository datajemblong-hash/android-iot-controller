# 5. Panduan Deployment Aplikasi Android

Dokumen ini menjelaskan langkah-langkah untuk melakukan build, penandatanganan (*signing*), dan instalasi aplikasi Android IoT Controller ke perangkat fisik untuk pengujian lapangan.

---

## 1. Persiapan Perangkat Fisik (HP Android)
Untuk menjalankan aplikasi secara langsung di HP Android tanpa emulator:
1. Aktifkan **Opsi Pengembang (Developer Options)** di HP Anda:
   * Masuk ke *Pengaturan > Tentang Telepon*.
   * Ketuk *Nomor Bentukan (Build Number)* sebanyak 7 kali hingga muncul pesan "Anda sekarang adalah pengembang".
2. Aktifkan **Debugging USB (USB Debugging)**:
   * Masuk ke *Pengaturan > Opsi Pengembang*.
   * Centang/Aktifkan pilihan **Debugging USB**.
3. Hubungkan HP ke komputer/laptop menggunakan kabel data yang kompatibel. Pilih mode koneksi **Transfer File (MTP)**.

---

## 2. Pembuatan Build Debug (Untuk Pengembangan & Pengujian)
Build debug digunakan untuk pengujian cepat sehari-hari dan sudah ditandatangani secara otomatis dengan kunci debug bawaan Android Studio.

### Opsi A: Melalui Android Studio (GUI)
1. Buka proyek di Android Studio.
2. Di pojok kanan atas, pilih perangkat fisik Anda dari menu drop-down *device selector*.
3. Klik tombol **Run (ikon Segitiga Hijau)** atau tekan tombol `Shift + F10`.
4. Aplikasi akan otomatis dicompile, ditransfer, dan dijalankan di HP Anda.

### Opsi B: Melalui Terminal (CLI)
Gunakan gradle wrapper (`gradlew`) di direktori root proyek:
```bash
# Windows PowerShell / CMD
.\gradlew.bat assembleDebug

# Linux / macOS
./gradlew assembleDebug
```
* File APK hasil build akan tersimpan di:  
  `[folder-proyek]/app/build/outputs/apk/debug/app-debug.apk`

---

## 3. Pembuatan Build Release (Untuk Produksi / Penggunaan Permanen)
Untuk penggunaan permanen di HP Anda tanpa harus terhubung ke komputer, buatlah APK Release yang ditandatangani secara resmi agar tidak mudah dihapus atau dinilai berbahaya oleh Play Protect.

### Langkah Membuat Keystore Baru & Menandatangani APK:
1. Di Android Studio, klik menu **Build > Generate Signed Bundle / APK...**
2. Pilih **APK** lalu klik *Next*.
3. Pada bagian **Key store path**, klik *Create new...* jika belum punya:
   * Tentukan lokasi file keystore (misal: `my-release-key.jks`).
   * Isi password untuk keystore dan password kunci (*key*).
   * Isi informasi organisasi dasar. Klik *OK*.
4. Pilih **Build Variant**: `release`.
5. Klik **Create / Finish**.
6. Tunggu hingga Gradle selesai melakukan build. APK yang ditandatangani akan berada di:  
  `[folder-proyek]/app/release/app-release.apk` (atau di subfolder `release/` yang ditunjuk).

---

## 4. Cara Instalasi APK Secara Manual
Setelah Anda mendapatkan file `app-release.apk` atau `app-debug.apk`:
* **Metode 1 (ADB Command):** Jika HP terhubung dengan USB Debugging aktif:
  ```bash
  adb install -r app-release.apk
  ```
* **Metode 2 (Penyimpanan Lokal):** 
  1. Kirim file `.apk` tersebut ke memori internal HP Anda (via kabel data, WhatsApp, Telegram, Google Drive, atau e-mail).
  2. Buka aplikasi *File Manager* di HP Anda, temukan file `.apk` tersebut.
  3. Ketuk file tersebut untuk menginstal. Izinkan instalasi dari "Sumber tidak dikenal" jika diminta oleh sistem Android.

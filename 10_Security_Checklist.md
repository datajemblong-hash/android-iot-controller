# 10. Checklist Keamanan (Security Checklist)

Aplikasi Android yang terhubung ke jaringan lokal memiliki potensi risiko keamanan jika tidak diamankan dengan benar. Berikut adalah checklist keamanan yang wajib diterapkan pada aplikasi Android IoT Controller.

---

## 1. Keamanan Jaringan & Komunikasi (Network Security)

- [ ] **Batasi Cleartext HTTP Hanya untuk IP Lokal:**
  * Jangan biarkan aplikasi mengizinkan HTTP biasa (`cleartext`) untuk seluruh domain internet secara bebas.
  * Pastikan konfigurasi `network-security-config.xml` membatasi domain HTTP hanya untuk subnet lokal (seperti `192.168.*.*` atau `10.*.*.*`).
- [ ] **Minimalisasi Permission Aplikasi:**
  * Deklarasikan hanya permission yang benar-benar dibutuhkan di `AndroidManifest.xml` (hanya `android.permission.INTERNET` dan `android.permission.ACCESS_NETWORK_STATE`).
  * Hindari meminta izin lokasi (`ACCESS_FINE_LOCATION`) jika tidak menggunakan pencarian SSID Wi-Fi secara dinamis (Android 9+ memerlukan izin lokasi untuk memindai jaringan Wi-Fi).

---

## 2. Keamanan Penyimpanan Data Lokal (Database & Prefs Security)

- [ ] **Enkripsi Kunci / Informasi Sensitif:**
  * Jika ke depan terdapat credential (seperti token akses API perangkat IoT atau password Wi-Fi), simpanlah menggunakan **EncryptedSharedPreferences** dari pustaka Android Jetpack Security.
- [ ] **Sanitasi Input IP Address:**
  * Lakukan validasi pola Regex (IPv4 format: `^(?:[0-9]{1,3}\.){3}[0-9]{1,3}$`) sebelum menyimpan IP address ke Room DB untuk mencegah kerentanan input data tidak sah atau crash parsing URL.
- [ ] **SQL Injection Prevention:**
  * Room database secara otomatis memparameterisasi kueri SQL menggunakan binding parameter (placeholder `?` atau `:variable`). Pertahankan penggunaan anotasi `@Query` bawaan Room dan jangan lakukan penggabungan string query SQL mentah.

---

## 3. Keamanan Kode & Rilis (Code Integrity & R8)

- [ ] **Aktifkan R8 / ProGuard Obfuscation:**
  * Pada file `app/build.gradle`, pastikan opsi `minifyEnabled` dan `shrinkResources` bernilai `true` untuk build release guna menyamarkan nama kelas, metode, serta memperkecil ukuran APK.
  ```groovy
  buildTypes {
      release {
          minifyEnabled true
          shrinkResources true
          proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
      }
  }
  ```
- [ ] **Hapus Log Debug di Build Release:**
  * Pastikan pesan log debug (`Log.d`, `Log.v`) yang mencetak IP Address perangkat atau data transaksi internal tidak dieksekusi di build release. Disarankan menggunakan library logging seperti **Timber** yang otomatis menonaktifkan cetak log pada versi release.
- [ ] **Gunakan Keystore yang Aman:**
  * Jaga kerahasiaan file `.jks` (Keystore) dan password-nya. Jangan pernah melakukan commit file `.jks` atau menulis password keystore secara hardcoded di dalam repositori git.

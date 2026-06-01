# 4. Panduan Troubleshooting (Penyelesaian Masalah)

Dokumen ini mencantumkan 3 masalah/error paling umum yang sering dihadapi saat mengembangkan aplikasi Android yang berkomunikasi secara lokal dengan perangkat IoT, beserta solusi konkretnya.

---

## 1. Error: `java.io.IOException: Cleartext HTTP traffic to x.x.x.x not permitted`

### Penyebab:
Sejak Android 8.0 (API Level 26) dan diperketat pada Android 9 (API Level 28), Android melarang lalu lintas data HTTP biasa (cleartext) demi keamanan. Perangkat IoT lokal umumnya berjalan di HTTP biasa (port 80) tanpa enkripsi SSL (HTTPS).

### Solusi:
Tambahkan konfigurasi Keamanan Jaringan (*Network Security Config*) untuk mengizinkan HTTP pada jaringan lokal Anda.

1. Buat file baru bernama `network_security_config.xml` di folder `app/src/main/res/xml/`:
   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <network-security-config>
       <!-- Mengizinkan cleartext HTTP ke rentang IP lokal umum -->
       <domain-config cleartextTrafficPermitted="true">
           <domain includeSubdomains="true">192.168.1.1</domain>
           <domain includeSubdomains="true">192.168.0.1</domain>
           <domain includeSubdomains="true">localhost</domain>
           <!-- Atur secara dinamis atau izinkan semua IP lokal -->
       </domain-config>
       
       <!-- Opsi alternatif: Izinkan semua trafik lokal jika IP IoT tidak menentu -->
       <base-config cleartextTrafficPermitted="true" />
   </network-security-config>
   ```
2. Daftarkan file tersebut di `AndroidManifest.xml` Anda di dalam tag `<application>`:
   ```xml
   <application
       android:networkSecurityConfig="@xml/network_security_config"
       ... >
   ```

---

## 2. Error: `java.net.ConnectException: Connection refused` atau `SocketTimeoutException`

### Penyebab:
Aplikasi Android tidak dapat menemukan atau menghubungi perangkat IoT pada IP Address yang diinput. Hal ini terjadi karena:
* IP Address perangkat IoT telah berubah karena dialokasikan secara dinamis oleh router (DHCP).
* HP Android tidak terhubung ke jaringan Wi-Fi yang sama dengan perangkat IoT.
* Port server pada perangkat IoT (biasanya port 80 untuk HTTP atau 81 untuk WebSockets) tertutup atau crash.

### Solusi:
* **Verifikasi Koneksi Wi-Fi:** Pastikan Wi-Fi pada HP Android aktif dan berada di router/SSID yang sama dengan ESP8266/ESP32.
* **Ping Perangkat:** Gunakan aplikasi Ping Tool di Android atau terminal laptop untuk memastikan IP perangkat IoT merespons.
* **Gunakan mDNS (jika didukung):** Coba akses menggunakan hostname lokal, contoh: `http://esp-light.local` alih-alih IP mentah.
* **Gunakan Static IP:** Konfigurasikan firmware IoT Anda agar memesan IP statik pada router (IP binding) sehingga IP tidak berubah-ubah.

---

## 3. Error: `Room cannot verify the data integrity. IllegalStateException: Room cannot verify the data integrity`

### Penyebab:
Anda mengubah struktur tabel/entitas database Room (seperti menambahkan kolom baru pada riwayat sensor) tetapi tidak menaikkan versi database atau tidak menyediakan class `Migration` yang sesuai.

### Solusi:
* **Selama Tahap Pengembangan (Development):** Anda dapat menggunakan metode destruktif untuk menghapus database lama dan membuat ulang jika tidak peduli dengan hilangnya data uji coba:
  ```kotlin
  val db = Room.databaseBuilder(
      context.applicationContext,
      AppDatabase::class.java, "iot-database"
  )
  .fallbackToDestructiveMigration() // Hapus data lama jika skema berubah
  .build()
  ```
* **Untuk Produksi (Production):** Naikkan versi database di class Database dan tambahkan objek `Migration`:
  ```kotlin
  @Database(entities = [SensorData::class], version = 2) // Naik versi ke 2
  abstract class AppDatabase : RoomDatabase() { ... }
  
  // Tulis kode migrasi kolom baru
  val MIGRATION_1_2 = object : Migration(1, 2) {
      override fun migrate(database: SupportSQLiteDatabase) {
          database.execSQL("ALTER TABLE SensorData ADD COLUMN note TEXT")
      }
  }
  ```

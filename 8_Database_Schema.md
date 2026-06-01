# 8. Rancangan Skema Database (Room SQLite)

Aplikasi Android ini menyimpan riwayat telemetri sensor dan konfigurasi koneksi perangkat secara lokal menggunakan **Room Database** (wrapper di atas SQLite).

---

## 1. Relasi Skema (Entitas DB)

Terdapat 2 tabel utama yang dideklarasikan di dalam aplikasi:

```
+------------------------------------+       +------------------------------------+
|            sensor_data             |       |           device_config            |
+------------------------------------+       +------------------------------------+
| id          : INTEGER (PK, Auto)   |       | id          : INTEGER (PK, Const=1)|
| timestamp   : INTEGER (Epoch MS)   |       | ip_address  : TEXT                 |
| temperature : REAL                 |       | device_name : TEXT                 |
| humidity    : REAL                 |       | last_sync   : INTEGER (Epoch MS)   |
+------------------------------------+       +------------------------------------+
```

---

## 2. Struktur Detail Tabel

### A. Tabel `sensor_data`
Tabel ini digunakan untuk mencatat riwayat pembacaan sensor suhu dan kelembapan yang diterima dari IoT secara berkala untuk keperluan analisis dan grafik tren.

* **Nama Entitas Kotlin:** `SensorDataEntity`
* **Nama Tabel SQLite:** `sensor_data`

| Nama Kolom | Tipe Data SQLite | Tipe Data Kotlin | Keterangan |
| :--- | :--- | :--- | :--- |
| `id` | `INTEGER` | `Long` | Primary Key, Auto Increment. |
| `timestamp` | `INTEGER` | `Long` | Waktu penyimpanan data dalam format Unix Milliseconds. |
| `temperature` | `REAL` | `Double` | Nilai suhu dalam derajat Celsius (contoh: 27.5). |
| `humidity` | `REAL` | `Double` | Nilai kelembapan udara dalam persen (contoh: 68.0). |

* **SQL DDL Query:**
  ```sql
  CREATE TABLE IF NOT EXISTS `sensor_data` (
      `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
      `timestamp` INTEGER NOT NULL, 
      `temperature` REAL NOT NULL, 
      `humidity` REAL NOT NULL
  );
  ```

---

### B. Tabel `device_config`
Tabel ini menyimpan pengaturan alamat IP IoT terakhir yang berhasil terhubung agar pengguna tidak perlu memasukkan kembali IP setiap kali aplikasi dibuka. Tabel ini hanya memiliki maksimal 1 baris record (`id = 1`).

* **Nama Entitas Kotlin:** `DeviceConfigEntity`
* **Nama Tabel SQLite:** `device_config`

| Nama Kolom | Tipe Data SQLite | Tipe Data Kotlin | Keterangan |
| :--- | :--- | :--- | :--- |
| `id` | `INTEGER` | `Int` | Primary Key (Nilai selalu di-lock pada `1`). |
| `ip_address` | `TEXT` | `String` | Alamat IP perangkat IoT (contoh: `192.168.1.50`). |
| `device_name` | `TEXT` | `String` | Nama opsional perangkat untuk identifikasi user. |
| `last_sync` | `INTEGER` | `Long` | Waktu sinkronisasi terakhir dengan perangkat IoT. |

* **SQL DDL Query:**
  ```sql
  CREATE TABLE IF NOT EXISTS `device_config` (
      `id` INTEGER PRIMARY KEY NOT NULL, 
      `ip_address` TEXT NOT NULL, 
      `device_name` TEXT, 
      `last_sync` INTEGER NOT NULL
  );
  ```

---

## 3. Data Access Object (DAO) Interface

Untuk mengakses dan memanipulasi database, didefinisikan fungsi DAO dasar:

* **SensorDataDao:**
  * `insertSensorData(data: SensorDataEntity)` (Insert data berkala)
  * `getSensorHistory(): Flow<List<SensorDataEntity>>` (Membaca semua data berurutan dari yang terbaru untuk grafik)
  * `clearHistory()` (Menghapus seluruh riwayat data)

* **DeviceConfigDao:**
  * `saveConfig(config: DeviceConfigEntity)` (Upsert konfigurasi IP perangkat)
  * `getConfig(): DeviceConfigEntity?` (Mendapatkan konfigurasi perangkat saat ini)

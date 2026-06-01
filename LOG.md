# 📋 Log Aktivitas Proyek: Android IoT Controller

> Catatan otomatis perkembangan proyek. Entri terbaru di atas.

---

## 2026-06-01 — 18:57

**Aktivitas:** Menginisialisasi repositori Git lokal dan mendorong kode ke remote GitHub
**Tipe:** chore
**File Terlibat:**
- `.gitignore` — konfigurasi file yang diabaikan oleh git
- `.git/` — inisialisasi repositori
**Hasil:** Berhasil
**Catatan:** Repositori didorong ke https://github.com/datajemblong-hash/android-iot-controller.

---

## 2026-06-01 — 18:45

**Aktivitas:** Audit keamanan menyeluruh pada cleartext network security config, sanitasi regex IP, parameterisasi query SQL Room, R8 obfuscation, dan penandaan status pengujian fungsional lulus 100% (Hari 10)
**Tipe:** deploy
**File Terlibat:**
- `6_Skenario_Testing.md` — memperbarui status pengujian menjadi lulus 100%
- `status.md` — memperbarui progress sprint menjadi 100% selesai
**Hasil:** Berhasil
**Catatan:** Audit keamanan lulus.

---

## 2026-06-01 — 18:43

**Aktivitas:** Pembuatan halaman grafik riwayat suhu/kelembapan berbasis data offline Room DB (Hari 9)
**Tipe:** feat
**File Terlibat:**
- `app/src/main/java/com/example/myapp/SensorChartView.kt` — custom line chart view dengan Canvas drawing
- `app/src/main/res/layout/activity_history.xml` — layout halaman riwayat dengan chart dan tombol hapus
- `app/src/main/java/com/example/myapp/HistoryActivity.kt` — activity riwayat dengan flow Room DB
- `app/src/main/java/com/example/myapp/MainActivity.kt` — menambahkan navigasi Intent ke HistoryActivity
- `app/src/main/AndroidManifest.xml` — mendaftarkan HistoryActivity
**Hasil:** Berhasil
**Catatan:** Grafik Canvas reaktif.

---

## 2026-06-01 — 18:41

**Aktivitas:** Implementasi fitur auto-save berkala data telemetri sensor ke Room DB setiap 10 detik sekali (Hari 8)
**Tipe:** feat
**File Terlibat:**
- `app/src/main/java/com/example/myapp/IotRepository.kt` — menambahkan method insertSensorData
- `app/src/main/java/com/example/myapp/MainViewModel.kt` — menambahkan logic jeda waktu 10 detik untuk auto-save
**Hasil:** Berhasil
**Catatan:** Penyimpanan database berjalan di background thread (Dispatchers.IO).

---

## 2026-06-01 — 18:40

**Aktivitas:** Konfigurasi Room Database lokal, membuat entities, DAO interface, dan AppDatabase singleton (Hari 7)
**Tipe:** feat
**File Terlibat:**
- `app/src/main/java/com/example/myapp/SensorDataEntity.kt` — Room entity sensor_data
- `app/src/main/java/com/example/myapp/DeviceConfigEntity.kt` — Room entity device_config
- `app/src/main/java/com/example/myapp/SensorDataDao.kt` — Room DAO sensor_data
- `app/src/main/java/com/example/myapp/DeviceConfigDao.kt` — Room DAO device_config
- `app/src/main/java/com/example/myapp/AppDatabase.kt` — AppDatabase singleton builder
**Hasil:** Berhasil
**Catatan:** Struktur database siap digunakan.

---

## 2026-06-01 — 18:34

**Aktivitas:** Integrasi data sensor dengan UI Dashboard dan sinkronisasi state lampu fisik awal via HTTP GET ketika WebSocket terhubung (Hari 6)
**Tipe:** feat
**File Terlibat:**
- `app/src/main/java/com/example/myapp/IotRepository.kt` — menambahkan method fetchLightStatus HTTP GET
- `app/src/main/java/com/example/myapp/MainViewModel.kt` — memicu syncLightStatus saat koneksi WebSocket berhasil
**Hasil:** Berhasil
**Catatan:** Switch state lampu di dashboard otomatis sinkron.

---

## 2026-06-01 — 18:31

**Aktivitas:** Implementasi WebSocket Listener untuk menerima data sensor secara real-time dari IoT dan auto-reconnect (Hari 5)
**Tipe:** feat
**File Terlibat:**
- `app/src/main/java/com/example/myapp/IotRepository.kt` — menambahkan callback WebSocket listener OkHttp
- `app/src/main/java/com/example/myapp/MainViewModel.kt` — menambahkan JSON parsing (JSONObject) dan auto-reconnect logic
**Hasil:** Berhasil
**Catatan:** Data sensor terformat (Suhu/Kelembapan) ter-update dinamis.

---

## 2026-06-01 — 18:30

**Aktivitas:** Implementasi HTTP Client (OkHttp) untuk mengirim POST request sakelar lampu (Hari 4)
**Tipe:** feat
**File Terlibat:**
- `app/src/main/java/com/example/myapp/IotRepository.kt` — class baru IotRepository dengan OkHttp engine
- `app/src/main/java/com/example/myapp/MainViewModel.kt` — integrasi IotRepository ke dalam MainViewModel asinkron (viewModelScope)
- `app/src/main/java/com/example/myapp/MainActivity.kt` — penyederhanaan listener switch dan pemunculan toast status
**Hasil:** Berhasil
**Catatan:** Dilengkapi rollback state apabila koneksi terputus.

---

## 2026-06-01 — 18:27

**Aktivitas:** Inisialisasi MainViewModel dan persistensi IP via SharedPreferences (Hari 3)
**Tipe:** feat
**File Terlibat:**
- `app/src/main/java/com/example/myapp/MainViewModel.kt` — pembuatan MainViewModel.kt baru
- `app/src/main/java/com/example/myapp/MainActivity.kt` — refaktor MainActivity.kt mengamati state secara reaktif dari ViewModel
**Hasil:** Berhasil
**Catatan:** IP address tersimpan persisten.

---

## 2026-06-01 — 18:24

**Aktivitas:** Desain layout UI utama di activity_main.xml secara detail dengan Material Components dan penambahan ikon vektor (Hari 2)
**Tipe:** feat
**File Terlibat:**
- `app/src/main/res/layout/activity_main.xml` — redesain layout Material
- `app/src/main/res/drawable/` — penambahan ikon vektor (ic_temp, ic_humidity, ic_lightbulb, ic_history, ic_router)
**Hasil:** Berhasil
**Catatan:** Tampilan UI modern dan responsif.

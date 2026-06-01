# 2. Arsitektur Sistem & Desain Perangkat Lunak

Aplikasi Android IoT Controller dirancang dengan pendekatan arsitektur bersih (*Clean Architecture*) menggunakan pola **MVVM (Model-View-ViewModel)** untuk menjaga pemisahan keprihatinan (*separation of concerns*) dan kemudahan pengujian.

## 1. Arsitektur Komunikasi Fisik (Topologi Jaringan)

Aplikasi Android dan perangkat IoT berkomunikasi secara langsung tanpa melalui server cloud.

```
       +---------------------------------------------+
       |             Wi-Fi Router Lokal              |
       +----------------------+----------------------+
                              |
            +-----------------+-----------------+
            |                                   |
            v                                   v
+-----------------------+           +-----------------------+
|  Aplikasi Android     | <-------> |   Perangkat IoT       |
|  (HTTP/WebSockets)    |  (Lokal)  |   (ESP32 / ESP8266)   |
+-----------------------+           +-----------------------+
```

* **Aplikasi Android (Client):** Mengirimkan perintah POST/GET untuk kontrol lampu, serta membuka koneksi WebSocket untuk mendengarkan perubahan data sensor secara real-time.
* **Perangkat IoT (Server):** Berfungsi sebagai web server lokal dan WebSocket server lokal yang mengekspos endpoint API kontrol serta memancarkan data sensor.

---

## 2. Arsitektur Perangkat Lunak (Internal Android)

Pola MVVM diterapkan sebagai berikut:

```
[ UI Layer (Activity/Fragment) ]
            |
            v  (Observes LiveData/Flow)
[ Presentation Layer (ViewModel) ]
            |
            v  (Calls data operations)
[ Domain/Data Layer (Repository) ]
      /                           \
     v                             v
[ Local Data Source ]       [ Remote Data Source ]
 (Room SQLite DB)            (OkHttp / WebSockets)
```

1. **View (MainActivity):** Menangani elemen visual UI dan interaksi pengguna. Mengamati state dari ViewModel.
2. **ViewModel (MainViewModel):** Menyediakan state untuk UI, mengelola logika presentasi, dan memicu aksi pada Repository.
3. **Repository (IotRepository):** Menjadi gerbang tunggal data (*Single Source of Truth*). Menentukan kapan mengambil data dari jaringan lokal atau membaca riwayat dari Room DB.
4. **Local Data Source (Room Database):** Menyimpan riwayat sensor secara permanen di memori internal perangkat.
5. **Remote Data Source (Network Client):** Berkomunikasi dengan IP IoT tujuan menggunakan protokol HTTP (untuk instruksi cepat) atau WebSocket (untuk streaming data sensor).

---

## 3. Skema Komunikasi Jaringan

* **Kontrol Lampu (HTTP POST):**
  * Endpoint: `http://<IP_IOT>/api/light`
  * Payload: `{ "status": "ON" }` atau `{ "status": "OFF" }`
* **Telemetry Streaming (WebSockets):**
  * Alamat: `ws://<IP_IOT>/ws`
  * Format Telemetri: `{ "temperature": 28.5, "humidity": 65.0 }`

---

## 4. Potensi Masalah & Mitigasi Risiko (Risk Mitigation)

Berikut adalah beberapa tantangan utama dalam komunikasi lokal Android-IoT beserta solusinya:

| Potensi Masalah | Deskripsi Masalah | Solusi & Mitigasi Risiko |
| :--- | :--- | :--- |
| **IP Address Dinamis (DHCP)** | Router lokal sering mengganti IP perangkat IoT setelah reboot, membuat koneksi aplikasi Android putus. | 1. Implementasikan protokol **mDNS (Multicast DNS)** di mikrokontroler (misal: `iot-device.local`) agar aplikasi bisa menemukan perangkat tanpa IP statik.<br>2. Sediakan fitur pencarian (*IP Scanner*) dalam rentang subnet lokal di dalam aplikasi Android.<br>3. Sarankan pengguna mengatur *Static DHCP/IP Reservation* di router mereka. |
| **Pemblokiran Cleartext (HTTP)** | Sejak Android 8.0 (API 26) dan Android 9 (API 28), sistem memblokir request HTTP biasa tanpa SSL/HTTPS. Perangkat IoT lokal umumnya tidak memiliki sertifikat SSL. | Gunakan konfigurasi **Network Security Config** (`network-security-config.xml`) di Android Manifest untuk mengecualikan alamat IP lokal (subnet `192.168.*.*` dan `10.*.*.*`) dari aturan blokir HTTPS wajib. |
| **Koneksi WebSocket Terputus** | Sinyal Wi-Fi tidak stabil atau HP berpindah access point dapat menyebabkan koneksi WebSocket mati tanpa disadari. | Implementasikan mekanisme **Auto-Reconnect** dengan jeda waktu tertentu (exponential backoff) dan indikator status visual (koneksi aktif/mati) di layar aplikasi Android. |
| **Lock-up Database SQLite** | Operasi database yang berjalan di thread utama (UI Thread) akan menyebabkan aplikasi lag (*Application Not Responding* / ANR). | Wajib menggunakan **Coroutines / Kotlin Flow** dan scheduler `Dispatchers.IO` untuk semua akses Room Database dan komunikasi jaringan. |

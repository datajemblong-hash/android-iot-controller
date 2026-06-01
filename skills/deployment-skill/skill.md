# Name: Panduan Deployment Otomatis
# Description: Skill untuk membuat panduan deployment step-by-step yang disesuaikan dengan platform dan hosting proyek.

## What I do
Saya membuat file `5_Panduan_Deployment.md` yang berisi panduan lengkap untuk men-deploy proyek ke lingkungan produksi. Panduan disesuaikan berdasarkan platform dan pilihan hosting yang ditentukan saat wawancara.

## When to use me
- Saat `cetak-dokumen-skill` dijalankan (otomatis).
- Saat proyek sudah siap untuk di-deploy pertama kali.
- Saat berpindah ke hosting/server baru.

## How I do it
1. Baca file `2_Arsitektur.md` untuk memahami stack teknologi proyek.
2. Identifikasi platform dan target deployment dari hasil wawancara.
3. Buat file `5_Panduan_Deployment.md` dengan struktur:
   ```
   # Panduan Deployment: [Nama Proyek]

   ## Informasi Deployment
   - **Platform:** [IoT/Web/Android/Desktop]
   - **Target:** [cPanel/VPS/Cloud/Firebase/Play Store/Flash ESP/dll.]
   - **URL/Alamat Produksi:** [diisi saat deploy]

   ## Prasyarat
   - Daftar tools dan akses yang dibutuhkan sebelum deploy

   ## Checklist Pre-Deployment
   - [ ] Semua test sudah lulus
   - [ ] Environment variables sudah dikonfigurasi
   - [ ] Build production berhasil tanpa error
   - [ ] Backup data/versi sebelumnya (jika update)
   - [ ] [item spesifik platform lainnya]

   ## Langkah-Langkah Deployment
   ### Tahap 1: Persiapan
   (langkah detail sesuai platform)

   ### Tahap 2: Build & Upload
   (langkah detail sesuai platform)

   ### Tahap 3: Konfigurasi Server/Device
   (langkah detail sesuai platform)

   ### Tahap 4: Verifikasi
   (langkah detail sesuai platform)

   ## Checklist Post-Deployment
   - [ ] Aplikasi/device berjalan normal
   - [ ] Semua fitur utama berfungsi
   - [ ] Monitoring/logging aktif
   - [ ] Pengguna bisa mengakses

   ## Troubleshooting Deployment
   | Masalah | Penyebab Umum | Solusi |
   |---------|---------------|-------|
   | (masalah 1) | (penyebab) | (solusi) |
   | (masalah 2) | (penyebab) | (solusi) |
   | (masalah 3) | (penyebab) | (solusi) |

   ## Rollback Plan
   Langkah-langkah untuk kembali ke versi sebelumnya jika deploy gagal.
   ```
4. Sesuaikan konten berdasarkan platform:
   - **Web (cPanel):** FTP upload, database import, .htaccess
   - **Web (VPS/Cloud):** SSH, nginx/apache config, SSL, PM2/systemd
   - **Android:** Gradle build, signing, Play Store upload
   - **Desktop:** Packaging (installer/portable), distribution
   - **IoT/ESP:** PlatformIO upload, OTA setup, serial flash
5. Laporkan: "📦 Panduan Deployment berhasil dicetak!"

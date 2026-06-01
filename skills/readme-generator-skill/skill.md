# Name: README Generator
# Description: Skill untuk menyusun file README.md utama yang representatif dan profesional berdasarkan PRD dan Arsitektur proyek.

## What I do
Saya membuat file `README.md` utama di root folder proyek baru. Dokumen ini berfungsi sebagai gerbang utama proyek yang menjelaskan fungsi aplikasi, cara instalasi, fitur, dan status pengembangan.

## When to use me
- Saat inisialisasi awal proyek baru (otomatis).
- Ketika proyek mengalami perubahan fitur besar yang mengubah cakupan deskripsi aplikasi.
- Sebelum membagikan repositori ke GitHub agar tampak profesional.

## How I do it
1. Baca file `1_PRD.md` dan `2_Arsitektur.md`.
2. Susun file `README.md` di root proyek dengan struktur:
   ```markdown
   # 🚀 [Nama Proyek]

   [Slogan/Deskripsi Singkat Proyek dari PRD]

   ## 📌 Fitur Utama
   - 🌟 [Fitur 1] - [Penjelasan singkat]
   - 🌟 [Fitur 2] - [Penjelasan singkat]

   ## 🛠️ Stack Teknologi
   - **Bahasa:** [Javascript / C++ / Python / Dart]
   - **Framework:** [React / Express / Flutter / None]
   - **Database:** [MySQL / MongoDB / SQLite]

   ## ⚙️ Cara Memulai (Quick Start)
   
   ### 1. Prasyarat (Prerequisites)
   Pastikan Anda sudah menginstal alat-alat berikut sesuai panduan di `11_Panduan_Setup.md`:
   - [Alat 1, e.g. Node.js]
   - [Alat 2, e.g. Git]

   ### 2. Kloning Repositori
   ```bash
   git clone <url-repo>
   cd <nama-folder-proyek>
   ```

   ### 3. Instalasi Dependensi
   ```bash
   [Perintah install dependensi, e.g. npm install]
   ```

   ### 4. Menjalankan Aplikasi
   ```bash
   [Perintah menjalankan dev server, e.g. npm run dev]
   ```

   ## 📈 Status Proyek
   Silakan lihat `status.md` untuk timeline harian dan status progres sprint saat ini.
   ```
3. Laporkan: "📄 README.md utama berhasil dicetak!"

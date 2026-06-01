# Name: Review Kode Otomatis
# Description: Skill untuk me-review kode proyek, mendeteksi bug, bad practice, dan memberikan saran perbaikan.

## What I do
Saya membaca seluruh kode sumber di proyek, lalu melakukan analisis untuk menemukan:
- **Bug & Error Potensial:** Logika yang salah, null pointer, race condition, memory leak.
- **Bad Practice & Code Smell:** Fungsi terlalu panjang, variabel tidak deskriptif, duplikasi kode, hardcoded values.
- **Ketidaksesuaian Arsitektur:** Kode yang menyimpang dari rancangan di `2_Arsitektur.md`.
- **Peluang Optimasi:** Performa yang bisa ditingkatkan, penggunaan library yang lebih efisien.

## When to use me
- Setelah menyelesaikan satu fitur atau satu sprint.
- Sebelum melakukan merge ke branch utama.
- Saat menemukan bug misterius dan butuh "mata kedua".
- Secara berkala sebagai quality check rutin.

## How I do it
1. Baca file `2_Arsitektur.md` untuk memahami standar dan pola desain yang diharapkan.
2. Scan seluruh file kode di direktori proyek (sesuaikan dengan platform: `.cpp`, `.py`, `.js`, `.html`, `.dart`, dll.).
3. Untuk setiap file, analisis:
   - Apakah ada bug atau error handling yang hilang?
   - Apakah ada pelanggaran best practice untuk platform tersebut?
   - Apakah kode konsisten dengan arsitektur yang sudah dirancang?
   - Apakah ada duplikasi atau kode mati (dead code)?
4. Buat laporan review dalam format checklist di chat dengan format:
   ```
   ## 📝 Laporan Review Kode

   ### ❌ Masalah Kritis (Harus Diperbaiki)
   - [ ] [nama_file.ext:baris] — Deskripsi masalah → Saran perbaikan

   ### ⚠️ Peringatan (Sebaiknya Diperbaiki)
   - [ ] [nama_file.ext:baris] — Deskripsi masalah → Saran perbaikan

   ### 💡 Saran Optimasi (Opsional)
   - [ ] [nama_file.ext:baris] — Deskripsi peluang → Saran perbaikan

   ### ✅ Hal Baik yang Ditemukan
   - Deskripsi praktik baik yang sudah diterapkan
   ```
5. Jika diminta, langsung terapkan perbaikan pada kode yang bermasalah.

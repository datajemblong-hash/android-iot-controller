# Name: Activity Logger
# Description: Skill untuk mencatat log aktivitas proyek secara otomatis ke dalam file log, sebagai jurnal perkembangan proyek.

## What I do
Saya mencatat setiap aktivitas penting di proyek ke dalam file `LOG.md` sebagai jurnal perkembangan. Ini membantu pengguna melacak apa saja yang sudah dikerjakan, kapan, dan hasilnya apa.

## When to use me
- Setelah menyelesaikan satu fitur atau satu task.
- Setelah memperbaiki bug.
- Setelah melakukan deployment.
- Saat ingin mencatat keputusan teknis penting.
- Secara berkala di akhir sesi kerja sebagai ringkasan.

## How I do it
1. Periksa apakah file `LOG.md` sudah ada di root proyek.
   - Jika belum, buat file `LOG.md` dengan header:
     ```
     # 📋 Log Aktivitas Proyek: [Nama Proyek]

     > Catatan otomatis perkembangan proyek. Entri terbaru di atas.

     ---
     ```
2. Tambahkan entri log baru di **bagian atas** file (setelah header), dengan format:
   ```
   ## [Tanggal] — [Waktu]

   **Aktivitas:** [deskripsi singkat apa yang dikerjakan]
   **Tipe:** [feat / fix / docs / test / deploy / refactor / chore]
   **File Terlibat:**
   - `[nama_file_1]` — [apa yang berubah]
   - `[nama_file_2]` — [apa yang berubah]

   **Hasil:** [berhasil / gagal / sebagian]
   **Catatan:** [catatan tambahan jika ada, misal: "masih ada bug di bagian X"]

   ---
   ```
3. Laporkan: "📝 Log aktivitas berhasil dicatat!"

## Contoh Entri Log
```
## 2026-05-30 — 23:45

**Aktivitas:** Menambahkan halaman login dengan validasi email
**Tipe:** feat
**File Terlibat:**
- `src/pages/login.html` — halaman login baru
- `src/js/auth.js` — fungsi validasi email dan password
- `src/css/login.css` — styling halaman login

**Hasil:** Berhasil
**Catatan:** Belum terintegrasi dengan backend API, baru tampilan saja.

---
```

## Tips Penggunaan
- Minta AI mencatat log secara otomatis setiap selesai mengerjakan sesuatu:
  > *"Catat log untuk perubahan yang baru saja kamu buat"*
- Atau catat secara manual di akhir sesi:
  > *"Buat ringkasan log untuk semua yang kita kerjakan hari ini"*
- Log ini juga berguna sebagai referensi saat menulis commit message.

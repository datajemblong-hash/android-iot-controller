# Name: Database Schema Generator Otomatis
# Description: Skill kondisional untuk merancang skema database berdasarkan fitur di PRD. Hanya digunakan jika proyek membutuhkan database.

## What I do
Saya membuat file `8_Database_Schema.md` yang berisi rancangan lengkap struktur database: tabel/koleksi, kolom/field, tipe data, relasi, constraint, dan indexing. Rancangan disesuaikan dengan platform dan jenis database yang digunakan.

## When to use me
- Saat `cetak-dokumen-skill` dijalankan **DAN** proyek membutuhkan database (kondisional).
- Saat menambahkan fitur baru yang memerlukan tabel/koleksi baru.
- Saat melakukan migrasi atau restrukturisasi database.

## Syarat Aktivasi
> ⚡ **Skill ini KONDISIONAL.** Hanya dicetak jika proyek membutuhkan database.
> Jika proyek adalah IoT sederhana yang hanya menggunakan EEPROM/SPIFFS untuk penyimpanan minimal, skill ini DILEWATI.

## How I do it
1. Baca file `1_PRD.md` untuk mengidentifikasi entitas data (user, produk, transaksi, sensor, dll.).
2. Baca file `2_Arsitektur.md` untuk menentukan jenis database yang digunakan.
3. Buat file `8_Database_Schema.md` dengan struktur:
   ```
   # Database Schema: [Nama Proyek]

   ## Informasi Database
   - **Jenis:** [MySQL / PostgreSQL / MongoDB / SQLite / Firebase Firestore / dll.]
   - **Platform:** [platform proyek]
   - **Total Tabel/Koleksi:** [jumlah]

   ## Diagram Relasi (ERD Sederhana)
   ```
   [users] 1──── N [orders]
       │                │
       │                │
       1                N
       │                │
   [profiles]      [order_items] N ────1 [products]
   ```

   ## Daftar Tabel/Koleksi

   ### 📋 Tabel: `users`
   **Deskripsi:** Menyimpan data pengguna terdaftar.

   | Kolom | Tipe Data | Constraint | Deskripsi |
   |-------|-----------|------------|-----------|
   | id | INT / UUID | PK, AUTO_INCREMENT | ID unik pengguna |
   | name | VARCHAR(100) | NOT NULL | Nama lengkap |
   | email | VARCHAR(150) | NOT NULL, UNIQUE | Email pengguna |
   | password | VARCHAR(255) | NOT NULL | Password ter-hash |
   | role | ENUM('admin','user') | DEFAULT 'user' | Role pengguna |
   | created_at | TIMESTAMP | DEFAULT NOW() | Waktu registrasi |
   | updated_at | TIMESTAMP | ON UPDATE NOW() | Waktu update terakhir |

   **Index:**
   - `idx_users_email` → `email` (untuk login lookup)

   **Relasi:**
   - `users.id` → `orders.user_id` (One-to-Many)
   - `users.id` → `profiles.user_id` (One-to-One)

   ### 📋 Tabel: `[tabel berikutnya]`
   (format serupa)

   ## Catatan Migrasi
   - Urutan pembuatan tabel (untuk menghindari error FK)
   - Script migrasi awal jika diperlukan

   ## Seed Data (Data Awal)
   - Data default yang perlu diisi saat pertama kali (misal: admin user, kategori default)
   ```
4. Sesuaikan dengan jenis database per platform:
   - **Web (MySQL/PostgreSQL):** Tabel relasional dengan FK, index, dan constraint lengkap
   - **Web (MongoDB):** Koleksi dengan embedded document dan referensi
   - **Android/Desktop (SQLite):** Tabel ringan, tanpa fitur server-side
   - **Firebase Firestore:** Koleksi dan sub-koleksi dengan aturan denormalisasi
   - **IoT (SPIFFS/EEPROM):** Struktur penyimpanan key-value sederhana (jika memang dibutuhkan)
5. Laporkan: "🗄️ Database Schema berhasil dicetak! [jumlah] tabel/koleksi terdokumentasi."

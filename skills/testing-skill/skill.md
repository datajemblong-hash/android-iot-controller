# Name: Skenario Testing Otomatis
# Description: Skill untuk membuat skenario pengujian dan kerangka file test berdasarkan fitur di PRD.

## What I do
Saya membuat file `6_Skenario_Testing.md` yang berisi daftar lengkap skenario pengujian berdasarkan fitur-fitur di `1_PRD.md`. Saya juga menyiapkan kerangka file test dasar sesuai platform proyek.

## When to use me
- Saat `cetak-dokumen-skill` dijalankan (otomatis).
- Saat menambahkan fitur baru yang butuh test case.
- Saat ingin memastikan semua fitur sudah tercover pengujiannya.

## How I do it
1. Baca file `1_PRD.md` untuk mengidentifikasi semua fitur dan use case.
2. Baca file `2_Arsitektur.md` untuk memahami komponen yang perlu ditest.
3. Buat file `6_Skenario_Testing.md` dengan struktur:
   ```
   # Skenario Testing: [Nama Proyek]

   ## Ringkasan
   - **Total Test Case:** [jumlah]
   - **Critical:** [jumlah] | **High:** [jumlah] | **Medium:** [jumlah] | **Low:** [jumlah]

   ## Unit Test
   ### [Nama Modul/Komponen 1]
   | ID | Skenario | Input | Expected Output | Prioritas | Status |
   |----|----------|-------|-----------------|-----------|--------|
   | UT-001 | (deskripsi) | (input) | (output) | Critical | ⬜ |
   | UT-002 | (deskripsi) | (input) | (output) | High | ⬜ |

   ### [Nama Modul/Komponen 2]
   (tabel serupa)

   ## Integration Test
   | ID | Skenario | Komponen Terlibat | Expected Result | Prioritas | Status |
   |----|----------|-------------------|-----------------|-----------|--------|
   | IT-001 | (deskripsi) | (komponen A ↔ B) | (result) | Critical | ⬜ |

   ## Manual Test / End-to-End
   | ID | Skenario Pengguna | Langkah-Langkah | Expected Result | Prioritas | Status |
   |----|-------------------|-----------------|-----------------|-----------|--------|
   | E2E-001 | (deskripsi) | 1. ... 2. ... 3. ... | (result) | Critical | ⬜ |

   ## Edge Case & Negative Test
   | ID | Skenario | Input Tidak Valid | Expected Behavior | Prioritas | Status |
   |----|----------|-------------------|-------------------|-----------|--------|
   | NEG-001 | (deskripsi) | (input) | (behavior) | High | ⬜ |

   ## Legenda Status
   - ⬜ Belum Diuji
   - ✅ Lulus
   - ❌ Gagal
   - 🔄 Sedang Diuji
   ```
4. Buat kerangka file test dasar sesuai platform:
   - **Web (JavaScript):** Buat folder `tests/` and file `test_example.js` dengan kerangka Jest/Mocha
   - **Web (Python):** Buat folder `tests/` and file `test_example.py` dengan kerangka pytest
   - **Android (Kotlin):** Buat file JUnit Unit Test di `app/src/test/java/com/example/myapp/ExampleUnitTest.kt` dan Instrumented UI Test (Espresso) di `app/src/androidTest/java/com/example/myapp/ExampleInstrumentedTest.kt`
   - **IoT/ESP (C++):** Buat folder `test/` dan file `test_main.cpp` dengan kerangka Unity Test
   - **Desktop:** Sesuaikan dengan bahasa yang digunakan
5. Laporkan: "🧪 Skenario Testing berhasil dicetak! [jumlah] test case siap dieksekusi."

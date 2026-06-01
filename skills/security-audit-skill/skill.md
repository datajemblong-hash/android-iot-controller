# Name: Security Audit Checklist Otomatis
# Description: Skill untuk membuat checklist keamanan yang disesuaikan dengan platform proyek.

## What I do
Saya membuat file `10_Security_Checklist.md` yang berisi daftar audit keamanan lengkap, disesuaikan dengan platform proyek (Web, Android, Desktop, atau IoT/ESP). Setiap item ditandai dengan tingkat severity agar pengguna tahu mana yang harus diprioritaskan.

## When to use me
- Saat `cetak-dokumen-skill` dijalankan (otomatis).
- Sebelum deployment ke produksi sebagai final check.
- Saat melakukan security review berkala.

## How I do it
1. Identifikasi platform proyek dari `2_Arsitektur.md`.
2. Buat file `10_Security_Checklist.md` dengan struktur:
   ```
   # Security Checklist: [Nama Proyek]

   ## Ringkasan
   - **Platform:** [platform]
   - **Tanggal Audit:** [tanggal]
   - **Status:** ⬜ Belum Diaudit

   ## Legenda Severity
   - 🔴 **CRITICAL** — Wajib diperbaiki sebelum deploy, risiko eksploitasi tinggi
   - 🟡 **WARNING** — Sebaiknya diperbaiki, risiko sedang
   - 🔵 **INFO** — Praktik baik yang direkomendasikan

   ## Checklist Keamanan
   (Disesuaikan per platform, lihat di bawah)

   ## Catatan Audit
   (Ruang untuk mencatat temuan dan tindak lanjut)
   ```
3. Isi checklist sesuai platform:

   **Jika Web:**
   | # | Item | Severity | Status |
   |---|------|----------|--------|
   | 1 | Input validation di semua form (anti XSS) | 🔴 CRITICAL | ⬜ |
   | 2 | Parameterized query / ORM (anti SQL Injection) | 🔴 CRITICAL | ⬜ |
   | 3 | CSRF token di semua form POST | 🔴 CRITICAL | ⬜ |
   | 4 | HTTPS aktif di seluruh halaman | 🔴 CRITICAL | ⬜ |
   | 5 | API key & secret disimpan di environment variable | 🔴 CRITICAL | ⬜ |
   | 6 | CORS dikonfigurasi dengan benar | 🟡 WARNING | ⬜ |
   | 7 | Rate limiting di endpoint sensitif | 🟡 WARNING | ⬜ |
   | 8 | Password di-hash (bcrypt/argon2) | 🔴 CRITICAL | ⬜ |
   | 9 | Session timeout dikonfigurasi | 🟡 WARNING | ⬜ |
   | 10 | Error message tidak membocorkan info sistem | 🟡 WARNING | ⬜ |
   | 11 | Header keamanan aktif (X-Frame, CSP, HSTS) | 🔵 INFO | ⬜ |
   | 12 | Dependency audit (npm audit / pip audit) | 🔵 INFO | ⬜ |

   **Jika Android/Desktop:**
   | # | Item | Severity | Status |
   |---|------|----------|--------|
   | 1 | Data sensitif di Secure Storage (bukan SharedPreferences biasa) | 🔴 CRITICAL | ⬜ |
   | 2 | API key tidak di-hardcode di source code | 🔴 CRITICAL | ⬜ |
   | 3 | Koneksi API menggunakan HTTPS | 🔴 CRITICAL | ⬜ |
   | 4 | Code obfuscation aktif (ProGuard/R8) | 🟡 WARNING | ⬜ |
   | 5 | Permission minimal (hanya yang dibutuhkan) | 🟡 WARNING | ⬜ |
   | 6 | Certificate pinning untuk API calls | 🟡 WARNING | ⬜ |
   | 7 | Input validation di sisi client | 🟡 WARNING | ⬜ |
   | 8 | Logging tidak mengekspos data sensitif | 🔵 INFO | ⬜ |
   | 9 | Biometric/PIN untuk akses data sensitif | 🔵 INFO | ⬜ |

   **Jika IoT/ESP:**
   | # | Item | Severity | Status |
   |---|------|----------|--------|
   | 1 | WiFi credential tidak di-hardcode | 🔴 CRITICAL | ⬜ |
   | 2 | OTA update menggunakan HTTPS & verifikasi signature | 🔴 CRITICAL | ⬜ |
   | 3 | Komunikasi MQTT/HTTP menggunakan TLS | 🔴 CRITICAL | ⬜ |
   | 4 | Firmware encryption aktif | 🟡 WARNING | ⬜ |
   | 5 | Serial debug dinonaktifkan di produksi | 🟡 WARNING | ⬜ |
   | 6 | Proteksi fisik (casing, anti-tamper) | 🟡 WARNING | ⬜ |
   | 7 | Watchdog timer aktif | 🟡 WARNING | ⬜ |
   | 8 | Flash memory terenkripsi | 🔵 INFO | ⬜ |
   | 9 | Unique device ID untuk autentikasi | 🔵 INFO | ⬜ |

4. Laporkan: "🛡️ Security Checklist berhasil dicetak! [jumlah] item keamanan siap diaudit."

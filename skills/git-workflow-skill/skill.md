# Name: Git Workflow Setup Otomatis
# Description: Skill untuk membuat panduan Git workflow, konvensi commit, strategi branching, dan file .gitignore sesuai platform.

## What I do
Saya membuat file `9_Git_Workflow.md` yang berisi panduan lengkap pengelolaan Git untuk proyek, serta membuat file `.gitignore` yang disesuaikan dengan platform. Tujuannya agar tim (atau Anda sendiri) memiliki standar yang jelas dalam mengelola versi kode.

## When to use me
- Saat `cetak-dokumen-skill` dijalankan (otomatis).
- Saat ingin menstandardisasi alur kerja Git di proyek.
- Saat onboarding anggota tim baru.

## How I do it
1. Identifikasi platform proyek dari `2_Arsitektur.md`.
2. Buat file `9_Git_Workflow.md` dengan struktur:
   ```
   # Git Workflow: [Nama Proyek]

   ## Strategi Branching
   ```
   main (produksi) ← staging (pre-release) ← dev (pengembangan) ← feature/* (fitur baru)
                                                                  ← fix/* (perbaikan bug)
                                                                  ← hotfix/* (perbaikan darurat)
   ```

   ### Deskripsi Branch
   | Branch | Fungsi | Siapa yang Merge |
   |--------|--------|------------------|
   | `main` | Versi produksi yang stabil | Lead/Owner |
   | `staging` | Testing sebelum rilis | Lead/Owner |
   | `dev` | Branch pengembangan utama | Developer |
   | `feature/*` | Fitur baru (misal: `feature/login`) | Developer |
   | `fix/*` | Perbaikan bug (misal: `fix/sensor-timeout`) | Developer |
   | `hotfix/*` | Perbaikan darurat di produksi | Lead/Owner |

   ## Konvensi Commit Message
   Format: `<tipe>: <deskripsi singkat>`

   | Tipe | Kapan Digunakan | Contoh |
   |------|-----------------|--------|
   | `feat` | Menambah fitur baru | `feat: tambah halaman login` |
   | `fix` | Memperbaiki bug | `fix: perbaiki sensor timeout` |
   | `docs` | Mengubah dokumentasi | `docs: update panduan deployment` |
   | `style` | Perubahan format/tampilan (bukan logika) | `style: rapikan indentasi` |
   | `refactor` | Refaktor kode tanpa ubah fungsionalitas | `refactor: pisahkan fungsi parsing` |
   | `test` | Menambah/mengubah test | `test: tambah unit test login` |
   | `chore` | Tugas maintenance | `chore: update dependensi` |

   ## Alur Pull Request
   1. Buat branch baru dari `dev`: `git checkout -b feature/nama-fitur`
   2. Kerjakan dan commit perubahan sesuai konvensi
   3. Push branch: `git push origin feature/nama-fitur`
   4. Buat Pull Request ke `dev`
   5. Minta review (jika ada tim)
   6. Setelah disetujui, merge ke `dev`
   7. Setelah sprint selesai, merge `dev` → `staging` → `main`

   ## Aturan Penting
   - ⛔ DILARANG push langsung ke `main`
   - ⛔ DILARANG force push ke branch bersama
   - ✅ Selalu pull terbaru sebelum mulai kerja
   - ✅ Satu commit = satu perubahan logis
   - ✅ Tulis commit message yang jelas dan deskriptif
   ```
3. Buat file `.gitignore` sesuai platform:
   - **Web:** `node_modules/`, `.env`, `dist/`, `build/`, `.DS_Store`
   - **Python:** `__pycache__/`, `*.pyc`, `.env`, `venv/`, `.vscode/`
   - **IoT/ESP:** `.pio/`, `.vscode/`, `lib/README`, `.DS_Store`
   - **Android:** `build/`, `.gradle/`, `*.apk`, `local.properties`
   - **Desktop:** Sesuaikan dengan bahasa/framework
4. Laporkan: "🌿 Git Workflow berhasil dicetak! Branch strategy dan konvensi commit siap digunakan."

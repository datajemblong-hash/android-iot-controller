# Name: Git Command Executor
# Description: Skill untuk menjalankan perintah-perintah Git secara operasional: init, commit, push, pull, branching, dan koneksi ke repository online (GitHub/GitLab).

## What I do
Saya mengeksekusi perintah Git di terminal untuk mengelola versi kode proyek. Saya bisa:
- Menginisialisasi repository baru
- Menyimpan perubahan (commit)
- Mengunggah kode ke repository online (push ke GitHub/GitLab/Bitbucket)
- Menarik perubahan terbaru (pull)
- Mengelola branch (buat, pindah, merge)
- Menghubungkan proyek lokal ke repository online

## When to use me
- Saat proyek baru selesai dicetak dan ingin langsung diinisialisasi sebagai Git repo.
- Saat pengguna ingin menyimpan perubahan (commit).
- Saat pengguna ingin mengunggah kode ke GitHub/GitLab.
- Saat pengguna ingin menarik perubahan terbaru dari tim.
- Saat pengguna ingin membuat atau berpindah branch.

## How I do it

### Inisialisasi Repository Baru
1. Jalankan `git init` di direktori proyek.
2. Jalankan `git add .` untuk menambahkan semua file.
3. Jalankan `git commit -m "feat: inisialisasi proyek"` untuk commit pertama.
4. Laporkan: "✅ Repository Git berhasil diinisialisasi!"

### Menghubungkan ke Repository Online
1. Tanyakan ke pengguna:
   - Platform apa? (GitHub / GitLab / Bitbucket)
   - URL repository-nya? (misal: `https://github.com/username/nama-repo.git`)
2. Jalankan `git remote add origin [URL]`
3. Jalankan `git branch -M main`
4. Jalankan `git push -u origin main`
5. Laporkan: "🌐 Proyek berhasil terhubung dan diunggah ke [platform]!"

### Menyimpan Perubahan (Commit + Push)
1. Jalankan `git add .`
2. Tanyakan deskripsi perubahan ke pengguna, atau buat otomatis berdasarkan file yang berubah.
3. Jalankan `git commit -m "[tipe]: [deskripsi]"` sesuai konvensi di `9_Git_Workflow.md`:
   - `feat:` untuk fitur baru
   - `fix:` untuk perbaikan bug
   - `docs:` untuk perubahan dokumentasi
   - `style:` untuk perubahan tampilan
   - `refactor:` untuk refaktor kode
   - `test:` untuk perubahan test
   - `chore:` untuk maintenance
4. Jalankan `git push` untuk mengunggah ke online.
5. Laporkan:
   ```
   ✅ Perubahan berhasil disimpan dan diunggah!
   📝 Commit: [pesan commit]
   📦 File berubah: [jumlah] file
   🌐 Push ke: [branch] → [remote]
   ```

### Menarik Perubahan Terbaru (Pull)
1. Jalankan `git pull origin [branch]`
2. Jika ada konflik, laporkan file yang konflik dan minta instruksi pengguna.
3. Laporkan: "🔄 Perubahan terbaru berhasil ditarik dari [branch]!"

### Mengelola Branch
1. **Buat branch baru:** `git checkout -b [tipe]/[nama]` (misal: `feature/login`)
2. **Pindah branch:** `git checkout [nama-branch]`
3. **Merge branch:** `git merge [nama-branch]`
4. **Hapus branch:** `git branch -d [nama-branch]`
5. **Lihat semua branch:** `git branch -a`
6. Laporkan aksi yang dilakukan.

### Cek Status
1. Jalankan `git status` untuk melihat file yang berubah.
2. Jalankan `git log --oneline -10` untuk melihat 10 commit terakhir.
3. Laporkan hasilnya dalam format yang mudah dibaca.

## ⚠️ Peringatan Keamanan
- JANGAN pernah commit file yang mengandung password, API key, atau secret.
- Pastikan `.gitignore` sudah benar sebelum commit pertama.
- Jika ragu, jalankan `git status` dulu sebelum `git add .`

# Name: Project Backup Skill
# Description: Skill untuk melakukan pencadangan (backup) folder proyek sebelum melakukan modifikasi atau penulisan kode berskala besar.

## What I do
Saya mengamankan seluruh file proyek dengan membuat folder snapshot/backup. Ini berguna jika perubahan kode yang dilakukan oleh AI merusak sistem, sehingga Anda dapat kembali ke versi sebelumnya dengan mudah.

## When to use me
- Sebelum meminta AI melakukan refactoring besar-besaran.
- Sebelum melakukan update dependensi/package utama.
- Sebelum mencoba implementasi fitur eksperimental.

## How I do it
1. Minta persetujuan pengguna untuk melakukan backup.
2. Tentukan nama folder backup dengan format: `backup-YYYY-MM-DD_HH-mm-ss` atau `backup-[nama-fitur]`.
3. Buat folder backup di tingkat yang sama dengan folder proyek atau di dalam folder khusus `.backups/`.
4. Salin seluruh file proyek (kecuali folder besar seperti `node_modules/`, `.pio/`, atau `.git/` yang tercatat di `.gitignore`).
5. Laporkan kepada pengguna: "💾 Backup berhasil dibuat di folder: `[nama-folder-backup]`. Sekarang aman untuk melanjutkan perubahan besar!"

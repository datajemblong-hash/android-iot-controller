# Name: Sprint Update Skill
# Description: Skill untuk mengupdate dan melacak progres harian/sprint di file status.md secara otomatis.

## What I do
Saya membantu melacak, mengelola, dan mengupdate file `status.md` proyek. Saya bisa:
- Menandai tugas yang sudah selesai (`[x]`).
- Menandai tugas yang sedang dikerjakan (`[/]`).
- Menambahkan tugas harian baru berdasarkan progres nyata.
- Menghitung persentase progres proyek/sprint saat ini.

## When to use me
- Di akhir setiap sesi vibe coding atau setelah menyelesaikan suatu fitur.
- Saat ingin merencanakan tugas untuk esok hari atau sprint berikutnya.
- Saat ingin tahu berapa persen proyek yang sudah selesai dikerjakan.

## How I do it
1. Baca file `status.md` di direktori proyek.
2. Minta konfirmasi pengguna mengenai tugas mana saja yang sudah selesai, sedang dikerjakan, atau ingin ditunda.
3. Update status tugas pada list:
   - Gunakan `[x]` untuk selesai.
   - Gunakan `[/]` untuk sedang dikerjakan.
   - Gunakan `[ ]` untuk belum dikerjakan.
4. Hitung persentase progres dengan rumus:
   `(Jumlah Tugas Selesai / Total Tugas) * 100%`
5. Tuliskan ringkasan status di bagian atas file `status.md` atau perbarui tabel progres yang ada.
6. Laporkan di chat:
   ```
   📈 Progres Sprint Berhasil Diupdate!
   📊 Selesai: X / Y tugas (Z%)
   🔄 Sedang dikerjakan: [Daftar tugas]
   ```

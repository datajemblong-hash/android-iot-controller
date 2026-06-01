# 9. Panduan Git Workflow & Konvensi Commit

Dokumen ini merancang strategi penggunaan Git untuk memastikan riwayat pengembangan kode aplikasi terkelola dengan rapi, terlacak, dan kolaboratif.

---

## 1. Strategi Branching (Alur Branch)

Untuk skala proyek pengembangan aplikasi Android ini, kita menerapkan model branching **Git Flow Sederhana**:

```
  main      ========================= (Production / Stable Releases)
                      ^
                      |  (Merge PR)
  develop   ----------+-------------- (Integrasi Fitur)
             \       / \
              \     /   \
  feature/     v   /     v
  features  ====[feat]====[fix]====== (Pengembangan Fitur Baru / Perbaikan)
```

### Penjelasan Branch:
* **`main`**: Cabang utama yang berisi kode versi produksi yang stabil. Kode di branch ini selalu siap untuk dirilis ke pengguna (dalam bentuk APK Release resmi).
* **`develop`**: Cabang integrasi tempat menggabungkan semua fitur baru yang telah diuji sebelum didorong ke `main`.
* **`feature/`**: Cabang sementara yang dicabangkan dari `develop` untuk membuat fitur spesifik. 
  * Penamaan: `feature/nama-fitur` (contoh: `feature/room-database`, `feature/ui-dashboard`, `feature/websocket-client`).
* **`hotfix/`**: Cabang darurat yang langsung dicabangkan dari `main` jika ada bug kritis di aplikasi rilis yang harus segera diperbaiki tanpa menunggu siklus pengembangan di `develop`.

---

## 2. Konvensi Pesan Commit (Conventional Commits)

Format pesan commit mengikuti struktur:
```
<type>: <deskripsi singkat dalam bahasa indonesia/inggris>
```

### Jenis-jenis `<type>` yang disepakati:
* **`feat`**: Penambahan fitur baru (contoh: `feat: implementasi Room Database untuk simpan data sensor`).
* **`fix`**: Perbaikan bug (contoh: `fix: perbaiki socket timeout saat IP IoT tidak merespons`).
* **`docs`**: Perubahan dokumentasi saja (contoh: `docs: update panduan setup android studio`).
* **`style`**: Perubahan kosmetik/format kode yang tidak memengaruhi logika (contoh: `style: perbaiki indentasi xml layout`).
* **`refactor`**: Restrukturisasi kode tanpa menambah fitur atau memperbaiki bug (contoh: `refactor: ubah implementasi repository ke interface`).
* **`chore`**: Pemeliharaan build tool, update dependensi gradle, dll (contoh: `chore: naikkan versi dependency retrofit`).

---

## 3. Alur Penggabungan Kode (Pull Request)

Setiap penggabungan cabang `feature/` ke `develop` harus melalui alur review berikut:
1. Pastikan kode lokal sudah di-build tanpa error di Android Studio.
2. Dorong (*push*) branch feature ke repositori remote (GitHub/GitLab).
3. Buat **Pull Request (PR)** dari `feature/nama-fitur` menuju `develop`.
4. Lakukan peninjauan mandiri (*Self-review*) atau minta review dari rekan tim (jika ada) untuk memastikan kode bersih dari hardcoded IP dan API Keys.
5. Merge PR menggunakan metode **Squash and Merge** agar riwayat commit di branch utama tetap ringkas dan bersih.
6. Hapus branch `feature/nama-fitur` lokal dan remote yang sudah selesai digabungkan.

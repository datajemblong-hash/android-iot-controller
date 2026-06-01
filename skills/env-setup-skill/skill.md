# Name: Environment Setup Helper
# Description: Skill untuk menyusun panduan instalasi software dan setup lingkungan pengembangan yang dibutuhkan oleh proyek.

## What I do
Saya membuat dokumen `11_Panduan_Setup.md` yang memuat panduan lengkap menginstal tools, library, extension IDE, dan konfigurasi lingkungan pengembangan lokal agar siap digunakan untuk ngoding/vibe coding.

## When to use me
- Saat pertama kali mencetak proyek baru (otomatis).
- Ketika proyek di-clone di komputer baru.
- Ketika ada penambahan tools/software baru yang diwajibkan untuk proyek.

## How I do it
1. Baca file `2_Arsitektur.md` untuk mengidentifikasi bahasa pemrograman, framework, runtime, dan database yang digunakan.
2. Buat dokumen `11_Panduan_Setup.md` yang mencakup:
   - **Kebutuhan Minimum:** Versi OS, RAM, disk space yang disarankan (terutama min. 8GB RAM untuk Android Studio emulator).
   - **Instalasi Software Utama:** Tautan unduh resmi dan instruksi instalasi (misalnya: Node.js, Python, Git, VS Code, Java JDK LTS/17, Android Studio).
   - **Instalasi Dependensi Global/CLI:** Perintah terminal untuk install CLI (misalnya: `npm install -g create-react-app`, `pip install virtualenv`).
   - **Konfigurasi Android (Kotlin) khusus:** Setup path `ANDROID_HOME` & tools SDK di OS environment variables, serta verifikasi build tool Gradle.
   - **Panduan Testing Perangkat (Android):**
     - Cara mengaktifkan *Developer Options* dan *USB Debugging* di handphone fisik.
     - Cara membuat virtual device (Emulator) menggunakan *AVD Manager* di Android Studio.
   - **Konfigurasi IDE/Editor:** Extension VS Code yang sangat direkomendasikan (misalnya: PlatformIO untuk C++, Kotlin/Kotlin Language, Prettier, ESLint).
   - **Verifikasi Instalasi:** Perintah-perintah untuk mengecek apakah instalasi sukses (misalnya: `node -v`, `python --version`, `git --version`, `java -version`, `adb --version`).
3. Tulis langkah-langkah dengan bahasa yang ramah pemula (non-programmer).
4. Laporkan: "🛠️ Panduan Setup lokal berhasil dicetak!"

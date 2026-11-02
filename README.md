# School LMS (Android WebView)

Membungkus https://man19jkt.assessment.web.id/login menjadi aplikasi Android.

## Cara build APK
1. Instal Android Studio (versi terbaru).
2. File > Open > pilih folder ini. Tunggu Gradle sync selesai (butuh internet).
3. Colokkan HP (aktifkan USB debugging) lalu tekan Run, atau
   Build > Build Bundle(s) / APK(s) > Build APK(s).
4. Hasil: app/build/outputs/apk/debug/app-debug.apk -> kirim ke HP dan instal.

Untuk rilis/Play Store: Build > Generate Signed Bundle / APK.

## Kustomisasi
- Domain induk: app/build.gradle.kts (BASE_DOMAIN). Kode sekolah menjadi subdomain: https://<kode>.assessment.web.id/login
- Ganti sekolah: tekan lama ikon aplikasi > Ganti sekolah
- Nama aplikasi: app/src/main/res/values/strings.xml
- Warna: res/values/colors.xml
- Ikon: res/drawable/ic_launcher.xml

## Build lewat GitHub (tanpa Android Studio)
1. Unggah seluruh isi folder ini ke repositori GitHub (termasuk folder .github).
2. Buka tab Actions > Build APK > Run workflow (atau otomatis setelah push).
3. Setelah selesai (hijau), buka run tersebut, lalu unduh "SchoolLMS-apk" di bagian Artifacts.
4. Ekstrak zip-nya, instal app-debug.apk di HP.

## Jendela AI khusus guru (build terpisah)
APK biasa (untuk siswa) TIDAK memuat jendela AI. Jendela AI hanya ada di build guru, yang
dipasang sebagai aplikasi terpisah (id ...man19jkt.guru) dan hanya aktif di situs sekolah sendiri.

1. Deploy `cloudflare-worker/worker.js` di Cloudflare Workers. Isi Variables and Secrets:
   ANTHROPIC_API_KEY (Secret), TEACHER_CODE (Secret), ALLOWED_ORIGIN (Text) = `https://*.assessment.web.id`
2. Sesuaikan `QUESTION_SELECTOR` di `app/src/main/assets/school-lms-ai.js` dengan elemen soal di LMS.
3. Build guru:
   - GitHub: tab Actions > Build APK Guru > Run workflow > isi alamat worker > unduh "SchoolLMS-guru-apk".
   - Android Studio / Gradle: `gradle assembleDebug -PteacherAi=true -PaiApiUrl=https://xxx.workers.dev`
4. Pertama kali dipakai, jendela meminta kode guru (TEACHER_CODE). Bagikan APK guru hanya kepada guru.

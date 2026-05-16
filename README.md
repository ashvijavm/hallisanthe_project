# 🛒 Halli-Santhe Digital Marketplace
### Android App — Kotlin + Firebase
**Student:** Ashvija V M | **USN:** 1JS22CI014 | **Dept:** CSE (AI & ML)

---

## 📋 TABLE OF CONTENTS
1. Prerequisites
2. How to Open in Android Studio (Step-by-Step)
3. Firebase Setup (Required)
4. Font Setup
5. How to Run the App
6. App Features
7. Project Structure
8. Troubleshooting

---

## ✅ STEP 1 — PREREQUISITES

Before you begin, make sure you have installed:

| Tool | Version | Download |
|------|---------|----------|
| Android Studio | Hedgehog 2023.1+ | https://developer.android.com/studio |
| JDK | 17 or higher | Bundled with Android Studio |
| Android SDK | API 21–34 | Install via Android Studio |
| Git (optional) | Any | https://git-scm.com |

---

## 📂 STEP 2 — HOW TO OPEN THE ZIP IN ANDROID STUDIO

Follow these steps **exactly**:

### 2.1 — Extract the ZIP
1. Locate the downloaded file: `HalliSanthe.zip`
2. Right-click → **Extract All** (Windows) or double-click (Mac)
3. Extract to a simple path like:
   - Windows: `C:\Projects\HalliSanthe`
   - Mac/Linux: `~/Projects/HalliSanthe`
4. ⚠️ **IMPORTANT:** Avoid paths with spaces or special characters

### 2.2 — Open in Android Studio
1. Launch **Android Studio**
2. On the Welcome Screen, click **"Open"**
   - (OR go to **File → Open...** if a project is already open)
3. In the file browser, navigate to the extracted folder
4. Select the **`HalliSanthe`** folder (the one that contains `build.gradle`)
5. Click **"OK"**
6. If prompted "Trust this project?" → Click **"Trust Project"**

### 2.3 — Wait for Gradle Sync
1. Android Studio will start syncing automatically
2. You will see **"Gradle: Sync"** progress at the bottom
3. ⏳ First sync takes **3–10 minutes** (downloads dependencies)
4. Wait until you see **"BUILD SUCCESSFUL"** in the Build output
5. ⚠️ Do NOT click Run before sync completes

### 2.4 — Check for Errors
If you see red errors after sync:
- Go to **File → Invalidate Caches → Invalidate and Restart**
- Wait for Android Studio to restart and re-sync

---

## 🔥 STEP 3 — FIREBASE SETUP (REQUIRED TO RUN)

The app uses Firebase. You must create a free Firebase project.

### 3.1 — Create Firebase Project
1. Go to https://console.firebase.google.com
2. Click **"Add Project"**
3. Project name: `HalliSanthe` → Click Continue
4. Disable Google Analytics (optional) → Click **"Create Project"**
5. Wait for project to be created → Click **"Continue"**

### 3.2 — Add Android App to Firebase
1. On Firebase console dashboard, click the **Android icon** (</> Android)
2. Fill in:
   - **Android package name:** `com.hallisanthe`
   - **App nickname:** HalliSanthe (optional)
   - **Debug signing certificate SHA-1:** (skip for now)
3. Click **"Register App"**

### 3.3 — Download google-services.json
1. Click **"Download google-services.json"**
2. Save this file
3. In Android Studio, find the `app` folder in the Project panel (left side)
4. **Replace** the existing `app/google-services.json` with the downloaded one
5. Click **"Next"** → **"Next"** → **"Continue to Console"**

### 3.4 — Enable Firestore Database
1. In Firebase Console → left menu → **"Firestore Database"**
2. Click **"Create Database"**
3. Select **"Start in test mode"** (for development)
4. Choose region: **asia-south1 (Mumbai)** → Click **"Enable"**

### 3.5 — Enable Firebase Storage
1. In Firebase Console → left menu → **"Storage"**
2. Click **"Get Started"**
3. Select **"Start in test mode"** → Click **"Next"** → **"Done"**

### 3.6 — Enable Authentication
1. In Firebase Console → left menu → **"Authentication"**
2. Click **"Get Started"**
3. Click **"Email/Password"** → Toggle **Enable** → Click **"Save"**

---

## 🔤 STEP 4 — FONT SETUP (IMPORTANT)

The app uses **Poppins** font. You need to add font files.

### Option A — Download from Google Fonts (Recommended)
1. Go to: https://fonts.google.com/specimen/Poppins
2. Click **"Download Family"**
3. Extract the ZIP
4. Copy these two files:
   - `Poppins-Regular.ttf` → rename to `poppins_regular.ttf`
   - `Poppins-Bold.ttf` → rename to `poppins_bold.ttf`
5. Place both files in:
   `app/src/main/res/font/`

### Option B — Use Default Font (Quick Fix)
If you don't want to add fonts, open `themes.xml` and remove this line:
```xml
<item name="android:fontFamily">@font/poppins_regular</item>
```
And in `activity_splash.xml` remove:
```xml
android:fontFamily="@font/poppins_bold"
```

---

## ▶️ STEP 5 — HOW TO RUN THE APP

### On Emulator:
1. Go to **Tools → Device Manager**
2. Click **"Create Device"**
3. Select **Pixel 6** → Click **Next**
4. Download **API 33 (Android 13)** system image → Click **Next** → **Finish**
5. Click the **▶ Play button** in the toolbar

### On Physical Android Device:
1. On your phone: **Settings → About Phone → tap "Build Number" 7 times**
2. Go to **Settings → Developer Options → Enable USB Debugging**
3. Connect phone via USB cable
4. Click **Allow** on the USB Debugging dialog on your phone
5. Select your device from the dropdown in Android Studio
6. Click **▶ Run**

---

## 📱 STEP 6 — APP FEATURES

| Feature | Description |
|---------|-------------|
| 🏠 Home Screen | Grid view of all products with category filter chips |
| 🔍 Search | Search by product name in English, Kannada, Hindi, Tamil |
| 📦 Product Detail | Full detail with multilingual names + CTA buttons |
| 📤 Upload Product | Artisan can list product with image & 4-language names |
| 💬 WhatsApp Enquiry | One-tap WhatsApp message to seller |
| 📞 Call Seller | Direct call to seller |
| 🌐 Language Toggle | Switch UI between English / ಕನ್ನಡ / हिंदी / தமிழ் |
| 🗂️ Categories | Pottery, Textiles, Toys, Jewelry, Food, Woodcraft, Flowers |
| 🔄 Demo Data | Seed 6 demo products with one tap (Channapatna toys, Ilkal saree, etc.) |
| 💾 Offline Cache | Room DB caches products for offline viewing |

---

## 📁 STEP 7 — PROJECT STRUCTURE

```
HalliSanthe/
├── app/
│   ├── google-services.json         ← REPLACE with yours
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/hallisanthe/
│       │   ├── adapter/
│       │   │   └── ProductAdapter.kt
│       │   ├── model/
│       │   │   ├── Product.kt
│       │   │   └── AppDatabase.kt
│       │   ├── repository/
│       │   │   └── ProductRepository.kt
│       │   └── ui/
│       │       ├── splash/SplashActivity.kt
│       │       ├── home/
│       │       │   ├── MainActivity.kt
│       │       │   ├── HomeViewModel.kt
│       │       │   └── LoginActivity.kt
│       │       ├── detail/ProductDetailActivity.kt
│       │       └── upload/UploadProductActivity.kt
│       └── res/
│           ├── layout/              ← All XML layouts
│           ├── values/              ← Colors, strings, themes
│           ├── values-kn/           ← Kannada strings
│           ├── values-hi/           ← Hindi strings
│           ├── values-ta/           ← Tamil strings
│           ├── drawable/            ← Icons and backgrounds
│           ├── font/                ← Add Poppins fonts here
│           ├── menu/                ← Toolbar menu
│           └── anim/                ← Transition animations
```

---

## 🛠️ STEP 8 — TROUBLESHOOTING

### ❌ "google-services.json not found"
→ Make sure you downloaded and placed your `google-services.json` in the `app/` folder.

### ❌ "Unresolved reference: poppins_regular"
→ Either add the font .ttf files to `res/font/` OR remove the fontFamily references from themes.xml (see Step 4 Option B).

### ❌ Gradle sync fails with network error
→ Check your internet connection. Go to **File → Settings → Build → Gradle** and make sure it is set to "Use Gradle from: wrapper".

### ❌ "FAILED TO RESOLVE" dependency errors
→ Go to **File → Invalidate Caches → Invalidate and Restart**

### ❌ App crashes on launch
→ Ensure `google-services.json` is the real one (not the placeholder). Check Logcat for the exact error.

### ❌ Products not loading
→ Make sure Firestore is in **Test Mode**. Tap **"Load Demo Products"** button on the empty screen.

### ❌ Images not uploading
→ Make sure Firebase Storage is enabled and in Test Mode. Grant STORAGE permission on the device.

---

## 📞 SUPPORT

If you face any issues, check:
- Firebase Console: https://console.firebase.google.com
- Android Studio Help: https://developer.android.com/studio/intro

---

*Halli-Santhe — Empowering Artisans, Connecting Communities 🇮🇳*
*#VocalForLocal*

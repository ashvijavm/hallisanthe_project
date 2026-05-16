# 🛒 Halli-Santhe Digital Marketplace

### Android App — Kotlin + Firebase
**Student:** Ashvija V M | **USN:** 1JS22CI014 | **Dept:** CSE (AI & ML)

---

## 📋 Table of Contents
1. [Prerequisites](#-step-1--prerequisites)
2. [How to Open in Android Studio](#-step-2--how-to-open-the-zip-in-android-studio)
3. [Firebase Setup](#-step-3--firebase-setup-required-to-run)
4. [Font Setup](#-step-4--font-setup-important)
5. [How to Run the App](#-step-5--how-to-run-the-app)
6. [App Features](#-step-6--app-features)
7. [Project Structure](#-step-7--project-structure)
8. [Troubleshooting](#-step-8--troubleshooting)

---

## ✅ STEP 1 — PREREQUISITES

Before you begin, make sure you have installed:

| Tool | Version | Download Link |
| :--- | :--- | :--- |
| **Android Studio** | Hedgehog 2023.1+ | [Download Android Studio](https://developer.android.com/studio) |
| **JDK** | 17 or higher | Bundled with Android Studio |
| **Android SDK** | API 21–34 | Install via Android Studio SDK Manager |
| **Git** *(Optional)* | Any | [Download Git](https://git-scm.com) |

---

## 📂 STEP 2 — HOW TO OPEN THE ZIP IN ANDROID STUDIO

Follow these steps exactly to import the project:

### 2.1 — Extract the ZIP
1. Locate your downloaded file: `HalliSanthe.zip`
2. Right-click and choose **Extract All** (Windows) or double-click to unzip (Mac).
3. Extract to a clean directory path without spaces or special characters:
   * **Windows:** `C:\Projects\HalliSanthe`
   * **Mac/Linux:** `~/Projects/HalliSanthe`

### 2.2 — Open in Android Studio
1. Launch **Android Studio**.
2. On the Welcome Screen, click **Open** *(or go to **File → Open...** if another project is already open)*.
3. Navigate to your extracted directory and select the root **`HalliSanthe`** folder (the one containing the main `build.gradle` file).
4. Click **OK**.
5. If prompted with a security dialog, select **Trust Project**.

### 2.3 — Wait for Gradle Sync
1. Android Studio will automatically initiate a Gradle sync.
2. Monitor the **Gradle: Sync** progress bar at the bottom of the window.
3. > ⏳ **Note:** The initial sync typically takes **3–10 minutes** depending on your network connection as it downloads required dependencies.
4. Wait until you see **BUILD SUCCESSFUL** in the build tool window before attempting to interact with or run the app.

### 2.4 — Error Check
If you see immediate compilation or sync errors:
* Go to **File → Invalidate Caches → Invalidate and Restart**.
* Wait for the IDE to relaunch and automatically re-index the project.

---

## 🔥 STEP 3 — FIREBASE SETUP (REQUIRED TO RUN)

This application relies on Firebase services. You must link it to your own Firebase project configuration to successfully compile and load data.

### 3.1 — Create a Firebase Project
1. Navigate to the [Firebase Console](https://console.firebase.google.com).
2. Click **Add Project**.
3. Name the project `HalliSanthe` and click **Continue**.
4. Disable Google Analytics (optional for development) and click **Create Project**.
5. Once ready, click **Continue**.

### 3.2 — Add Android App to Firebase
1. Click the **Android icon** (`</> Android`) on your project dashboard page.
2. Configure the following explicit details:
   * **Android package name:** `com.hallisanthe`
   * **App nickname:** `HalliSanthe`
3. Click **Register App**.

### 3.3 — Download and Place Config File
1. Download the generated `google-services.json` file.
2. In Android Studio, switch the left-hand project view dropdown from *Android* to **Project**.
3. Locate the `app/` directory block.
4. **Replace** any existing file or drop your freshly downloaded `google-services.json` directly into that `app/` folder root.
5. Return to the Firebase Console, click **Next** through the steps, then select **Continue to Console**.

### 3.4 — Enable Firestore Database
1. From the left-hand navigation pane, select **Firestore Database**.
2. Click **Create Database**.
3. Choose **Start in test mode** to bypass immediate authentication security rule blocks.
4. Choose your deployment region (Recommended: `asia-south1` for Mumbai) and click **Enable**.

### 3.5 — Enable Firebase Storage
1. From the left navigation pane, select **Storage**.
2. Click **Get Started**.
3. Choose **Start in test mode** and click **Next** → **Done**.

### 3.6 — Enable Authentication
1. Go to **Authentication** in the left sidebar menu.
2. Click **Get Started**.
3. Under the Sign-in method tab, select **Email/Password**.
4. Toggle the status to **Enable** and click **Save**.

---

## 🔤 STEP 4 — FONT SETUP (IMPORTANT)

The app uses the custom **Poppins** typeface layout style. Choose one of the options below to handle asset linking:

### Option A — Download & Bundle Assets (Recommended)
1. Head over to [Google Fonts - Poppins](https://fonts.google.com/specimen/Poppins).
2. Click **Download Family** and unpack the folder.
3. Extract and match up these exact file items:
   * Move `Poppins-Regular.ttf` to `app/src/main/res/font/` and rename it to `poppins_regular.ttf`
   * Move `Poppins-Bold.ttf` to `app/src/main/res/font/` and rename it to `poppins_bold.ttf`

### Option B — Use System Fallback Font (Quick Fix)
If you prefer to bypass adding static assets, strip the custom font calls directly inside your workspace:
* Inside `themes.xml`, delete:
  ```xml
  <item name="android:fontFamily">@font/poppins_regular</item>
  

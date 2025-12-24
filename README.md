# APPWRK – Android Assignment (Training Modules App)

This is a native Android application built in **Kotlin** as part of an Android assignment for **APPWRK IT Solutions**.

The application displays a list of training modules and allows the user to mark each module as **Completed** or **Pending**.  
The app works completely offline and stores all data locally (no backend required).

---

## 📱 Features
- Home screen with a vertical list of training modules
- Each module displays:
    - Title
    - Short description
    - Status (Completed / Pending)
- Detail screen showing full module information
- Toggle module status (Completed ↔ Pending)
- Completion status persists after app restart
- Filter modules:
    - All
    - Completed
    - Pending
- Offline-first Android application

---

## 🏗 Architecture Explanation

The app follows a **basic MVVM (Model–View–ViewModel)** architecture.

### Architecture Layers:
- **View**
    - Activities
    - RecyclerView
    - XML layouts
- **ViewModel**
    - Manages UI-related data
    - Uses LiveData to notify UI changes
- **Model**
    - TrainingModule data class
- **Persistence**
    - SharedPreferences to store module completion status

This separation ensures clean code, better maintainability, and scalability.

---

## 🛠 Tech Stack
- Language: **Kotlin**
- UI: **XML + RecyclerView**
- Architecture: **MVVM**
- Jetpack Components:
    - ViewModel
    - LiveData
- Local Storage: **SharedPreferences**
- Target SDK: Recent Android version

---

## ▶️ How to Run the Project
1. Clone the repository:
   ```bash
   git clone https://github.com/PankajPauriyal/APPWRK.git

2. Open the project in Android Studio

3. Let Gradle sync complete

4. Run the app on:

Android Emulator, or

Physical Android device (Android 7.0+)

🚀Question- Why I Chose This Approach?

Ans -MVVM provides a clean separation between UI and business logic
LiveData ensures the UI updates automatically when data changes
SharedPreferences is lightweight and sufficient for simple local persistence
RecyclerView efficiently handles lists of data
This approach keeps the application simple, readable, and scalable

🚀Question- What I Would Improve With One More Day

And -With additional time, I would:
Replace SharedPreferences with Room Database
Add search functionality for training modules
Improve UI using Material Design components
Add unit tests for ViewModel
Enhance user experience with animations and visual polish


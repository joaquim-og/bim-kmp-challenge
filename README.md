# BIMM KMP Challenge

## App Summary 📖

This is a Kotlin Multiplatform project targeting Android and iOS platforms. The business logic was built on the shared layer, while UI was built natively, using Jetpack Compose for Android and SwiftUI for IOS codebase.

## ⚡ Architecture

- **MVVM (Model-View-ViewModel)**: Organises the codebase with a clear separation of concerns, ensuring maintainable and testable code.
- **Kotlin Multiplatform**: Enables code sharing between Android and iOS, reducing duplication and ensuring consistency.

## 🌱 Technologies

- **Kotlin**: The primary programming language for both shared and platform-specific shared code.
- **Jetpack Compose**: Used for building a modern, declarative UI for Android.
- **Swift UI**: Used for building a modern, declarative UI for IOS.
- **Kotlin Multiplatform (KMP)**: Facilitates code sharing across Android and iOS.
- **Coroutines**: For asynchronous programming and managing background tasks.

## 🛠️ Project Structure

- **/composeApp**: Contains the shared Multiplatform code.
  - **commonMain**: Code shared across all platforms (Android and iOS).
  - **androidMain**: Android-specific implementations.
  - **iosMain**: iOS-specific integrations.
- **/iosApp**: Entry point for the iOS application, including SwiftUI code.
- **/shared**: Shared business logic and utilities.
  - **commonMain**: Core logic shared across all platforms.
  - Platform-specific folders for additional customisations.

# FHIR Starter App for Android: README

Welcome to the FHIR Starter App for Android! This app is designed as a starting point for developers
exploring the integration of FHIR (Fast Healthcare Interoperability Resources) into Android
applications. It showcases essential use cases of FHIR resources and demonstrates how to leverage
the Android FHIR SDK.

# Table of Contents

* Overview
* Features
* Prerequisites
* Setup Instructions
* Use Cases
* Architecture
* Screenshots
* Contributing
* License

## Overview

FHIR Starter App is a demonstration project for developers looking to:

Explore FHIR resource management on Android.
Understand common use cases such as patient registration, encounter management, and observation
handling.
Learn how to use the Android FHIR SDK for resource parsing, storage, and retrieval.

## Features

* **Patient Management:** Create, read, update, and search for patient records.
Observation Tracking: Record and retrieve observations like vital signs (e.g., blood pressure,
temperature).
* **Encounter History:** Log and view patient encounters with healthcare providers.
* **Resource Querying:** Use FHIR-compliant search and filtering mechanisms.
* **Offline Support:** Cache FHIR data locally for offline access.
* **Modular Design:** Easy to extend for additional FHIR resources.

## Prerequisites

* Android Studio: Bumblebee (2021.1.1) or later.
* Android SDK: API level 21 or higher.
* Android FHIR SDK: Integrated in the app.
* Java/Kotlin: Proficiency in Java or Kotlin is recommended.

## Setup Instructions

1. Clone the Repository 

 ```
   git clone https://github.com/IntelliSOFT-Consulting/android-fhir-starter-app.git
   cd android-fhir-starter-app
 ```

2. Open in Android Studio
   Open the project in Android Studio.
   Let Gradle sync the dependencies automatically.
3. Configure the Backend Server
   If you’re using a FHIR server (e.g., HAPI FHIR, Google Healthcare API), update the base URL in
   app/src/main/java/com/jeff/imeja/fhir/FhirApplication.kt
   Example:
   kotlin
    ```
     FhirEngineProvider.init(
            FhirEngineConfiguration(
                enableEncryptionIfSupported = false,
                DatabaseErrorStrategy.RECREATE_AT_OPEN,
                ServerConfiguration(
                    baseUrl = "https://hapi.fhir.org/baseR4/",
                    httpLogger =
                    HttpLogger(
                        HttpLogger.Configuration(
                            if (BuildConfig.DEBUG) HttpLogger.Level.BODY else HttpLogger.Level.BASIC,
                        ),
                    ) {
                        Log.d("App-HttpLog", it)
                    },
                ),
            ),
        )
   ```
4. Build and Run
   Connect your Android device or start an emulator.
   Build the project and run the app.
  
## Use Cases
1. Patient Management
   * Add Patient: Fill out a simple form with demographics like name, gender, and date of birth.
   * Search Patient: Search by name, ID, or other parameters using the FHIR search API.
2. Observations
   * Record observations such as vital signs, lab results, or measurements.
   * Display a history of observations for a patient.
3. Encounter Management
   * Log visits or interactions with healthcare providers.
   * Retrieve past encounters with details like date, time, and provider.
4. Data Synchronization
   * Fetch data from the FHIR server and cache it locally for offline use.

## Architecture

### Tech Stack

* Frontend: XML-based layouts.
* Backend: Android FHIR SDK for resource handling.
* Data Storage: SQLite via Room (local caching).
* Networking: Retrofit for FHIR server communication.

### Layers

* Presentation: UI components using Jetpack Compose or XML.
* Domain: Business logic and use case handlers.
* Data: API services, local database, and FHIR SDK integration.

## Contributing

Contributions are welcome! Please follow these steps:

```
Fork the repository.
Create a feature branch (git checkout -b feature-name).
Commit your changes (git commit -m "Add feature").
Push to the branch (git push origin feature-name).
Open a pull request.
```

## License

This project is licensed under the MIT License. See the LICENSE file for details.
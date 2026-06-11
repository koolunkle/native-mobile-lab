# ActivityCallbacks

![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)

## Overview
A project demonstrating the Android Activity lifecycle and the mechanism for preserving UI state across configuration changes and process death.

## Key Learning Topics
- Activity lifecycle callback sequences (onCreate, onStart, onResume, etc.)
- UI state preservation using onSaveInstanceState and onRestoreInstanceState
- Handling configuration changes (e.g., screen rotation)
- Implementing edge-to-edge display using WindowInsets

## Tech Stack & Libraries
- **Architecture:** Basic
- **UI:** XML with ViewBinding
- **Key Libraries:** AndroidX Core KTX, AppCompat, Material Components

## Notes
The application logs lifecycle events to the console to visualize the Activity transitions. It specifically highlights how to maintain user input in a discount code generation form when the Activity is recreated.

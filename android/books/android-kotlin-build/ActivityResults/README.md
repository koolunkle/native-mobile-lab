# ActivityResults

![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)

## Overview
A project showcasing the modern Android Activity Result API for type-safe data exchange between activities.

## Key Learning Topics
- Implementing ActivityResultLauncher and ActivityResultContract
- Registering for results using registerForActivityResult
- Passing data back to a calling activity using setResult
- Decoupling result handling from the calling logic

## Tech Stack & Libraries
- **Architecture:** Basic
- **UI:** XML with ViewBinding
- **Key Libraries:** AndroidX Activity KTX, AppCompat, Material Components

## Notes
This project implements a color picker workflow where a main activity launches a second activity to select a color and receives the result through the modern API, replacing the deprecated startActivityForResult pattern.

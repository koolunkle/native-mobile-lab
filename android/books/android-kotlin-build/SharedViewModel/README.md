# SharedViewModel

## Overview
This project demonstrates how to share data between multiple fragments within a single Activity by using an Activity-scoped ViewModel.

## Key Learning Topics
- Fragment-to-Fragment communication via Shared ViewModel
- Scoping ViewModels to the Activity lifecycle
- Reactive data streams in ViewModels using RxJava

## Tech Stack & Libraries
- **Architecture:** MVVM
- **UI:** XML with ViewBinding
- **Key Libraries:** Lifecycle (ViewModel), RxJava3

## Notes
Uses requireActivity() when initializing the ViewModel in fragments to ensure both fragments share the same ViewModel instance hosted by the Activity.

# SharedPreferences

## Overview
This project demonstrates the use of SharedPreferences for persistent data storage of simple key-value pairs, integrated within an MVVM architecture.

## Key Learning Topics
- Basic SharedPreferences usage and the edit block
- Data abstraction using a wrapper class
- ViewModel and LiveData integration for reactive preference updates

## Tech Stack & Libraries
- **Architecture:** MVVM
- **UI:** XML with ViewBinding
- **Key Libraries:** Lifecycle (ViewModel, LiveData), Core KTX

## Notes
Implements a clean approach to preferences by wrapping SharedPreferences access in a dedicated class and exposing data through a ViewModel to the UI.

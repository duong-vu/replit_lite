# Replit Lite
An application created for Replit by Duong Vu

## Instructions
### Running the app
1. Open the project inside Android Studio.
2. Attach an Android Virtual Device or Android phone.
3. Install the app.
4. You can find it under the name Replit Lite.
### Configuring API endpoint
In case you need to change the API endpoint, go to `RetrofitModule` and update `BASE_URL`. Reinstall the app.

## Overview
### App architecture
* 100% native Kotlin.
* Single-Activity application.
* Koin is used for lightweight dependency injection. Services like API or Repository are injected through this.
* Retrofit provides remote query support. Gson is used to support object parsing.
* Model-View-View Model (MVVM) pattern is used for the UI layer.
* Data flows are managed through a combination of Kotlin Coroutines, Flow, and Channel.
* While not yet relevant here, the app is designed in such a way that adding a new language support would require very little refactoring and extension.

### App features
* Rudimentary syntax highlighting based on regex.
* Split-screen editor and console layout. Keyboard presence detection makes sure that the editor takes up as much space as needed while the user edits the code.
* Helper keyboard to provide access to common charactes like `(`, `)`, `:` as well as the `tab` key for better UX.
* Ability to clear console output.

## Planned Improvements
* Allow the user to create multiple coding files and folders.
* Add persistence to allow viewing saved work.
* Use a more robust infra to provide syntax highlighting (ideally relying on AST parsing, which also opens up the door to quick refactoring).
* Enable line numbering and horizontal overflow for the editor.
* Allow the user to customize their experience e.g. changing tab size, configuring the helper keyboard, changing editor theme.
* Enable account creation and cloud syncing.


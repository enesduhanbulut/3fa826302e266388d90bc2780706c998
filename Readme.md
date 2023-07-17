# Satellite Info

Satellite Info is an Android application that provides information about satellites. It utilizes
clean architecture principles, follows a single activity multi-fragment architecture, and makes use
of Flow and Coroutines for asynchronous operations.

## Features

- Display a list of satellites with their details
- Filter satellites by name and active/passive status
- View satellite details including cost per launch, first flight, height, and mass

## Architecture

The project follows the principles of clean architecture, separating the codebase into the following
layers:

- **Presentation**: Contains the UI-related components such as fragments, view models, and adapters.
- **Domain**: Defines the business logic and use cases of the application.
- **Data**: Implements the data sources and repositories, including room database and asset files.

## Tech Stack

The application is built using the following technologies and libraries:

- Kotlin: A modern programming language for Android development.
- Android Jetpack libraries: Provides a set of components and tools to build Android apps, including
  ViewModel, Room, Navigation, and Data Binding.
- Hilt: A dependency injection framework for Android, used for managing dependencies and enabling
  dependency injection.
- Moshi: A JSON parsing library for Kotlin, used for serialization and deserialization of JSON data.
- Coroutine: A Kotlin library for managing asynchronous programming with coroutines and flows.
- JUnit, Mockito, Turbine: Libraries for writing unit tests and conducting test-driven development (
  TDD).
- Espresso: A testing framework for writing UI tests in Android.
- Local
  caching: [Room](https://proandroiddev.com/enabling-cache-offline-support-on-android-using-room-4b82ae0c9c88)
  is used to cache satellite data locally.

## Setup

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build the project and run it on an emulator or a connected device.

## Testing

The project includes unit tests and UI tests to ensure code quality and correctness. You can run the
tests using the following steps:

1. Open the project in Android Studio.
2. Go to the `androidTest` directory to run UI tests or the `test` directory to run unit tests.
3. Right-click on the desired test file or directory and select "Run" or "Debug".

## Contributing

Contributions are welcome! If you find any bugs or have suggestions for improvements, please open an
issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

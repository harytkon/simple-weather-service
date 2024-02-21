# Simple weather service

This is a web app a that fetch data from a open weather API via own backend.

## Frontend

Written in JavaScript, React.js used as a UI library and unit tests written using Jest.

Source code located at `weather-frontend` folder.

### Start

Execute `npm start` from the terminal.

Runs the app in the development mode.
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.

### Run unit tests

Execute `npm test` from the terminal.

Launches the test runner in the interactive watch mode.

### Build for production

Execute `npm run build` from the terminal.

Builds the app for production to the `build` folder.
Bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.

## Backend

Written in Kotlin, Netty used as an embedded server and unit tests written using JUnit and Mockk.

Source code located at `weather-backend` folder.

### REST API

Get API is server at http://localhost:8080/weather/<city name>.
It can be used for example as http://localhost:8080/weather/helsinki

### Start

Launch from IntelliJ CE by right clicking `src/main/kotlin/Main.kt` and choosing `Run MainKt`.

### Run unit tests

Execute from IntelliJ CE by right clicking `src/test/` folder and choosing `Run tests in weather-backend`.

### Weather Open API

Uses Visual Crossing Weather Open APIs for fetching weather data.

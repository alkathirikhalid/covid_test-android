# CovidTest
Sample project created using the Covid-19-API from https://github.com/M-Media-Group/Covid-19-API to retrieve a list of all the countries and show every details of each one about Covid confirmed cases and deaths.

The application is built according the MVC pattern and it is divided in 3 parts.

- model: This component stores the application data. The model is responsible for handling the domain logic(real-world business rules) and communication with the network layers.
- controller: This component establishes the relationship between the View and the Model. It contains the core application logic and gets informed of the user’s behavior.
- view: This part includes the Main Activity and the Fragments that shows the UI.  it provides the visualization of the data stored in the Model and offers interaction to the user. In the ListFragment, users can sort the data by 4 different criteria and can filter the list by name.
    In the DetailFragment the app displays some data related to rhe country selected, and a graph showing the number of the deaths in the selected country in the last 10 days.

This app is written in Java 8 and it does not use any third party library. It relies only on the classes available in the Android framework, included Jetpack.
It uses AsyncTask for background threading as requested by test specifications.


# UI
<img src="https://github.com/IQUII/covid_test-android/blob/develop/wiki/list.jpeg" width="240" height="506"><img src="https://github.com/IQUII/covid_test-android/blob/develop/wiki/list.jpeg" width="240" height="506"><img src="https://github.com/IQUII/covid_test-android/blob/develop/wiki/sort.jpeg" width="240" height="506">

# Import project
- open your **Terminal** app
- run command: `git clone <git-repository-url>`
- open **Android Studio** app
- select **Import Project from Gradle**



# Project specifications
- Java 8
- HttpUrlConnection (no third part library for network calls)
- MVC Pattern
- AsyncTask for executing background threads

# License
[https://www.apache.org/licenses/LICENSE-2.0](https://www.apache.org/licenses/LICENSE-2.0)

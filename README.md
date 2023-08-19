# Flight Delay Estimator
A Flight Viewer application that uses Machine-Learning model to estimate the upcoming delay on each flight acroding to Time of departure, arrival as well as Day and Month of the flight.
The project was made with jetpack Compose.
## Project's KeyPoints
* Machine Learning
* JetPack Compose
* Jython
* Chaquo
* Pickle
## Built with
* [Kotlin](https://kotlinlang.org/): Default language used to build this project
* [Jetpack Compose](https://developer.android.com/jetpack/compose): Jetpack Compose is Android's recommended modern toolkit for building native UI. It simplifies and accelerates UI development on Android.
* [Chaquo](https://chaquo.com/chaquopy): Python SDK for Android, Full integration with Android Studio's standard Gradle build system.
* [Pickle](https://docs.python.org/3/library/pickle.html#:~:text=%E2%80%9CPickling%E2%80%9D%20is%20the%20process%20whereby,back%20into%20an%20object%20hierarchy.): The process whereby a Python object hierarchy is converted into a byte stream

## ScreenShots
<table>
  <tr>
    <td align="center">ScreenRecording</td>
  </tr>
  <tr>
    <td><img src="/Screenshot/Screen_recording.gif" width="360" 
     height="760" ></td>
   </tr>
 </table>

 ## Notes
* Model file is included in assets file .pkl
* python Runner is the gate of calling the __init__.py python file to make prediction from model
* when using sklearn to train ML model make sure that you're using sklearn.__version == 0.24.1 as this is the lastest version supported by Chaquo project

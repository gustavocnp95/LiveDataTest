# LiveDataTest
An example project to demonstrate the using of LiveData feature in Android, for an article made in the medium platform.

As you can see by browsing the code, the basic idea is to be able to insert an register on sqlite database and when it occurs, the viewmodel uses livedata to alert view that the progress bar should be showed.

I do not considerate the best practices on viewmodel or xml's files and do not tried to get the best perfomance, even because there is a delay on rxjava using to make sure the user can see the loading bar happening.

![Peek 2021-06-27 20-04](https://user-images.githubusercontent.com/48319666/123562099-db4e6380-d782-11eb-92ca-aa8981cf155b.gif)

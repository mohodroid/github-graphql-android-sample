# github-graphql-android-sample
A simple github client that indicates basic profile information and repositries of the current authenticated uesr with graohql API of github.

##Summary
Technologies in this project:Apollo(Graphql client), OkHttp, Coroutine, Room, Glide, Navigation Component, MVVM, Dagger Single Activity

![alt text](https://github.com/mohodroid/github-graphql-android-sample/blob/main/arts/List.png)
![alt text](https://github.com/mohodroid/github-graphql-android-sample/blob/main/arts/Profile.png)

##Structure
The structure of the project:
The architecture of the project is based on the DDD approach
The Data layer used a repository pattern to return data from a remote server or DB(Offline first)
The Presentation layer is based on the MVVM Architecture pattern
and also other design patterns such as the Template method pattern and others.

##Build
Please consider add your API_HEADER to the build.gradle(:app) file(make sure your key is support user info such as email, followers, name)




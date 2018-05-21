# Picalicous MobileAPP
##### *Everything beyond a CLICK!*
Android App developed as part of CMPE 277 at SJSU, using google Cloud vision API. The app is able to click a picture and store it in a folder. Then we can label the image using google cloud vision API in the backend. The labels are stored locally in the app. Once labelled we can search through the labels without data connection. We have used Room Persistence Library to implement offline search. 


## Features

[Project Journal will be updated as a doc file](https://docs.google.com/document/d/1h4DPvFEw2VXFPVJhJE3s1yx8FvOCjO4bA41tlvCv2a0/edit?usp=sharing)

[Project presentation](https://docs.google.com/presentation/d/1_PnVPAkVe09luRH8J_mdTzbYXJEvodit5GWFch7L47E/edit?usp=sharing)



<img src="https://github.com/mhn10/Picalicous_277/blob/master/app/src/main/res/drawable/picalicious_splash.png?raw=true" width="200" height="300">


This project uses the following dependencies 

```java
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:27.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.1.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
    implementation 'com.applandeo:material-calendar-view:1.4.0'
    implementation 'com.android.support:design:27.1.0'
    implementation 'com.applandeo:material-calendar-view:1.4.0'    // retrofit, gson
    implementation 'com.google.code.gson:gson:2.6.2'
    implementation 'com.squareup.retrofit2:retrofit:2.0.2'
    implementation 'com.squareup.retrofit2:converter-gson:2.0.2'
    implementation 'com.google.api-client:google-api-client-android:1.22.0'
    implementation 'com.google.apis:google-api-services-vision:v1-rev357-1.22.0'
    implementation 'com.google.code.findbugs:jsr305:2.0.1'
    implementation 'commons-io:commons-io:2.5'
    implementation 'com.google.http-client:google-http-client-gson:1.19.0'
    implementation "android.arch.persistence.room:runtime:$rootProject.roomVersion"
    annotationProcessor "android.arch.persistence.room:compiler:$rootProject.roomVersion"
    androidTestImplementation "android.arch.persistence.room:testing:$rootProject.roomVersion"
    implementation "cat.ereza:customactivityoncrash:2.2.0"


}
```



<a href="http://www.youtube.com/watch?feature=player_embedded&v=PnmKfe5Zamo
" target="_blank"><img src="http://img.youtube.com/vi/PnmKfe5Zamo/0.jpg" 
alt="IMAGE ALT TEXT HERE" width="340" height="250" border="10" /></a>

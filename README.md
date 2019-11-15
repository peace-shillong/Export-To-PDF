# Export To PDF
 An android app sample that exports text to pdf file, using apw library

Find APW Library and demo project
https://github.com/Turbo87/apwlibrary

Used this as a short intro
https://medium.com/@ssaurel/learn-to-generate-pdf-documents-for-your-android-application-8abd3de7fe3b

## NOTES
If you are building from scratch and added apw library to your project do make the following changes: 

1. in side gradle file of your project
```
buildscript {
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.4.2'
        //added these two for apw to work
        classpath 'com.github.dcendents:android-maven-gradle-plugin:1.5'
        classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.7.3'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
```
2. remove build tools automatically from apwlibrary gradle file

3. add 
implementation project(path: ':apwlibrary')

to your app gradle file

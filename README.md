<H1>SabaOS-sdk messaging</H1>

[![](https://jitpack.io/v/sabaos-sdk/messaging.svg)](https://jitpack.io/#sabaos-sdk/messaging)

This library provides APIs and classes needed to create a client application for receiving messages(notifications) on smart phones.

<br/>
<H1>How to use</H1>

<H3>Step 1. Add the JitPack repository to your root build file</H3>

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  
<H3>Step 2. Add the dependency</H3>

	dependencies {
	        implementation 'com.github.sabaos-sdk:messaging:1.0.0'
	}
	
<H3>Step 3. Add the required code</H3>

1- Add the following code to the MainActivity's onCreate() method:

        Init init = new Init();
        init.startSaba(getApplicationContext());
	
2- Create an Intent Service named "SabaClientService". Note that this service should not be in a directory. It should in the root of your project's Java or Kotlin folder. Change the file so that it will extend from "ClientService" and remove all the default code that is in the class.

3- To receive the incoming messages override the "handleMessage()" method in "SabaClientService" file. You can do this by adding this code to the service:

	    @Override
        public void handleMessage(String data){
		
	    // the data parameter is the message. You can manipulate it as you wish.
        }
	
At this point your "SabaClientService" should look like this:
	
	public class SabaClientService extends ClientService {
	
            @Override
            public void handleMessage(String data){
	
	        // the data parameter is the message. You can manipulate it as you wish.
            }

    }
	
4- Finally in the project's Manifest file, change "SabaClientService" tag, so it will look like this:

	<service
        android:name=".SabaClientService"
        android:exported="true"></service>
	    	
 <H1><br/>Sample application</H1>
 In order to get a better understanding of this library, you can see our sample application <a href="https://github.com/sabaos-sdk/messaging-sample-app" target="_blank">here</a> which makes use of the library to receive messages and show them as notifcations.

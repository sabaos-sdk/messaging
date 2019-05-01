<H1>SabaOS-sdk messaging</H1>

This library provides APIs and classes needed to create a client application for receiving messages(notifications) on smart phones.

<br/>
<H1>Download</H1>

<H3>Step 1. Add the JitPack repository to your root build file</H3>

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  
<H3>Step 2. Add the dependency</H3>

	dependencies {
	        implementation 'com.github.sabaos-sdk:messaging:0.1.0'
	}
  <br/>
  
 <H1>Sample application</H1>
 In order to get a better understading of the library, you can see our sample application <a href="https://github.com/sabaos-sdk/messaging-sample-app">here</a> which makes use of this library to receive messages and show them as notifcations.

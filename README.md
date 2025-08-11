# 📱 Nextory Mobile Automation Test

**Pre Requisites**
Install the below Softwares
Appium: https://appium.io/docs/en/2.0/quickstart/install/
Java: https://www.oracle.com/in/java/technologies/downloads/
Android Studio: https://developer.android.com/studio
Xcode: From App Store(For Mac users only)
**Drivers:**
uiautomator2@3.7.3
xcuitest@7.24.0
**IDE**
IntelliJ Community edition: https://www.jetbrains.com/idea/download

---

## 🛠 Tech Stack
- **Java** (Test scripts)
- **JUnit/TestNG** (Testing framework)
- **Appium** (Mobile automation)
- **Selenium WebDriver**
- **Android Studio / AVD** or a real Android device
- **Node.js & Appium Server**
- **Maven** (Dependency management)
- **BrowserStack App Automate** (Cloud-based testing)
- **Allure-Reports** (For Reporting)

** Project Structure
nextory_assignment/
├── src/test/java/ # Test code
├── pom.xml # Maven dependencies
├── README.md # Project instructions
└── resources/ # Config files

**Framework Architecture Explanation**
The Framework is divided into below packages
1. ui_tests
   -->mobile

**Each Package includes below folders**
-->pages
This Class contains locators present on a particular page and methods that can be performed on that locator
-->actions
This class contains methods containing all the actions that could be performed on the particular page
-->base
This class contains supporting methods to perform any action on a element and File Utilities
-->tests
Actual Implementation of Test cases along with necessary Assertions on the page


**Additional Folders**
Resources
-->features: This file is used to write BDD tests
-->application property files to save property values
Testdata
-->Excel Driven test data file . One file for each environment
TestNG XML
-->Different XMLs to execute Sanity, Regression, E2E test cases
Drivers
-->To store the drivers for different browsers and modheaders

🌐 Running Tests on BrowserStack
**Create a BrowserStack Account**
-Sign up at https://www.browserstack.com/users/sign_up
-Get your Username and Access Key from BrowserStack Automate Dashboard
-Upload app to get the appUrl
-Add desired capabilities in the test code

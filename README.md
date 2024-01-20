# Steps creating "Amphibian" app

## 1. Make UI layer

### AmphibianApp.kt

This file is used only for displaying UI to users. This contains two part:
1. A composable method using `Scaffold` to divide screen to topBar and main screen.
2. A composable method that describes the structure of topBar

### MainScreen.kt

This file contains the codes of all screens which are displayed in this application. There are 3 different screens which are shown according to the uiState which will be discussed below

## 2. Create Class for Network Communication

### AmphibianItem

This is a data class used for saving response of API.

By using `@Serializable`, the JSON type reponse of API can be directly converted to a list of `AmphibianItem`.

### AmphibianApiService.kt 

This file contains an interface that describes the api for fetching datas from external URL.

Initially it contained an singletone object that initiallized a class which inherited the interface, but as `repository`and `appContainer` were implemented, all the codes except interface part got extracted to the `appContainer` file.

## 3. Create ViewModel

Before creating repository it acted as a uni-directional bridge on which data moves from `retrofit` to UI layer. 

### AmphibianUiState

First of all, an interface `UiState` for indicating current ui state of app is created.

### AmphibianViewModel
ViewModel manages `UiState` as a mutableState variable. It changes the value of `AmphibianUiState` according to the result of retrofit call, whether it's completed successfully or ended due to some exceptions.

By using ViewModel in UI Layer, UI layer can access to the data which is fetched from external URL.

## 4. Create Repository

Repository is created for separating application into two different layers: UI and Data.   
By this, managing and testing codes becomes much easier.

### AmphibianRepository

This class takes `ApiService` that contains retrofit related code as a factor. Using a method it calls the method from `ApiService` and returns its result.

From this point, ViewModel will not communicate directly with the ApiService. Repository class will access ApiService and save the data. Data saved in Repository class will be accessed by ViewModel and then ViewModel will give data to UI layer.

## 5. Create AppContainer

For preventing private declaration of class instance in certain class, which makes the model of application more complicating and difficult to test, an appContainer which can be access from any class is made.

AppContainer basically contains Repository and initiallizes it. Once repository is initiallized in AppContainer, any class can accessed on it.

AppContainer created is then initiallized in Application class. Application class created by developer must be registerd in 'manifest.xml' file under `<application android:name=""/>`


>[!NOTE]
> This markdown's content is just a rough explanation of the app pushed onto this github page.
> It may not be correct explanation.
> Just look through the concept of how app is built.   
> Sorry for poor english and thanks for reading









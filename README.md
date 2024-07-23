NYT Books 

The application shows a list of books. 
Every book displays the title, description and image. 

There is an option in a menu to refresh the book list if the user wants. 
If there is an error trying to get the list, the error description will be 
displayed and a button to try again the network call will be visible. 


Glide was used to load the book images as requested 
Dagger for dependency injection and mvp architecture was implemented 
Retrofit for the API and coroutines to consume the network call, 
because is easy to implement and simple to understand 


Mockito and coroutines test libraries were added to write unit test 
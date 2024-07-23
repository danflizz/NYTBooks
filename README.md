NYT Books 

The application shows a list of books. 
Every book displays the title, description and image. 

![phone](https://github.com/user-attachments/assets/4be3e235-8b1a-455e-a54d-e1e784bc56a3)

There is an option in a menu to refresh the book list if the user wants. 

![refresh](https://github.com/user-attachments/assets/194ff5df-5640-468c-8aff-cc14d30866e8)


If there is an error trying to get the list, the error description will be 
displayed and a button to try again the network call will be visible. 

![error](https://github.com/user-attachments/assets/32e48a36-212c-46c6-86c1-ec60ad17a13e)


Glide was used to load the book images as requested 
Dagger for dependency injection and mvp architecture was implemented 
Retrofit for the API and coroutines to consume the network call, 
because is easy to implement and simple to understand 


Mockito and coroutines test libraries were added to write unit test 

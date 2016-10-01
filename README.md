### Why I made this project?
Photobox (service where you can order pictures) informed us all the images
stored on the server will be deleted soon so we wanted to download them. After
seeing that manual downloading is barely possible (go to each picture, then 
inspect the browser to get url of pic) I found this needs to be automated.

### How I wanted to solve it?
Program which logs in the user, visit all the albums then each picture
and then downloads the full size pictures to directory structure representing albums.

### What I made on the end?
HtmlUnit based application scrapping the Photobox pages. The app:
- asks for login and passwords
- downloads all the pictures to albums directory

### What could be done better?
This was quick weekend evenings project, so I will probably not fix it, but:
- error handling
- more unit tests (on copy of page, not real pages)
- sometimes logging in the user fails

### How to use it?
Use maven to build jar (mvn package will produce uber-jar).



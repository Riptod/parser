"# parser" 

To run the application use **parser-1.0-SNAPSHOT-jar-with-dependencies.jar**

Here parse only the first 16 objects from the site were received, since the rest use lazy loading when scrolling the site down, in order to get all the objects, need to use some kind of browser emulation that could scroll the page.

The result is written to **items.json** file in resources folder.
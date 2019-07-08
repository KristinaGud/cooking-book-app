# Cooking-Book-App
contains of 2 micro services: 

* Collector, which  uses external recipes database and also collects recipes 
from online cooking sites using Selenium WebDriver;
* Searcher, which saves recipes in ElasticSearch database and search for recipes by given keyword

For their communication RabbitMQ is used. It sends recipes to save in ElasticSearch and MySql databases. 
The first one serves for searches and the second one for statistics.

Functionality:
-------------
* User can log on / log out;
* User can search for recipes by entering product name;
* User can check top upvoted recipes;
* User gets email alert when new recipe with his/her chosen keyword appears
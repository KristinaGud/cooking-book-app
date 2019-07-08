# Cooking-Book-App
contains of 2 micro services: 

* Collector, which  uses external recipes database and also collects recipes 
from online cooking sites using Selenium WebDriver;
* Searcher, which saves recipes in ElasticSearch database and search for recipes by given keyword

For their communication RabbitMQ is used. It sends recipes to save in ElasticSearch and MySql databases. 
The first one serves for searches and the second one for statistics.

Functionality:
-------------
* User can log in / log out;
* User can search for recipes by entering product name;
* User can check top upvoted recipes;
* User gets email alert when new recipe with his/her chosen keyword appears.

If you have docker installed, you can run:
* ElasticSearch: `sudo docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.2.0`
* Cerebro: `sudo docker run -p 9000:9000 lmenezes/cerebro`
* RabbitMQ: `sudo docker run -d --hostname my-rabbit --name some-rabbit -p 8090:15672 -p 5672:5672 rabbitmq:3-management`

Before start create SQL schema and ElasticSearch index:
* create index:
curl -X PUT "localhost:9200/recipe"
* create schema:
CREATE SCHEMA \`cooking_book_app\`
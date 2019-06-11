## Description 
Building a news article searching application powered by the New York Times Search API.

## Design Pattern
Using MVP

## User stories
- User can enter a search query that will display a grid of news articles using the thumbnail and headline from the New York Times Search API.
- User can click on "filter" icon which allows selection of advanced search options to filter articles. 
- User can tap on any article in results to view the contents in an embedded browser.
- User can scroll down "infinitely" to continue loading more news articles. The maximum number of articles is limited by the API search.
- Robust error handling, check if internet is available, handle error cases, network failures. 
- User can share a link to their friends or email it to themselves.
- For different news articles that only have text or have text with thumbnails, use Heterogenous Layouts with RecyclerView.

## Mockup
![MainActivity](https://user-images.githubusercontent.com/38346869/59287721-7196b600-8c9c-11e9-9c75-42baf6415236.PNG)
![Filter](https://user-images.githubusercontent.com/38346869/59287723-722f4c80-8c9c-11e9-9ef5-b295dd62695c.PNG)
![Read](https://user-images.githubusercontent.com/38346869/59287721-7196b600-8c9c-11e9-9c75-42baf6415236.PNG)

## Library 
- Glide
- Retrofit2

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
![MainActivity](https://user-images.githubusercontent.com/38346869/59198709-94eb3380-8bbe-11e9-84fa-32bb61335c57.PNG)

## Library 
- Glide
- Retrofit2

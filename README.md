## Summary
The Card cost calculator API is a case study of implementing an external API Integration

Based on card number, using the first 6 digits of a payment card number (credit cards, debit cards, etc.)
identify the institution that issued the card to the card holder and calculate the cost.

Clearing cost matrix:

Issuing Country,  Cost (USD)
- US , $5
- GR , $15 
- Others , $10


## Tools that I used:
- h2 in memory database
- openapi spec , to generate and  document the API (thanks openapi && swagger <3 )
- lombok library to reduce boilerplate code for model/data objects  (thanks lombok <3 )

##H2 in memory DB
http://localhost:8080/h2-console

## Postman requests
Content-Type: application/json

## OpenApi
You can  check the documentation by using:
- postman GET request
http://localhost:8080/v2/api-docs

or 
- hit on browser:
http://localhost:8080/swagger-ui.html




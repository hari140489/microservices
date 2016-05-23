#Microservices

- Clone the project using git url https://github.com/hari140489/microservices.git
- Discovery Service: Inside service-discovery folder
    - Run command "mvn spring-boot:run" (it runs on http://localhost:8070)
    - Run command "mvn spring-boot:run -Dspring.profiles.active=primary" (it runs on http://localhost:8071)
    - Run command "mvn spring-boot:run -Dspring.profiles.active=secondary" (it runs on http://localhost:8072)
    - Run command "mvn spring-boot:run -Dspring.profiles.active=tertiary" (it runs on http://localhost:8073)
- Config Server: Inside config-server folder
    - Run command "mvn spring-boot:run" command (it runs on http://localhost:8000) 
- Gateway/Zuul Proxy: Inside client-api-gateway folder
    - Run command "mvn spring-boot:run" (it runs on http://localhost:8003)
    - It gets registered automatically in discovery server
- Product Catalogue Service: Inside product-catalogue-service folder
    - Run command mvn spring-boot:run multiple times in different command prompts
    - Each time, it runs on any random available port and the individual instances get registered in discover server.
- Pricing Service: Inside pricing-service folder
    - Run command mvn spring-boot:run multiple times in different command prompts
    - Each time, it runs on any random available port and the individual instances get registered in discover server.
- Functions Available
    - Create: Open any restful client, invoke http://localhost:8003/api/product-catalogue-service/products with json for example {“name”: “maruti”, “type”:”car”} with request method POST and content-type application/json.
    - Retrieve: http://localhost:8003/api/product-catalogue-service/products with request method GET
    - Delete: http://localhost:8003/api/product-catalogue-service/products/{id} with request method DELETE. Id can anyone the products id retrieved from Retrieve url.
    - Search: http://localhost:8003/api/product-catalogue-service/products/searchByType?type=car with request method GET
    - Get Price:http://localhost:8003/api/pricing-service/products/price/get?name=maruti&type=car (name and type parameters are mandatory and both should match a single record present in database)
- Monitoring:
    - It runs on http://localhost:8004
    - Invoke url http://localhost:8003/hystrix.stream
    - Now, browser continuously refreshes after hystrix pings the restful services for health status. Browser can be closed here.
    - Hystrix dashboard runs on http://localhost:8004/hystrix.html
    - Now copy the url http://localhost:8003/hystrix.stream
    - Paste it in the test field where turbine stream input is present and hit monitor.
    - Now, you are redirected to monitoring screen where the above two two restful end points health is shown.
    - Now continuously hit any one of restful services product catalogue service or pricing service. You can observe the graph of the corresponding services where it shows the success or failure state.

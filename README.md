# FuelCostSpringWebapp

<p>You can enter and save into database type of fuel and average fuel usage of Your car. 
Then window appear with actual fuel prices (scrapped from another website). 
After entering the price and journey length You will see total cost of fuel used.</p>

This project is exercise of CRUD operations, created with Spring Boot 
contain changes allowing run on GoogleCloud.
<p>Project is running at https://wise-mantra-270807.appspot.com/</p>

Allowed HTTPs requests:

 `GET`/api<br/> **return:** String, **contain:** default greeting 
 
`GET`/api params={"lang", "name"}<br/> **return:** String, **contain:** greeting in specified langID(1=polish, 2=english, other=default) with name

`GET`/api/langs<br/> **return:** JSON, **contain:** all lang code and lang id from langs table 

`GET`/api/cars<br/> **return:** JSON, **contain:** all cars from cars table 

`POST`/api/fuelcost/{car id} @RequestBody trip to calculate: JSON eg. {"kmOnLpg":"100","kmOnPb":"100","kmOnOn":"0","lpgPrice":"5","pbPrice":"6","onPrice":"0"}<br/> **return:** double, **contain:** calculated cost

`POST`/api/cars<br/> @RequestBody car to save: JSON eg. {"name":"Opel","lpgPowered":true,"pbPowered":true,"onPowered":false,"onOn100Km":"0","lpgOn100Km":"10","pbOn100Km":"8"}**return:** JSON, **contain:** saved car 

`DELETE`/api/cars/{car id}<br/> **return:** 
<br/>HTTP200 when delete success

23.05.2020 big update with Spring Security - users database, each user has own car list, add login with Github

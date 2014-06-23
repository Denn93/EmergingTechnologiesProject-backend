<html>
    <body>
    <h2>This is a RestFull Webservices for the project Emerging Technology</h2>
    <hr/>

    <h3>How to use!</h3>

    <pre>
Hieronder alle endpoint vanuit de backend. Voor termen zie dangerousgoods csv op dropbox. :)
Alle results in JSON en alle endpoints een GET request. Behalve wanneer aangegeven

Default adress
http://145.24.222.137:8080/Rebuild/rest/

container/get			Alle Containers uit de database
container/get?{alle filter options}

location/get			Alle locaties binnen krijgen uit de database
location/get?{ORDER optie}	Alle locaties binnen krijgen uit de database En de mogelijkheid om ook te sorteren
location/get/{id}		Locatie binnen krijgen op basis van locationID

handling/get			Alle handlings binnen krijgen uit de database


Dus als voorbeeld url:
http://145.24.222.137:8080/Rebuild/rest/container/get

Container filter:
Alles gaat via een GET principe

Dus veld + waarde Bijvoorbeeld equipemnumber=PBAU3761225

Wanneer je een groter dan of kleiner dan (< >) wilt gebruiker plaats je veld nogmaals erin maar dan met value gt of lt (greater than, less than)
Een voorbeeld hiervan http://145.24.222.137:8080/Rebuild/rest/container/get?uno=2000&uno=gt
Dit betekent alle containers met een uno waarde groter dan 2000
Dit kan je zelf ook verder uitbreiden met alle velden waarop je wilt filteren


Verder is het mogelijk om ORDER by aan de query te koppelen
Dit gaat via de order=veldNaam -> order=equipmentNumber

Om hier een bepaalde volgorde aan te geven zal er order=ASC of order=DESC toegevoegd moeten worden
Dus een voorbeeld hiervan http://145.24.222.137:8080/Rebuild/rest/container/get?uno=2000&uno=gt&order=uno&order=ASC
Hier wordt de waarde gesorteerd op uno ascending. Mogelijk om ook meerdere velden te sorteren op volgorde zoals ook kan in een query.

    </pre>

    <p> &copy SinLimites; Version: 2.0 Final</p>

    </body>
</html>

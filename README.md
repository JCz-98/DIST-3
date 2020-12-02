# DIST-3
Simple JSF Table application

Authros: Cazco Jonathan
	Naunay Erick
	Pinta Mike

The application has 2 main html pages which the user interacts with.
	
	-> index (MIKE PINTA): page where the user enters the number of products
	what they want to buy. It is mainly handled by the ProductTable bean
	(ViewScoped); therefore it can be opened by multiple tabs / windows within the
	same browser to make independent purchases. However, the "users"
	are defined from each Session, so purchases are stored in, implements error messaging.
	ShopManager (SessionScoped). 
	
	-> purchase (MIKE) PINTA: page in which the user sees what they have purchased on the index page. 
	The information accessed here is stored in ShopManager (SessionScoped), which corresponds to the last
	purchase made in that Session.

Project Structure

	-> ProductsBean (ERICK NAUNAY): Bean that is in charge of maintaining the state
	of the products and their respective stocks.

	-> ShopManager (JONATHAN CAZCO): Bean that is responsible for maintaining the state of
	purchases made within that session, taking into account that the application
	handle each Session as a user. For this application only emphasis is made
	in the last purchase (in case the user manually revisits the page
	purchase).

	-> ProductTable (JONATHAN CAZCO): Bean in charge of controling the table on which the different
	products are displayed, as well as the different events.

	-> Product (ERICK NAUNAY): POJO class to represent a product. Includes Serial information
	Number, Stock Number, PurchaseNum (Amount / Quantity to buy), Unit Price,
	Total Price (calculated as PurchaseNum * Unit Price). Includes auxiliary attributes
	amountStr and errorStr to facilitate validations and error messages for the
	user.

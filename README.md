# DIST-3
Simple JSF Table application

The application has 2 main html pages which the user interacts with.
	
	-> productDetails (MIKE PINTA): page where the user enters the number of products
	what do you want to buy. It is mainly handled by the ProductsViewBean bean
	(ViewScoped); therefore it can be opened by multiple tabs / windows within the
	same browser to make independent purchases. However, the "users"
	are defined from each Session, so purchases are stored in, implements error messaging.
	UserManager (SessionScoped). 
	
	-> purchaseItems (MIKE) PINTA: page in which the user enters after making a
	successful purchase on the productDetails.xhtml page. Information is accessed here
	stored in UserManager (SessionScoped), which corresponds to the last
	purchase made in that Session. The table is generated in a scrollable section, implements error messaging.

Project Structure

	-> ProductsBean (ERICK NAUNAY): Bean that is in charge of maintaining the state
	of the products and their respective stocks.

	-> UserManager (JONATHAN CAZCO): Bean that is responsible for maintaining the state of
	purchases made within that session, taking into account that the application
	handle each Session as a user. For this application only emphasis is made
	in the last purchase (in case the user manually revisits the page
	purchasedItems.xhtml, for example).

	-> ProductsViewBean (JONATHAN CAZCO): Bean que se encarga de manejar la vista a
	través de la que el usuario realiza la compra de productos. Fue añadida para evitar
	errores con el cargado de componentes que surgían al manejar únicamente el bean
	UserManager.

	-> Products (ERICK NAUNAY): class to represent a product. Includes Serial information
	Number, Stock Number, PurchaseNum (Amount / Quantity to buy), Unit Price,
	Total Price (calculated as PurchaseNum * Unit Price). Includes auxiliary attributes
	amountStr and errorStr to facilitate validations and error messages for the
	user.

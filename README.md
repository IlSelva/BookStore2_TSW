# BookStore
Project for TSW exam - University of Salerno - with professors Costagliola and De Rosa, remake of old Project BookStore_TSW

## How to deploy:
in class ConPool change database settings (url, user, password ,...)
the folders for images configured in web.xml (for frontend) and in the upload servlet (for backend) should be changed as needed,
It also must be included the path in the conf/server.xml of tomcat:
  <Context docBase="Path\to\product\floder" path="/BookStore/immagini/prodotti"/>
	<Context docBase="Path\to\author\folder" path="/BookStore/immagini/autori"/>
  
  (it can also be included in the IntelliJ deploy)
  
  the project is built through maven, to create the war is necessary just a "mvn clean package".

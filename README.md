# Name of project: Online shop </br>
# Group: SE-2207 </br>
# Team members: Amirzhan Mukhidinov, Akarys Nuradinov </br>
The project is an online store specializing in the sale of laptops from three brands: Acer, Macbook, and Lenovo. The store aims to provide customers with a convenient platform to explore and purchase laptops based on their preferences and requirements. The primary purpose is to establish an effective and convenient e-commerce presence for selling laptops online. Also this platform will notify users about discounts and other news. The main objective is to create comprehensive product catalog, user authentification and integrate payment.</br>
In this project we used 6 patterns. 
# Singleton 
pattern was represented as a database in our project. 
# Strategy pattern
was integrated as a payment, where we have 2 strategies. 
First is payment by credit card and second is by paypal. 
# Decorator pattern 
was used to add additional RAM and SSD storage. 
# Adapter pattern 
is represented as an adapter of powersource. User can choose the powersource which he uses to charge laptop. 
# Factory pattern 
used to create different laptops. In our case we have Laptop Interface, which is implemented in 3 classes(AcerLaptop, MacbookLaptop and LenovoLaptop). 
# Observer pattern 
is used to notify users about discount.</br>
![uml](https://github.com/oop111/final/assets/121971831/102e7e8f-ce51-497b-8df2-c70b0425bc2e)

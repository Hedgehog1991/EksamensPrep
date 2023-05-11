Before trying to connect to a database you need server access. The easiest way to do this with the least
room for error. Is to enter Mysql root Local instance. Then select Administration next to Schemas far left on Mysql workbench.
Users and Privileges -> add account (what you like your connection username and pw to be in Intellij).
Check of administrative priviliges to avoid issues later. This is your root database SERVER login info. It is unrelated SQL queries.
It might create problems when initially trying to run the java if the server connection is not correct. And the first user created should reflect the
server username and password to avoid confusion. Later on it is possible to create new users. which can be given special priviliges when it comes to which
database connection they can use and what schemas they have access to.


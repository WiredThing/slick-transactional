slick-transactional
===================

A Transactional monad for managing DB reads and updates using slick

Still very much in an experimental state. I'm currently exploring different approaches to how we might use this in code, particularly how to bridge functional and OO code styles.

As with slick, the aim here is not to create an ORM, but rather to manage transactions so that the code gets a consistent view of the database depending on whether it is just reading or if it is updating the databse. An object model layered on top of slick, using mapped projections, should see a consistent set of data as it lazily traverses the object graph, and that view is immutable. Updates are applied using transactional isolation so that they represent a new state of the database that is isolated from the object model's earlier view.

I've been influenced here by the Haskell IO monad as a way of mapping one "world-state" to another "world-state", where the world-state here is the state of the database.

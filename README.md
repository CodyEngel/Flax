# Flax
Flax is another way to build Android applications.

Currently this project is still evolving into a library which can be easily used in Android projects. The idea is add layers between your UI and the underlying models. With that, your "view" (Activity, Fragment, View, ViewController, etc) is where things start and it branches out into two directions.

1) Responder -> Store
The responder is responsible for responding to actions/events which are coming from your view. These actions can be anything from activity lifecycle to a user entering their username and password. From there the responder parses/massages the action into something that's useable by the store (which contains all your models).

2) Renderer -> Store
The renderer is responsible for taking changes from the store and helping the view render that data. It should listen to the store for changes and when any changes are made it should invoke the appropriate view methods.

3) Store
The store is a collection of models. Currently it will lazy load models and then store their reference in a map for easy retrevial. This should be decoupled from the view lifecycle so that it can survive configuration changes, at the moment it's a singleton, but this could change before the initial "stable" release.

## The Bad Diagram
So this diagram might not be the best... But essentially what this shows is that the store should not reference the responder or the renderer. The responder will pass data into the store, the store will store that data and then notify anything that is subscribed to it. The renderer should subscribe to the store so it can receive any updates.

![alt tag](https://github.com/CodyEngel/Flax/blob/master/architecture-flow.png)

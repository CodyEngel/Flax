# Flax
Flax is another way to build Android applications.

Currently this project is still evolving into a library which can be easily used in Android projects. The idea is add layers between your UI and the underlying models. With that, your "view" (Activity, Fragment, View, ViewController, etc) is where things start and it branches out into two directions.

1) Responder -> Store
The responder is responsible for responding to actions/events which are coming from your view. These actions can be anything from activity lifecycle to a user entering their username and password. From there the responder parses/massages the flaxAction into something that's useable by the store (which contains all your models).

2) Renderer -> Store
The renderer is responsible for taking changes from the store and helping the view render that data. It should listen to the store for changes and when any changes are made it should invoke the appropriate view methods.

3) Store
The store is a collection of models. Currently it will lazy load models and then store their reference in a map for easy retrevial. This should be decoupled from the view lifecycle so that it can survive configuration changes, at the moment it's a singleton, but this could change before the initial "stable" release.

## The Bad Diagram
So this diagram might not be the best... But essentially what this shows is that the store should not reference the responder or the renderer. The responder will pass data into the store, the store will store that data and then notify anything that is subscribed to it. The renderer should subscribe to the store so it can receive any updates.

![alt tag](https://github.com/CodyEngel/Flax/blob/master/architecture-flow.png)

## Hello Flax
So let's put these together with [Hello Flax](https://github.com/CodyEngel/Flax/tree/master/helloflax). This is a very simple implementing of Flax and is just intended to show you the main pieces (objects) needed for an app. One thing you may notice is there is no `Store` mentioned in this app, that's because internally this is managed by the `Renderer` and `Responder` internally. If you want a slightly more involved example then check out [SimpleNetworking](https://github.com/CodyEngel/Flax/tree/master/simplenetworking) which shows how this works if you need to make network requests.

### MainActivity
This makes use of ButterKnife things a little bit. The main thing to keep in mind is that we are implementing a View interface (hello Model-View-Presenter), and creating a MainRenderer as well as a MainResponderer. The Responder is responsible for responding to events from Android (in this case, clicks) and the Renderer is responsible for rendering our model updates on our View. Also keep in mind that the Responder and Renderer should be disposed when the onDestroy is called.

```java
public class MainActivity extends AppCompatActivity implements MainView {
    @BindView(R.id.button) Button button;
    @BindView(R.id.text) TextView text;

    private Responder responder;
    private Renderer renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        renderer = new MainRenderer(this);
        responder = new MainResponder(new ActionObservableBuilder().mapClick(button).build());
    }

    @Override
    protected void onDestroy() {
        renderer.dispose();
        responder.dispose();
        super.onDestroy();
    }

    @Override
    public void setText(CharSequence text) {
        this.text.setText(text);
    }
}
```

### MainRenderer
The `Renderer` is responsible for taking model updates and updating our UI. In this example it takes `MainView` as the View and when `modelUpdated` is called we simply call the appropriate methods provided to us from `MainView`. In this example we care about the current value in our model (which is a number that is incremented when clicked).

```java
class MainRenderer extends Renderer<MainModel, MainView> {

    public MainRenderer(MainView view) {
        super(view);
    }

    @Override
    protected void modelUpdated(MainModel updatedModel) {
        getView().setText(String.valueOf(updatedModel.getValue()));
    }

}
```

### MainResponder
The `Responder` needs to respond to events from the system. In this example we really only care about when we receive a click event so that we can notify the model to update itself, and you can see that in the actionReceived callback.

```java
class MainResponder extends Responder<MainModel> {

    MainResponder(Observable<Action> actions) {
        super(actions);
    }

    @Override
    protected void actionReceived(Action flaxAction) {
        switch (flaxAction.getActionType()) {
            case Action.CLICK:
                if (flaxAction.getViewId() == R.id.button) {
                    getModel().plus();
                }
                break;
            default:
                throw new UnsupportedOperationException(String.format(Locale.US, "Action Type %s Not Supported", flaxAction.getActionType()));
        }
    }

    @Override
    protected void errorReceived(Throwable error) {
        Log.e(getClass().getName(), error.getMessage());
    }

    @Override
    protected void completed() {
        Log.i(getClass().getName(), "Completed");
    }

}
```

### MainModel
The `Model` is responsible for keeping the current state of our view. It is updated by the Responder and then rendered by the `Renderer`. It should extend from Model and the only internal method you need to be concerned about is `notifyModelChanged`, which should be called whenever you want to notify the `Renderer` of changes.

```java
class MainModel extends Model<MainModel> {

    private Integer value = 0;

    public MainModel() {
        super();
    }

    void plus() {
        value++;
        notifyModelChanged();
    }

    Integer getValue() {
        return value;
    }

}
```

### MainView
Again, the View is just an interface which should be implemented by your `Activity`, `Fragment`, `View`, or whatever you are using for your view (it really shouldn't matter, if you experience issues, file an issue). If you have built an app using Model-View-Presenter this should be very familiar. In this example our `MainView` has a method `setText` which can be used to update the `text`.

```java
interface MainView extends View {

    void setText(CharSequence text);

}
```

## License
```
 Copyright (c) 2017 Cody Engel

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
```

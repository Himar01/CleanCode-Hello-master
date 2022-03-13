package es.ulpgc.eite.cleancode.helloworld.bye;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.helloworld.app.AppMediator;
import es.ulpgc.eite.cleancode.helloworld.app.ByeToHelloState;
import es.ulpgc.eite.cleancode.helloworld.app.HelloToByeState;

public class ByePresenter implements ByeContract.Presenter {

  public static String TAG = ByePresenter.class.getSimpleName();

  private WeakReference<ByeContract.View> view;
  private ByeState state;
  private ByeContract.Model model;
  private AppMediator mediator;

  public ByePresenter(AppMediator mediator) {
    this.mediator = mediator;
    state = mediator.getByeState();
  }

/*  @Override
  public void onStart() {
    // Log.e(TAG, "onStart()");

    // initialize the state
    state = new ByeState();

    // call the model and update the state
    state.data = model.getStoredData();

    // use passed state if is necessary
    PreviousToByeState savedState = getStateFromPreviousScreen();
    if (savedState != null) {

      // update the model if is necessary
      model.onDataFromPreviousScreen(savedState.data);

      // update the state if is necessary
      state.data = savedState.data;
    }
  }*/

 /* @Override
  public void onRestart() {
    // Log.e(TAG, "onRestart()");

    // update the model if is necessary
    model.onRestartScreen(state.data);
  }*/

  @Override
  public void onResume() {
    // Log.e(TAG, "onResume()");

    // use passed state if is necessary
    HelloToByeState savedState = getDataFromHelloScreen();
    if (savedState != null) {

      // update the model if is necessary
      model.onDataFromNextScreen(savedState.message);

      // update the state if is necessary
      state.byeMessage = savedState.message;
    }

    // call the model and update the state
    //state.data = model.getStoredData();

    // update the view
    view.get().displayByeData(state);

  }

  private HelloToByeState getDataFromHelloScreen() {
    return mediator.getHelloToByeState();
  }

  @Override
  public void onBackPressed() {
    // Log.e(TAG, "onBackPressed()");
  }

  @Override
  public void onPause() {
    // Log.e(TAG, "onPause()");
  }

  @Override
  public void onDestroy() {
    // Log.e(TAG, "onDestroy()");
  }

 // private NextToByeState getStateFromNextScreen() {
 //   return mediator.getNextByeScreenState();
 // }

//  private void passStateToNextScreen(ByeToNextState state) {
//    mediator.setNextByeScreenState(state);
//  }

//  private void passStateToPreviousScreen(ByeToPreviousState state) {
//    mediator.setPreviousByeScreenState(state);
//  }

//  private HelloToByeState getStateFromPreviousScreen() {
//    return mediator.getPreviousByeScreenState();
//  }

  @Override
  public void injectView(WeakReference<ByeContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(ByeContract.Model model) {
    this.model = model;
  }

  @Override
  public void goHelloButtonClicked() {
    ByeToHelloState newState = new ByeToHelloState(state.byeMessage);
    passDataToHelloScreen(newState);
    navigateToHelloScreen();
  }

  private void navigateToHelloScreen() {
    view.get().navigateToHelloScreen();
  }

  private void passDataToHelloScreen(ByeToHelloState state) {
    mediator.setByeToHelloState(new ByeToHelloState(state.message));
  }

  @Override
  public void sayByeButtonClicked() {
/*    state.byeMessage = "";
    view.get().displayByeData(state);*/
    startByeMessageAsyncTask();
  }

  private void startByeMessageAsyncTask() {
    state.byeMessage = model.getByeMessage();
    view.get().displayByeData(state);
    mediator.setHelloToByeState(new HelloToByeState(state.byeMessage));
  }

}
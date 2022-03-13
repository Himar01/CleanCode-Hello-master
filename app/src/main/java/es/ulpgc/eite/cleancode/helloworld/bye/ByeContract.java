package es.ulpgc.eite.cleancode.helloworld.bye;

import java.lang.ref.WeakReference;

public interface ByeContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayByeData(ByeViewModel viewModel);

//        void onDataUpdated(ByeViewModel viewModel);

//        void navigateToNextScreen();/
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void sayByeButtonClicked();
        void goHelloButtonClicked();

        void onResume();

/*        void onStart();

        void onRestart();*/

        void onBackPressed();

        void onPause();

        void onDestroy();
    }

    interface Model {

        String getByeMessage();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);
    }

}
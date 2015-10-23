package de.mpaeschke.simplegallery.presentation.presenter;

import de.mpaeschke.simplegallery.presentation.model.MVPModel;
import de.mpaeschke.simplegallery.presentation.view.MVPView;

/**
 * Interface representing a Presenter in a model view presenter (MVP) pattern.
 */
public interface MVPPresenter {
    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void pause();

    void setView(MVPView view);
    void setModel(MVPModel model);
}

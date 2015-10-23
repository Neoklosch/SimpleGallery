package de.mpaeschke.simplegallery.presentation.view;

/**
 * Created by markuspaeschke on 21.10.15.
 */
public interface LoadingMVPView extends MVPView {
    void showLoading();
    void hideLoading();
    void showEmpty();
    void hideEmpty();
    void showError();
    void hideError();
}

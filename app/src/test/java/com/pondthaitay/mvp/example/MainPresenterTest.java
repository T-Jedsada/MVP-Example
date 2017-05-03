package com.pondthaitay.mvp.example;

import com.pondthaitay.mvp.example.ui.MainPresenter;
import com.pondthaitay.mvp.example.ui.MainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.spy;

@RunWith(PowerMockRunner.class)
public class MainPresenterTest {

    @Mock
    MainView.View view;
    private MainPresenter spyPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MainPresenter presenter = new MainPresenter();
        presenter.attachView(view);
        spyPresenter = spy(presenter);
        spyPresenter.attachView(view);
    }

    @Test
    public void plus() throws Exception {
        spyPresenter.plus(4, 4);
        verify(view, times(1)).setOnResultPlus(eq(8));
        assertThat(spyPresenter.getResultPlus(), is(8));
    }
}
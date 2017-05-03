package com.pondthaitay.mvp.example.ui;

import com.pondthaitay.mvp.example.JsonMockUtility;
import com.pondthaitay.mvp.example.api.GithubApiService;
import com.pondthaitay.mvp.example.api.GithubServiceManager;
import com.pondthaitay.mvp.example.base.BaseInterface;
import com.pondthaitay.mvp.example.dao.UserInfoDao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
public class MainPresenterTest {

    @Mock
    MainInterface.View mockView;
    MainPresenter presenter;
    MainPresenter spyPresenter;

    @Captor
    private ArgumentCaptor<Callback<UserInfoDao>> retrofitCallBack;

    @Mock
    private Call<UserInfoDao> mockCall;

    @Mock
    private GithubApiService mockApi;

    private GithubServiceManager spyManager;
    private JsonMockUtility jsonUtil;

    @Before
    public void setUp() {
        jsonUtil = new JsonMockUtility();
        MockitoAnnotations.initMocks(this);

        GithubServiceManager manager = GithubServiceManager.getInstance();
        spyManager = spy(manager);
        spyManager.setApi(mockApi);

        presenter = new MainPresenter();
        presenter.setManager(spyManager);
        presenter.attachView(mockView);
        spyPresenter = spy(presenter);
        spyPresenter.setManager(spyManager);
        spyPresenter.attachView(mockView);
    }

    @Test
    public void create_presenter() {
        assertThat(MainPresenter.create(), is(BaseInterface.Presenter.class));
    }

    @Test
    public void requestBeerProduct_success() {
        UserInfoDao mockResult = jsonUtil.getJsonToMock(
                "user_info_success.json",
                UserInfoDao.class);
        Response<UserInfoDao> mockResponse = Response.success(mockResult);
        when(spyManager.requestUserInfoCall(anyString())).thenReturn(mockCall);

        presenter.getUserInfo("");
        verify(mockView, times(1)).showProgressDialog();
        verify(mockCall).enqueue(retrofitCallBack.capture());
        retrofitCallBack.getValue().onResponse(mockCall, mockResponse);
        verify(mockView, times(1)).hideProgressDialog();
        verify(mockView, times(1)).setUserInfo(eq(mockResult));
    }

    @SuppressWarnings("ThrowableInstanceNeverThrown")
    @Test
    public void requestBeerProduct_failure() {
        Throwable throwable = new Throwable("error");
        when(spyManager.requestUserInfoCall(anyString())).thenReturn(mockCall);

        presenter.getUserInfo("");
        verify(mockView, times(1)).showProgressDialog();
        verify(mockCall).enqueue(retrofitCallBack.capture());
        retrofitCallBack.getValue().onFailure(mockCall, throwable);
        verify(mockView, times(1)).hideProgressDialog();
        verify(mockView, times(1)).showError(eq(throwable.getMessage()));
    }
}
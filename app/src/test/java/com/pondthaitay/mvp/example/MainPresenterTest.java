package com.pondthaitay.mvp.example;

import com.pondthaitay.mvp.example.api.GithubApiService;
import com.pondthaitay.mvp.example.api.GithubServiceManager;
import com.pondthaitay.mvp.example.base.BaseView;
import com.pondthaitay.mvp.example.dao.UserInfoDao;
import com.pondthaitay.mvp.example.ui.MainPresenter;
import com.pondthaitay.mvp.example.ui.MainView;

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

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
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
    private MainView.View mockView;
    @Mock
    private GithubApiService mockApi;
    @Captor
    private ArgumentCaptor<Callback<UserInfoDao>> retrofitCallBack;
    @Mock
    private Call<UserInfoDao> mockCall;

    private MainPresenter spyPresenter;
    private JsonMockUtil jsonMockUtil;
    private GithubServiceManager spyManager;

    @Before
    public void setUp() {
        jsonMockUtil = new JsonMockUtil();
        MockitoAnnotations.initMocks(this);

        GithubServiceManager manager = GithubServiceManager.getInstance();
        spyManager = spy(manager);
        spyManager.setApi(mockApi);

        MainPresenter presenter = new MainPresenter();
        presenter.setManager(spyManager);
        presenter.attachView(mockView);
        spyPresenter = spy(presenter);
        spyPresenter.setManager(spyManager);
        spyPresenter.attachView(mockView);
    }

    @Test
    public void plus() throws Exception {
        spyPresenter.plus(4, 4);
        verify(mockView, times(1)).setOnResultPlus(eq(8));
        assertThat(spyPresenter.getResultPlus(), is(8));
    }

    @Test
    public void minus() throws Exception {
        spyPresenter.minus(4, 4);
        verify(mockView, times(1)).setOnResultPlus(eq(0));
        assertThat(spyPresenter.getResultPlus(), is(0));
    }

    @Test
    public void createPresenter() throws Exception {
        assertThat(MainPresenter.create(), any(BaseView.Presenter.class));
    }

    @Test
    public void getUserInfoSuccess() {
        UserInfoDao mockResult = jsonMockUtil.getJsonToMock(
                "user_info_dao_success.json",
                UserInfoDao.class);

        Response<UserInfoDao> mockResponse = Response.success(mockResult);
        when(spyManager.requestUserInfoCall(anyString())).thenReturn(mockCall);
        spyPresenter.getUserInfo("pondthaitay");
        verify(mockView, times(1)).showProgressDialog();
        mockCall.enqueue(retrofitCallBack.capture());
        retrofitCallBack.getValue().onResponse(mockCall, mockResponse);
        verify(mockView, times(1)).hideProgressDialog();
        verify(mockView, times(1)).showUserInfo(eq(mockResponse.body()));
    }
}
package ws.com.mvvm_dome.mvp;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import java.util.List;

import ws.com.mvvm_dome.R;
import ws.com.mvvm_dome.mvp.annotation.InjectPresenter;
import ws.com.mvvm_dome.mvp.presenter.StarPresenter;
import ws.com.mvvm_dome.mvp.view.IStarView;

public class MvpActivity extends BaseActivity<StarPresenter, IStarView> implements IStarView {

    RecyclerView recyclerView;
    StarAdapter mAdapter;
    @InjectPresenter
    StarPresenter starPresenter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

//        starPresenter = new StarPresenter();
//        starPresenter.onAttach(this);
        mPresenter.fetch();
//        starPresenter.fetch();
    }

    @Override
    protected StarPresenter createPresenter() {
        return new StarPresenter();
    }


    @Override
    public void showErrorMessage(String error) {

    }

    @Override
    public void showStarList(List<Star> starList) {
        mAdapter = StarAdapter.getInstance();
        mAdapter.setStar(starList);
        recyclerView.setAdapter(mAdapter);
    }

}
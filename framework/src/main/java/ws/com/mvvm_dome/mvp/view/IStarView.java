package ws.com.mvvm_dome.mvp.view;

import java.util.List;

import ws.com.mvvm_dome.mvp.Star;

public interface IStarView extends IBaseView{
    //展示数据
    void showStarList(List<Star> starList);
}

package com.dingceng.guiddemo;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class GuidePagerAdapter extends PagerAdapter{
    private List<View> guideList;

    public void setGuideList(List<View> guideList) {
        this.guideList = guideList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return guideList==null?0:guideList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(guideList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(guideList.get(position));
        return guideList.get(position);
    }
}

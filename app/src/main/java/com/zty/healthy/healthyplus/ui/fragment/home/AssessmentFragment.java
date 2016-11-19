package com.zty.healthy.healthyplus.ui.fragment.home;

import android.view.View;
import android.widget.ExpandableListView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.adapter.AssessmentAdapter;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.base.BaseFragment;
import com.zty.healthy.healthyplus.model.AdviceModel;
import com.zty.healthy.healthyplus.model.AssessClassModel;
import com.zty.healthy.healthyplus.model.AssessQuestModel;
import com.zty.healthy.healthyplus.model.ResultBean;
import com.zty.healthy.healthyplus.service.AssessmentService;
import com.zty.healthy.healthyplus.utils.ResultUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在线评估
 * Created by zty on 2016/8/24.
 */
public class AssessmentFragment extends BaseFragment implements ExpandableListView.OnGroupClickListener {

    private CircularImageView imgAssessment;
    private ExpandableListView expandList;

    private List<AssessClassModel> assessClassModels = new ArrayList<>();

    private AssessmentAdapter adapter;

    private int currentGroup = 0;

    @Override
    public int getContentVew() {
        return R.layout.fragment_assessment;
    }

    @Override
    public void initView(View view) {

        ((BaseActivity) context).title.setText("健康评估");

        imgAssessment = (CircularImageView) view.findViewById(R.id.imgAssessment);
        expandList = (ExpandableListView) view.findViewById(R.id.expandList);

        expandList.setOnGroupClickListener(this);

    }

    @Override
    public void initData() {
        AssessmentService.getGroupItem(1, context, this);

        setData();
    }

    private void setData() {

        AssessClassModel model = new AssessClassModel();
        model.setName("aaaa");
        assessClassModels.add(model);
        model = new AssessClassModel();
        model.setName("bbbb");
        assessClassModels.add(model);

        adapter = new AssessmentAdapter(context, assessClassModels);
        expandList.setAdapter(adapter);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

        ResultBean resultBean = ResultUtil.getResult(response, false);

        if (resultBean.isSuccess()) {
            switch (requestCode) {
                case 1:
                    assessClassModels.clear();
                    List<AssessClassModel> models = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<AssessClassModel>>() {
                    }.getType());
                    if (models != null && models.size() > 0) {

                        assessClassModels.addAll(models);

                        adapter = new AssessmentAdapter(context, assessClassModels);
                        expandList.setAdapter(adapter);
                    }

                    break;
                case 2:

                    List<AssessQuestModel> assessQuestModels = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<AssessQuestModel>>() {
                    }.getType());

                    if (assessQuestModels != null && assessQuestModels.size() > 0) {
                        adapter.setChildrenData(currentGroup, assessQuestModels);
                        expandList.expandGroup(currentGroup);
                    }
                    break;
            }
        }

    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        boolean expanded = parent.isGroupExpanded(groupPosition);
        currentGroup = groupPosition;
        if (!expanded) {
            List<AssessQuestModel> assessQuestModels = new ArrayList<>();

            AssessQuestModel model = new AssessQuestModel();
            model.setTitle(assessClassModels.get(groupPosition).getName() + "1");
            assessQuestModels.add(model);
            model = new AssessQuestModel();
            model.setTitle(assessClassModels.get(groupPosition).getName() + "2");
            assessQuestModels.add(model);

            adapter.setChildrenData(currentGroup, assessQuestModels);
            expandList.expandGroup(currentGroup);

            AssessmentService.getAssessQuest(2, context, assessClassModels.get(groupPosition).getId(), this);
            return true;
        }
        return false;
    }
}

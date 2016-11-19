package com.zty.healthy.healthyplus.manager;

import android.content.Context;

import com.zty.healthy.healthyplus.base.MyApplication;
import com.zty.healthy.healthyplus.db.FinalDb;
import com.zty.healthy.healthyplus.model.OldManModel;
import com.zty.healthy.healthyplus.utils.SharedPrefUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 老人账户管理
 * Created by zty on 2016/8/26.
 */
public class OldManAccountManger {

    private static OldManAccountManger instance;

    private static FinalDb db;

    public static OldManAccountManger getInstance() {
        if (instance == null) {
            instance = new OldManAccountManger();
        }
        db = MyApplication.getInstance().finalDb;
        return instance;
    }

    /**
     * 获取数据库中老人账户
     *
     * @return
     */
    public List<OldManModel> getAccount() {

        return db.findAll(OldManModel.class);
    }

    /**
     * 更新老人(如果没有就添加到数据库)
     *
     * @param model
     */
    public void setCurrentAccount(OldManModel model) {

        boolean isHave = false;

        List<OldManModel> models = getAccount();

        if (models != null && models.size() > 0) {

            int count = models.size();
            for (int i = 0; i < count; i++) {
                if (models.get(i).getName().equals(model.getName())) {
                    isHave = true;
                }
                db.update(model, "name='" + model.getName() + "'");
            }
        }

        if (!isHave)
            db.save(model);

    }
}

package com.geetol.mylibrary.Dialog;

import com.geetol.mylibrary.InterFace.DialogInterFaceForAgreement;
import com.geetol.mylibrary.R;


/**
 * 哼哈 弹窗
 */

public class HengHaDialog extends TopImgDialog {


    public HengHaDialog(DialogInterFaceForAgreement listener) {
        super(listener);
    }

    @Override
    public int getLayout() {
        return R.layout.heng_ha_dialog_agreement;
    }

}

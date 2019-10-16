package com.geetol.mylibrary.Dialog;

import com.geetol.mylibrary.InterFace.DialogInterFaceForAgreement;
import com.geetol.mylibrary.R;


/**
 * 紫伊 弹窗
 */

public class ZiYiDialog extends TopImgDialog {


    public ZiYiDialog(DialogInterFaceForAgreement listener) {
        super(listener);
    }

    @Override
    public int getLayout() {
        return R.layout.zi_yi_dialog_agreement;
    }


}

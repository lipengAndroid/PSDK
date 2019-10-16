package com.geetol.mylibrary.Dialog;

import com.geetol.mylibrary.InterFace.DialogInterFaceForAgreement;
import com.geetol.mylibrary.R;


/**
 * 紫伊 弹窗
 */

public class JRYDialog extends TopImgDialog {


    public JRYDialog(DialogInterFaceForAgreement listener) {
        super(listener);
    }

    @Override
    public int getLayout() {
        return R.layout.jry_dialog_agreement;
    }


}

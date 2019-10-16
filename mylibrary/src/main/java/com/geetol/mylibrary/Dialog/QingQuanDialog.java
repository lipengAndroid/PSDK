package com.geetol.mylibrary.Dialog;

import com.geetol.mylibrary.InterFace.DialogInterFaceForAgreement;
import com.geetol.mylibrary.R;


/**
 * 清泉 弹窗
 */

public class QingQuanDialog extends TopImgDialog {


    public QingQuanDialog(DialogInterFaceForAgreement listener) {
        super(listener);
    }

    @Override
    public int getLayout() {
        return R.layout.qing_quan_dialog_agreement;
    }

}

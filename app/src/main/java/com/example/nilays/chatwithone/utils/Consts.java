package com.example.nilays.chatwithone.utils;

import com.example.nilays.chatwithone.R;
import com.quickblox.sample.core.utils.ResourceUtils;

public interface Consts {
//     In GCM, the Sender ID is a project ID that you acquire from the API console
    String GCM_SENDER_ID = "199178173505";

    String QB_APP_ID = "45643";
    String QB_AUTH_KEY = "35Y3dLSLMjOCQSf";
    String QB_AUTH_SECRET = "gDXvd73STFgXSe4";
    String QB_ACCOUNT_KEY = "TxtyFiFrS7XEuGqZPcAQ";

    String QB_USERS_TAG = "nilay";

//    String QB_USERS_NAME = "siddharth";
//    String QB_USERS_PASSWORD = "siddharth123";

    String QB_USERS_NAME = "nilay";
    String QB_USERS_PASSWORD = "nilay123";

    String QB_USERS_FULLNAME = "Siddharth Shah";
    String QB_USERS_PHONE = "9033462769";
    String QB_USERS_EMAIL = "nilay@yopmail.com";
    String QB_USERS_CITY_COMPANY = "City=Ahmedabad&Company=Rotex";

    int PREFERRED_IMAGE_SIZE_PREVIEW = ResourceUtils.getDimen(R.dimen.chat_attachment_preview_size);
    int PREFERRED_IMAGE_SIZE_FULL = ResourceUtils.dpToPx(320);
}
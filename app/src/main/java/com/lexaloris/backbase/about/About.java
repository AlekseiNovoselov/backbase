package com.lexaloris.backbase.about;

public interface About {

    interface Model {

        void getAboutInfo();
    }

    interface Presenter {

        void getAboutInfo();

        void onSuccess(AboutInfo aboutInfo);

        void onFail();
    }

    interface View {

        void setCompanyName(String companyName);

        void setCompanyAddress(String companyAddress);

        void setCompanyPostalCode(String postalCode);

        void setCompanyCity(String companyCity);

        void setAboutInfo(String info);

        void showError();

        void showProgress();

        void hideProgress();
    }
}

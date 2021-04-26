package utility;

import enums.Bic;

public class Environment {

    Bic bic = null;

    public Bic setBic(String selectedRadioBtn) {
        switch (selectedRadioBtn) {
            case "ALT":
            case "FLT":
                return bic = Bic.CBVILT2X;
            case "ALV":
            case "FLV":
                return bic = Bic.UNLALV2X;
            case "AEE":
            case "FEE":
                return bic = Bic.EEUHEE2X;
        }
        return bic;
    }
}

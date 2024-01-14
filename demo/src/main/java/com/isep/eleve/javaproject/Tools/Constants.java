package com.isep.eleve.javaproject.Tools;

import java.util.Map;

/**
 * This class contains constant values used
 * in the application.
 */
public class Constants {
  /**
   * The path to the add event view FXML file.
   */
  public static final String ADD_EVENT_VIEW_PATH = "/com/isep/eleve/javaproject/view/AddEventsView.fxml";

  /**
   * The path to the analysis view FXML file.
   */
  public static final String ANALYSIS_VIEW_PATH = "/com/isep/eleve/javaproject/view/AnalysisView.fxml";

  /**
   * The path to the asset information view FXML file.
   */
  public static final String ASSET_INFORMATION_VIEW_PATH = "/com/isep/eleve/javaproject/view/AssetInformationView.fxml";

  /**
   * The path to the buy asset view FXML file.
   */
  public static final String BUY_ASSET_VIEW_PATH = "/com/isep/eleve/javaproject/view/BuyAssetView.fxml";

  /**
   * The path to the buy asset view FXML file.
   */
  public static final String BUY_CRYPTO_VIEW_PATH = "/com/isep/eleve/javaproject/view/BuyCryptoView.fxml";

  /**
   * The path to the clone portfolio view FXML file.
   */
  public static final String CLONE_PORTFOLIO_VIEW_PATH = "/com/isep/eleve/javaproject/view/ClonePortfolioView.fxml";

  /**
   * The path to the crypto information view FXML file.
   */
  public static final String CRYPTO_INFORMATION_VIEW_PATH = "/com/isep/eleve/javaproject/view/CryptoInformationView.fxml";

  /**
   * The path to the delete events view FXML file.
   */
  public static final String DELETE_EVENTS_VIEW_PATH = "/com/isep/eleve/javaproject/view/DeleteEventsView.fxml";

  /**
   * The path to the demonstrate value view FXML file.
   */
  public static final String DEMONSTRATE_VALUE_VIEW_PATH = "/com/isep/eleve/javaproject/view/DemonstrateValueView.fxml";

  /**
   * The path to the home view FXML file.
   */
  public static final String HOME_VIEW_PATH = "/com/isep/eleve/javaproject/view/HomePageView.fxml";

  /**
   * The path to the login view FXML file.
   */
  public static final String LOGIN_VIEW_PATH = "/com/isep/eleve/javaproject/view/LoginView.fxml";
  public static final String LOGIN_VIEW_CSS_PATH = "/com/isep/eleve/javaproject/view/LoginView.css";
  /**
   * The path to the new portfolio view FXML file.
   */
  public static final String NEW_PORTFOLIO_VIEW_PATH = "/com/isep/eleve/javaproject/view/NewPortfolioView.fxml";

  /**
   * The path to the portfolio information view FXML file.
   */
  public static final String PORTFOLIO_INFORMATION_VIEW_PATH = "/com/isep/eleve/javaproject/view/PortfolioInformationView.fxml";

  /**
   * The path to the registration view FXML file.
   */
  public static final String REGISTRATION_VIEW_PATH = "/com/isep/eleve/javaproject/view/RegistrationView.fxml";
  public static final String REGISTRATION_VIEW_CSS_PATH = "/com/isep/eleve/javaproject/view/RegistrationView.css";
  /**
   * The path to the sell asset view FXML file.
   */
  public static final String SELL_ASSET_VIEW_PATH = "/com/isep/eleve/javaproject/view/SellAssetView.fxml";

  /**
   * The path to the sell crypto view FXML file.
   */
  public static final String SELL_CRYPTO_VIEW_PATH = "/com/isep/eleve/javaproject/view/SellCryptoView.fxml";

  public static final String ADD_ASSET_VIEW_PATH = "/com/isep/eleve/javaproject/view/AddAssetView.fxml";

  /**
   * The encryption types available.
   */
  public static enum ENCRYPT_TYPE {
    MD5,
    SHA1,
    SHA256,
    SHA512,
  };

  /**
   * The types of registration failures.
   */
  public static enum REGISTRATION_FAILIURE_TYPE {
    PASSWORD_NOT_SAME,
    USERNAME_ALREADY_EXIST,
  };

  /*public enum ASSET_TYPE {
    CASH("CASH"),
    FIXED_DEPOSIT("FIXED_DEPOSIT"),
    CRYPTO("CRYPTO"),
    STOCK("STOCK");

    private String value;

    ASSET_TYPE(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static ASSET_TYPE fromString(String text) {
        for (ASSET_TYPE b : ASSET_TYPE.values()) {
            if (b.value.equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}*/
  public static enum ASSET_TYPE {
    CASH,
    FIXED_DEPOSIT,
    CRYPTO,
    STOCK
  }


  public static enum CHANGE_TYPE {
    ADD,
    SUBTRACT,
    SET
  }

  public static enum TRANSACTION_TYPE {
    BUY,
    SELL,
    BUT_MARKET,
    SELL_MARKET
  }

  public static Map<String, ASSET_TYPE> ASSET_TYPE_MAP = Map.of(
    "CASH", ASSET_TYPE.CASH,
    "FIXED_DEPOSIT", ASSET_TYPE.FIXED_DEPOSIT,
    "BTC", ASSET_TYPE.CRYPTO,
    "AAPL", ASSET_TYPE.STOCK
  );

}
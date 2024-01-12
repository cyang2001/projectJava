package com.isep.eleve.javaproject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isep.eleve.javaproject.App;
import com.isep.eleve.javaproject.config.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
@Controller
public class MainController implements Initializable {
	private static final Logger logger = Logger.getLogger(MainController.class.getName());
  
	private App app;

	@FXML
	private TreeView<String> main_treeview;

	@FXML
	private ScrollPane main_scroll_pane;

	@FXML
	private AnchorPane main_pane_under_scroll;

  @Autowired
  public MainController(App app) {
    this.app = app;
  }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setTreeView();
	}


	@SuppressWarnings("unchecked")
	public void setTreeView() {
		TreeItem<String> root = new TreeItem<String>(ViewConfig.MAIN_TREE_HEADER);
		root.setExpanded(true);
		root.getChildren().addAll(new TreeItem<String>(ViewConfig.MAIN_TREE_HEADER_ITEM1),
				new TreeItem<String>(ViewConfig.MAIN_TREE_HEADER_ITEM2),
				new TreeItem<String>(ViewConfig.MAIN_TREE_HEADER_ITEM3),
				new TreeItem<String>(ViewConfig.MAIN_TREE_HEADER_ITEM4),
				new TreeItem<String>(ViewConfig.MAIN_TREE_HEADER_ITEM5));
		main_treeview.setRoot(root);

	}


	public void mainTreeViewClick() throws IOException {
		logger.log(Level.INFO, "Click on the main tree view");

		TreeItem<String> selectedItem = main_treeview.getSelectionModel().getSelectedItem();
		logger.log(Level.INFO, selectedItem.getValue());

		String pagePath = "";
		switch (selectedItem.getValue()) {
		case ViewConfig.MAIN_TREE_HEADER:
			//pagePath = ViewConfig.;
			break;
		case ViewConfig.MAIN_TREE_HEADER_ITEM1:
			pagePath = ViewConfig.NEW_PORTFOLIO_VIEW_PATH;
			break;
		case ViewConfig.MAIN_TREE_HEADER_ITEM2:
			//pagePath = ViewConfig.;
			break;
		case ViewConfig.MAIN_TREE_HEADER_ITEM3:
			//pagePath = ViewConfig.;
			break;
		case ViewConfig.MAIN_TREE_HEADER_ITEM4:
			//pagePath = ViewConfig.DATA_VIEW_PATH;
			break;
		case ViewConfig.MAIN_TREE_HEADER_ITEM5:
			//pagePath = ViewConfig.;
			break;
		}
		
		skipView(pagePath);
	}


	private void skipView(String pagePath) throws IOException {
		ObservableList<Node> scrolChildren = main_pane_under_scroll.getChildren();
		scrolChildren.clear();
		scrolChildren.add(FXMLLoader.load(getClass().getResource(pagePath)));
	}

}


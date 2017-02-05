package by.tc.iosa.view;

import by.tc.iosa.bean.Node;
import by.tc.iosa.service.exception.ServiceException;
import by.tc.iosa.service.factory.ServiceFactory;
import by.tc.iosa.service.impl.AllFileService;
import org.apache.log4j.Logger;

/**
 * Created by User on 19.01.2017.
 */
public class view {

    private final static Logger logger = Logger.getLogger(view.class);

    public static void main(String[] args) {

        Node node = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AllFileService allFileService = serviceFactory.getAllFileService();

        try {
            // The default file is file.xml
            allFileService.setFilePath("abreakfastMenu.xml");
            allFileService.initData();
            while ((node = allFileService.getNextNode()) != null) {
                System.out.print(node.getContent() + " - ");
                switch (node.getNodeType()) {
                    case OPENING_TAG: {
                        System.out.println("OPENING TAG");
                        break;
                    }
                    case CLOSING_TAG: {
                        System.out.println("CLOSING TAG");
                        break;
                    }
                    case TAG_CONTENT: {
                        System.out.println("TAG CONTENT");
                        break;
                    }
                    case TAG_WITHOUT_BODY: {
                        System.out.println("TAG WITHOUT BODY");
                        break;
                    }
                }
            }
        } catch (ServiceException e) {
            System.out.println("Sorry, this functionality is not available");
            logger.fatal("Application crashed", e);
        }
    }
}

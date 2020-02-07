package MyProgs.util;

import MyProgs.data.ValuteList;
import MyProgs.bean.Valute;
import org.glassfish.jersey.server.Uri;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.print.DocFlavor;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URL;


public class DOMxmlReader implements Runnable{

    private static final String VALUTE = "https://www.cbr-xml-daily.ru/daily_utf8.xml";
    private static final String TAG = "Valute";
    private ValuteList list;

    public DOMxmlReader (){
        list = ValuteList.getInstance();
    }

    @Override
    public void run() {
        updateList();
    }

    private void updateList(){
        if (list.getList().size() > 0){
            updateValue();
        } else{
            parseXML();
        }
    }
    public NodeList getNodelist(String adress){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        NodeList nodeList = null;
        try{
            builder = factory.newDocumentBuilder();
            URL url = new URL(adress);
            Document document = builder.parse(url.openStream());
            document.getDocumentElement().normalize();
            nodeList = document.getElementsByTagName(TAG);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return nodeList;
    }
    private void parseXML(){
        NodeList nodeList = getNodelist(VALUTE);
        for (int i = 0; i < nodeList.getLength(); i++){
            list.addValute(getNewValute(nodeList.item(i)));
        }

    }
    public void updateValue(){

        NodeList nodeList = getNodelist(VALUTE);
        for (int i = 0; i < nodeList.getLength(); i++){
            Element element = (Element) nodeList.item(i);
            String name = getTagValue("Name",element);
            int nominal = Integer.parseInt(getTagValue("Nominal",element));
            double value = Double.parseDouble(getTagValue("Value",element).replaceAll(",","."));
            list.getValute(name).setNominal(nominal);
            list.getValute(name).setValue(value);
        }
    }
    private  Valute getNewValute(Node node){
        Valute valute = null;
        if (node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;
            int num = Integer.parseInt(getTagValue("NumCode", element));
            String charCode = getTagValue("CharCode", element);
            int nominal = Integer.parseInt(getTagValue("Nominal",element));
            String name = getTagValue("Name", element);
            double value = Double.parseDouble(getTagValue("Value",element).replaceAll(",","."));
            valute = new Valute(num,charCode,name);
            valute.setNominal(nominal);
            valute.setValue(value);
        }
        return valute;
    }
    private String getTagValue(String tag, Element element){
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
}

package com.example.v9t3;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class areaXML {
    listOfData list = new listOfData();
    String[] data2;


    public String[] readXML() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String url = "https://www.finnkino.fi/xml/TheatreAreas/";
            Document document = builder.parse(url);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getDocumentElement().getElementsByTagName("TheatreArea");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    data data1 = new data();
                    if (element.getElementsByTagName("ID").item(0).getTextContent().equals("1012") || element.getElementsByTagName("ID").item(0).getTextContent().equals("1014") || element.getElementsByTagName("ID").item(0).getTextContent().equals("1002") || element.getElementsByTagName("ID").item(0).getTextContent().equals("1021")) {
                    } else {
                        data1.setId(element.getElementsByTagName("ID").item(0).getTextContent());
                        data1.setName(element.getElementsByTagName("Name").item(0).getTextContent());
                        list.appendToList(data1);
                    }
                }
            }
            data2 = new String[list.getLength()];
            for (int i = 0; i < list.getLength(); i++) {
                data2[i] = list.getName(i);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return data2;
    }

    public StringBuilder specificDate(String name) {
        StringBuilder stringBuilder = new StringBuilder();

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        String dateFormatted = formatter.format(date);
        String url = "https://www.finnkino.fi/xml/Schedule/?area=" + list.getID(name) + "&dt=" + dateFormatted;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(url);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getDocumentElement().getElementsByTagName("Show");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String list1[] = (element.getElementsByTagName("dttmShowStart").item(0).getTextContent()).split("T");
                    stringBuilder.append(element.getElementsByTagName("Title").item(0).getTextContent() + "\nSali: " + element.getElementsByTagName("TheatreAuditorium").item(0).getTextContent() + "\nKesto : "+ element.getElementsByTagName("LengthInMinutes").item(0).getTextContent() + "min" + "\nEsityksen aloitus klo: " + list1[1] + "\n\n");
                    }
                }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }


}



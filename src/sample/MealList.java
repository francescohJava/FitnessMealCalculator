package sample;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Alert;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//Class MealList stores list of meals, it is a static list because existing only one list of meal in the whole program
public class MealList {
    static private final ArrayList<Meal> mealList;
    static private File xmlFile;
    static private BooleanProperty changeList;
    static final String fileName = "meal_nutrition_values.xml";

    static {
        mealList = new ArrayList<>();
        changeList = new SimpleBooleanProperty();
        readMealXML();
    }

    //Method to recogniyze changes in mealList, if user add or delete meal the combobox in main window refresh meals
    static public final void setChangeList(Boolean state)
    {
        changeList.set(state);
    }
    static public BooleanProperty changeListProperty()
    {
        return changeList;
    }

    static public boolean add(Meal meal)
    {
        if(find(meal.getName()) == null)
        {
            return mealList.add(meal);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input data error");
            alert.setContentText("The added meal exists!");

            return false;
        }

    }

    public static boolean delete(Meal meal)
    {
        return mealList.remove(meal);
    }

    public static boolean repair(Meal meal, int order)
    {
        Meal settedMeal = mealList.set(order, meal);
        return settedMeal.getName().equals(meal.getName());
    }

    static public Meal find(String name)
    {
        for(int i = 0; i < mealList.size(); i++)
        {
            if(mealList.get(i).getName().equals(name))
                return mealList.get(i);
        }
        return null;
    }

    static public int indexOf(Meal meal)
    {
        return mealList.indexOf(meal);
    }

    static private int readMealXML() {
        int i = 0;

        try {
            xmlFile = new File(fileName);
            if(xmlFile.length() == 0)
            {
                return 0;
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("meal");

            for (i = 0; i < nList.getLength(); i++) {

                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Meal tempMeal = new Meal();
                    Element eElement = (Element) nNode;

                    tempMeal.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                    tempMeal.setCalories(Double.parseDouble(eElement.getElementsByTagName("calories").item(0).getTextContent()));
                    tempMeal.setTotalFat(Double.parseDouble(eElement.getElementsByTagName("totalFat").item(0).getTextContent()));
                    tempMeal.setSaturatedFat(Double.parseDouble(eElement.getElementsByTagName("saturatedFat").item(0).getTextContent()));
                    tempMeal.setCholesterol(Double.parseDouble(eElement.getElementsByTagName("cholesterol").item(0).getTextContent()));
                    tempMeal.setTotalCarbohydrate(Double.parseDouble(eElement.getElementsByTagName("totalCarbohydrate").item(0).getTextContent()));
                    tempMeal.setDietaryFiber(Double.parseDouble(eElement.getElementsByTagName("dietaryFiber").item(0).getTextContent()));
                    tempMeal.setSugar(Double.parseDouble(eElement.getElementsByTagName("sugar").item(0).getTextContent()));
                    tempMeal.setProtein(Double.parseDouble(eElement.getElementsByTagName("protein").item(0).getTextContent()));

                    if(!add(tempMeal))
                    {
                        System.out.println("Add meal to the list failed!");
                    }
                    System.out.println(tempMeal);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    static public void writeMealXML()
    {
        try
        {
            xmlFile = new File(fileName);
            xmlFile.delete();
            xmlFile.createNewFile();

        }
        catch(IOException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("XML file error");
            alert.setContentText("meal_nutrition_values.xml cannot open to write");

            alert.showAndWait();
            return;
        }

        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("mealList");
            doc.appendChild(rootElement);

            for(int i = 0; i < mealList.size(); i++)
            {
                Element meal = doc.createElement("meal");
                rootElement.appendChild(meal);

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(mealList.get(i).getName()));
                meal.appendChild(name);

                Attr attr = doc.createAttribute("id");
                attr.setValue(Integer.toString(i));
                name.setAttributeNode(attr);

                Element calories = doc.createElement("calories");
                calories.appendChild(doc.createTextNode(Double.toString(mealList.get(i).getCalories())));
                meal.appendChild(calories);

                Element totalFat = doc.createElement("totalFat");
                totalFat.appendChild(doc.createTextNode(Double.toString(mealList.get(i).getTotalFat())));
                meal.appendChild(totalFat);

                Element saturatedFat = doc.createElement("saturatedFat");
                saturatedFat.appendChild(doc.createTextNode(Double.toString(mealList.get(i).getSaturatedFat())));
                meal.appendChild(saturatedFat);

                Element cholesterol = doc.createElement("cholesterol");
                cholesterol.appendChild(doc.createTextNode(Double.toString(mealList.get(i).getCholesterol())));
                meal.appendChild(cholesterol);

                Element totalCarbohydrate = doc.createElement("totalCarbohydrate");
                totalCarbohydrate.appendChild(doc.createTextNode(Double.toString(mealList.get(i).getTotalCarbohydrate())));
                meal.appendChild(totalCarbohydrate);

                Element dietaryFiber = doc.createElement("dietaryFiber");
                dietaryFiber.appendChild(doc.createTextNode(Double.toString(mealList.get(i).getDietaryFiber())));
                meal.appendChild(dietaryFiber);

                Element sugar = doc.createElement("sugar");
                sugar.appendChild(doc.createTextNode(Double.toString(mealList.get(i).getSugar())));
                meal.appendChild(sugar);

                Element protein = doc.createElement("protein");
                protein.appendChild(doc.createTextNode(Double.toString(mealList.get(i).getProtein())));
                meal.appendChild(protein);

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);

            transformer.transform(source, result);

            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("XML File");
            info.setContentText("Meals nutrition values saved successfully!");

            info.showAndWait();
        }
        catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }

    static public String[] toArrayStringName()
    {

        String arrayString[] = new String[mealList.size()];
        for(int i = 0; i < mealList.size(); i++)
        {
            arrayString[i] = mealList.get(i).getName();
        }
        return arrayString;
    }
}


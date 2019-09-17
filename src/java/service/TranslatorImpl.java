package service;
import java.io.IOException;
import javax.jws.WebService;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@WebService(endpointInterface= "service.Translator")
public class TranslatorImpl implements Translator {
    
    public  String translate(String toTrans, String srcLang, String destLang) {
            String prevod="";
            String eng, srb, ger;
            Element word, lang1, lang2, lang3;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setIgnoringElementContentWhitespace(true);
        try {
            
            
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse("prevodilac.xml");
                Element root = doc.getDocumentElement();
                NodeList words = root.getChildNodes();
        for(int i=0; i<words.getLength();i++)
        {
                word = (Element) words.item(i);
                lang1 = (Element) word.getChildNodes().item(0);
                lang2 = (Element) word.getChildNodes().item(1);
                lang3 = (Element) word.getChildNodes().item(2); 
                eng = lang1.getNodeName();
                srb = lang2.getNodeName();
                ger = lang3.getNodeName(); 
             if(srcLang.equals(eng)  &&  destLang.equals(srb)   
                && toTrans.toLowerCase().equals(lang1.getTextContent()))
            {
                prevod = lang2.getTextContent();
                break;
            }
            else if(srcLang.equals(eng) && destLang.equals(ger) 
                    && toTrans.toLowerCase().equals(lang1.getTextContent()))
            {
                prevod = lang3.getTextContent();
                break;
            }
            else if(srcLang.equals(srb) && destLang.equals(eng) && toTrans.toLowerCase().equals(lang2.getTextContent()))
            {
                prevod = lang1.getTextContent();
                break;
            }
            else if(srcLang.equals(srb) && destLang.equals(ger) 
                    && toTrans.toLowerCase().equals(lang2.getTextContent()))
            {
                prevod = lang3.getTextContent();
                break;
            }
            else if(srcLang.equals(ger) && destLang.equals(eng)
                    && toTrans.toLowerCase().equals(lang3.getTextContent()))
            {
                prevod = (lang1.getTextContent());
                break;
            }
            else if(srcLang.equals(ger) && destLang.equals(srb) && 
                    toTrans.toLowerCase().equals(lang3.getTextContent()))
            {
                prevod = (lang2.getTextContent());
                break;
            }
        }  
            if(!prevod.equals("")) 
            return(prevod.toUpperCase().charAt(0) + prevod.substring(1)); 
            else return "No such word in dictionary...";  
             
        
            } catch (ParserConfigurationException | SAXException | IOException ex) 
            {
                return("Error: "+ex.getMessage());
            }   
    } 

    public String addWord(String eng, String srb, String ger) {
            Element nWord, lang1, lang2, lang3;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setIgnoringElementContentWhitespace(true);
        try {
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse("prevodilac.xml");
                Element root = doc.getDocumentElement();
                nWord = doc.createElement("word");
                lang1 = doc.createElement("english");
                lang2 = doc.createElement("serbian");
                lang3 = doc.createElement("german");
                lang1.setTextContent(eng.toLowerCase());
                lang2.setTextContent(srb.toLowerCase());
                lang3.setTextContent(ger.toLowerCase());
                nWord.appendChild(lang1);
                nWord.appendChild(lang2);
                nWord.appendChild(lang3); 
                root.appendChild(nWord);
                DOMSource source = new DOMSource(doc);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult("prevodilac.xml");
        transformer.transform(source, result);
                return ("Uspesno dodata rec{ Eng: "+eng+", Srb: "+srb+", Ger: "+ger+"}");
            } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex)  
            {return "Greska prilikom dodavanja nove reci!";}
        }  
    public static void main(String[] args) {
        TranslatorImpl ti = new TranslatorImpl();
        System.out.println(ti.translate("house", "english", "serbian"));
    }
}
